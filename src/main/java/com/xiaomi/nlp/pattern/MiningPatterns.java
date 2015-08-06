package com.xiaomi.nlp.pattern;

//import org.apache.commons.math3.analysis.function.Min;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

/**
 * Created by DY on 15/2/28.
 */


public class MiningPatterns {

    private static Logger logger = new Logger(MiningPatterns.class);

    static {
        BasicConfigurator.configure();
        logger.setLevel(Level.ERROR);
    }

    public static class CorpusM implements PatternMinable {
        String text;
        int sup;

        public CorpusM(String text, int sup) {
            this.text = text;
            this.sup = sup;
        }

        @Override
        public String getCorpus() {
            return text;
        }

        @Override
        public List<String> getTokens() {
            return null;
        }

        @Override
        public int getSupport() {
            return sup;
        }
    }

    public SmsPattern[] Patterns;
    public List ToBeMine;
    public int CorpusTotN;
    public int MinSup;
    public double SupRatio;

    public MiningPatterns(double supRatio) {
        this.SupRatio = supRatio;
        //this.SupRatio = 0.3;
    }

    public MiningPatterns(String taskName, double supRatio) {
        this.SupRatio = supRatio;
        //this.SupRatio = 0.3;
    }

    public boolean inital(List<Line> toBeMine) {
        this.ToBeMine = toBeMine;
        this.CorpusTotN = toBeMine.size();
        Patterns = new SmsPattern[CorpusTotN * 2];

        for (int i = 0; i < toBeMine.size(); ++i) {
            String tmp = corpusM.getCorpus().replace("rn", ";").replace("\\r\\n", ";").replace("\\n", ";").replace(",", ",,").replace("，", "，，");
            tmp += ";";
            SmsPattern sms = SmsPattern.getNew(tmp);
            sms.baseSup = corpusM.getSupport();
            sms.id = i;
            sms.corpusId = i;
            Patterns[i] = sms;
        }
        MinSup = (int)Math.ceil(toBeMine.size() * SupRatio);
        return true;
    }

    //生成patterns
    public List<SmsPattern> getPatWithPosition() {
        logger.info("class capacity(records): " + CorpusTotN);

        List<SmsPattern> ret = new ArrayList<SmsPattern>();

        for (int i = 0; i < CorpusTotN; ++i)
            if (Patterns[i].getSup() >= MinSup) ret.add(Patterns[i]);

        if (CorpusTotN <= 1) return ret;

        int MAXQUEUESIZE = MinSup * 10;
        /*
        PriorityQueue<SmsPattern> priQueueTmp = new PriorityQueue<SmsPattern>();

        for (int i = 0; i < CorpusTotN; ++i) { // get Lcp of i and j
            for (int j = i + 1; j < CorpusTotN; ++j) {
                priQueueTmp.add(Patterns[i].getLctpWith(Patterns[j]));
                if (priQueueTmp.size() > MAXQUEUESIZE) priQueueTmp.poll();
            }
        }

        PriorityQueue<SmsPattern> priQueue = new PriorityQueue<SmsPattern>(MAXQUEUESIZE, new Comparator<SmsPattern>() {
            @Override
            public int compare(SmsPattern o1, SmsPattern o2) {
                return - o1.compareTo(o2);
            }
        });
        for (SmsPattern smsPattern : priQueueTmp) priQueue.add(smsPattern);
        */
        logger.info("Began pair-wise Lctp extraction.");
        //Tbst<SmsPattern> priQueue = new Tbst<SmsPattern>();
        PriorityQueue<SmsPattern> priQueue = new PriorityQueue<SmsPattern>();

        for (int i = 0; i < CorpusTotN; ++i) { // get Lcp of i and j
            for (int j = i + 1; j < CorpusTotN; ++j) {
                SmsPattern tmp = Patterns[i].getLctpWithFacade(Patterns[j]);
                priQueue.add(tmp);
                if (priQueue.size() > MAXQUEUESIZE) priQueue.poll();
            }
        }
        logger.info("Ended pair-wise Lctp extraction.");


        for (int cur = CorpusTotN; cur < CorpusTotN * 2 - 1; ++cur) {
            SmsPattern maxPattern = null;
            while (!priQueue.isEmpty()) {
                maxPattern = priQueue.poll();
                if (maxPattern.lc == null || maxPattern.rc == null) {//maxpattern is EmptyOrd
                    return ret;
                }
                if (maxPattern.lc.used && maxPattern.rc.used) {
                    maxPattern = null;
                    continue;
                }
                break;
            }
            if (maxPattern == null) break;

            Patterns[cur] = maxPattern;
            Patterns[cur].id = cur;
            maxPattern.lc.used = maxPattern.rc.used = true;

            logger.info("Begin enerating new patterns from the maximal pattern.");
            //new patterns come from the new pattern
            for (int i = 0; i < cur; ++i) {
                if (i == maxPattern.lc.id || i == maxPattern.rc.id) continue;
                //Lcp[i][cur] = getLcsp(smsPatterns[i], smsPatterns[cur]);
                priQueue.add(Patterns[i].getLctpWithFacade(maxPattern));
                if (priQueue.size() > MAXQUEUESIZE) priQueue.poll();
            }
            logger.info("Ended generating new patterns from the maximal pattern.");

            /*
            //loose the space complexity
            if (priQueue.size() > MAXQUEUESIZE) {
                PriorityQueue<SmsPattern> tmp = new PriorityQueue<SmsPattern>();
                for (int i = 0; i < MAXQUEUESIZE; ++i) tmp.add(priQueue.poll());
                priQueue = tmp;
            }*/

            logger.info("Begin fulfilling the sourceIndex of the maximal pattern.");
            // cal sourceIndex
            for (int i = 0; i < CorpusTotN; ++i) {
                //todo optimize on time complexity
                if (maxPattern.isSubpatOfFacade(Patterns[i])) {
                    maxPattern.updWildcards();
                    maxPattern.sourceIndex.add(Patterns[i]);
                }
            }
            logger.info("Ended fulfilling the sourceIndex of the maximal pattern.");
            if (maxPattern.getSup() < MinSup) continue;

            logger.info("Begin deduplication of the ret");
            //deduplicate
            boolean repated = false;
            for (int i = 0; i < ret.size(); ++i) {
                if (maxPattern.isSubstrOfFacade(ret.get(i)) && maxPattern.getSup() <= ret.get(i).getSup()) {
                    repated = true;
                    break;
                }
//                if (ret.get(i).isSubstrOfFacade(maxPattern)) {
//                    ret.get(i).chds.clear();
//                    ret.get(i).chds.add(new Token("Repated"));
//                    //ret.set(i, new Token("Repated"));
//                }
            }
            logger.info("Ended deduplication of the ret");
            if (!repated) {
                ret.add(maxPattern);
                //System.out.println(ret.get(ret.size() - 1));
            }
            maxPattern.used = true;
        }

        return ret;
    }

}