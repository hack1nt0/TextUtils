package com.xiaomi.nlp.pattern;

//import org.apache.commons.math3.analysis.function.Min;

import com.xiaomi.nlp.tokenizer.MyTokenizer;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by DY on 15/2/28.
 */


public class MiningPatterns {

    private static Logger logger = Logger.getLogger(MiningPatterns.class);

    static {
        BasicConfigurator.configure();
        logger.setLevel(Level.ERROR);
    }

    public class Line {
        String text;
        String[] tokens;
        int sup;

        public Line(String text, int sup) {
            this.text = text;
            this.sup = sup;
        }

        public Line(String[] tokens, int sup) {
            this.tokens = tokens;
            this.sup = sup;
        }

        public String getText() {
            if (text != null) return text;
            StringBuffer sb = new StringBuffer("");
            for (String token: tokens) sb.append(token);
            text = sb.toString();
            return text;
        }

        public String[] getTokens() {
            if (tokens != null) return tokens;
            MyTokenizer tokenizer = MyTokenizer.getInstance();
            tokens = tokenizer.getTokens(text);
            return tokens;
        }

        public int getSupport() {
            return sup;
        }
    }

    public SmsPattern[] Patterns;
    //public List ToBeMine;
    //public int CorpusTotN;
    public int MIN_SUP;
    public double SUP_RATIO;
    public int MAX_SAMPLE_ITR = 5;
    ArrayList<SmsPattern> parsedCorpus = new ArrayList<SmsPattern>();
    public Map<Integer, Integer> baseSupMap = new HashMap<Integer, Integer>();
    public int NUM_THREAD = 2;

    public MiningPatterns(double supRatio) {
        this.SUP_RATIO = supRatio;
        //this.SUP_RATIO = 0.3;
    }

    public MiningPatterns(String taskName, double supRatio) {
        this.SUP_RATIO = supRatio;
        //this.SUP_RATIO = 0.3;
    }

    public void setMaxSampleItr(int MAX_SAMPLE_ITR) {
        this.MAX_SAMPLE_ITR = MAX_SAMPLE_ITR;
    }

    public void setSupRatio(double SUP_RATIO) {
        this.SUP_RATIO = SUP_RATIO;
    }

    public boolean inital(List<Line> toBeMine) {
        parsedCorpus.clear();
        baseSupMap.clear();
        for (int i = 0; i < toBeMine.size(); ++i) {
            Line corpusM = toBeMine.get(i);
            String tmp = corpusM.getText().replace("rn", ";").replace("\\r\\n", ";").replace("\\n", ";").replace(",", ",,").replace("，", "，，");
            tmp += ";";
            SmsPattern sms = SmsPattern.getNew(tmp);
            baseSupMap.put(i, corpusM.getSupport());
            //sms.addSource(i);
            //sms.baseSup = corpusM.getSupport();
            sms.id = i;
            sms.corpusId = i;
            parsedCorpus.add(sms);
        }
        MIN_SUP = SUP_RATIO >= 1 ? (int)SUP_RATIO : (int)Math.ceil(toBeMine.size() * SUP_RATIO);
        return true;
    }

    //生成patterns
    public List<SmsPattern> getPatWithPosition() {
        int PARTITION_SIZE = (int)Math.sqrt(parsedCorpus.size());
        if (PARTITION_SIZE == 0) return new ArrayList<SmsPattern>();
        Patterns = new SmsPattern[PARTITION_SIZE * 4];
        HashSet<SmsPattern> retSet = new HashSet<SmsPattern>();
        for(int itr = 0; itr < MAX_SAMPLE_ITR; ++itr) {
            HashMap<SmsPattern, Integer> itrRet = new HashMap<SmsPattern, Integer>();
            Collections.shuffle(parsedCorpus);
            for (SmsPattern smsPattern: parsedCorpus) smsPattern.reset();

            int from = 0;
            while (from < parsedCorpus.size()) {
                int curPartN = parsedCorpus.size() - from < 2 * PARTITION_SIZE ?  parsedCorpus.size() - from : PARTITION_SIZE;
                List<SmsPattern> partitionRet = getPatWithPosition(from, curPartN);
                for (SmsPattern smsPattern: partitionRet) {
                    int curSup = smsPattern.getSup(baseSupMap, from);
                    itrRet.put(smsPattern, itrRet.containsKey(smsPattern) ? itrRet.get(smsPattern) + curSup : curSup);
                }
                from += curPartN;
            }
            for (SmsPattern smsPattern: itrRet.keySet()) {
                int curSup = itrRet.get(smsPattern);
                if (curSup < MIN_SUP || retSet.contains(smsPattern)) continue;
                smsPattern.setFinalSup(curSup);
                retSet.add(smsPattern);
            }
        }
        List<SmsPattern> ret = new ArrayList<SmsPattern>();
        for (SmsPattern smsPattern: retSet) ret.add(smsPattern);
        return ret;
    }

    //生成patterns
    public List<SmsPattern> getPatWithPosition(int from, int PARTITION_SIZE) {

        logger.info("class capacity(records): " + PARTITION_SIZE);

        List<SmsPattern> ret = new ArrayList<SmsPattern>();

        for (int i = 0; i < PARTITION_SIZE; ++i) {
            Patterns[i] = parsedCorpus.get(i + from);
            if (baseSupMap.get(i + from) >= MIN_SUP) ret.add(Patterns[i]);
        }

        if (PARTITION_SIZE <= 1) return ret;

        int MAX_QUEUE_SIZE = PARTITION_SIZE;
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

        for (int i = 0; i < PARTITION_SIZE; ++i) { // get Lcp of i and j
            for (int j = i + 1; j < PARTITION_SIZE; ++j) {
                SmsPattern smsPattern = Patterns[i].getLctpWithFacade(Patterns[j]);//todo array out of bounds
                if (smsPattern == null) {
                    logger.error("=========== smsPattern == null =====================");
                    logger.error("left: " + Patterns[i].toString());
                    logger.error("right: " + Patterns[j].toString());
                    continue;
                }
                priQueue.add(smsPattern); //todo null pointer
                if (priQueue.size() > MAX_QUEUE_SIZE) priQueue.poll();
            }
        }
        logger.info("Ended pair-wise Lctp extraction.");


        for (int cur = PARTITION_SIZE; cur < PARTITION_SIZE * 2 - 1; ++cur) {
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
                SmsPattern smsPattern = Patterns[i].getLctpWithFacade(maxPattern); //todo array out of bounds
                if (smsPattern == null) {
                    logger.error("=========== smsPattern == null =====================");
                    logger.error("left: " + Patterns[i].toString());
                    logger.error("right: " + maxPattern.toString());
                    continue;
                }
                priQueue.add(smsPattern); //todo null pointer
                if (priQueue.size() > MAX_QUEUE_SIZE) priQueue.poll();
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
            for (int i = 0; i < PARTITION_SIZE; ++i) {
                //todo optimize on time complexity, but harmful due to wildcard argumentation
                //if (maxPattern.sourceIndex.get(i + from)) continue;
                if (maxPattern.isSubpatOfFacade(Patterns[i])) {//todo array out of bounds
                    maxPattern.updWildcards();
                    //maxPattern.sourceIndex.add(Patterns[i]);
                    maxPattern.addSource(i);
                }
            }
            logger.info("Ended fulfilling the sourceIndex of the maximal pattern.");
            //if (maxPattern.getSup(baseSupMap) < MIN_SUP) continue;

            logger.info("Begin deduplication of the ret");
            //deduplicate
            boolean repated = false;
            for (int i = 0; i < ret.size(); ++i) {
                if (maxPattern.isSubstrOfFacade(ret.get(i)) && maxPattern.getSup(baseSupMap, from) <= ret.get(i).getSup(baseSupMap, from)) {
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

