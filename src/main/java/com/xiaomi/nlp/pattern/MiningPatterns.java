package com.xiaomi.nlp.pattern;

//import org.apache.commons.math3.analysis.function.Min;

import com.xiaomi.nlp.tokenizer.HMMSentSeg;
import com.xiaomi.nlp.tokenizer.ISentSeg;
import com.xiaomi.nlp.tokenizer.MyTokenizer;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DY on 15/2/28.
 */


public class MiningPatterns {

    private static Logger logger = Logger.getLogger(MiningPatterns.class);

    static {
        BasicConfigurator.configure();
        logger.setLevel(Level.ERROR);
    }

    public static class Line implements PatternMinable{
        String text;
        List<String> tokens;
        int sup;

        public Line(String text, int sup) {
            this.text = text;
            this.sup = sup;
        }

        public Line(List<String> tokens, int sup) {
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

        @Override
        public String getCorpus() {
            return getText();
        }

        @Override
        public List<String> getTokens() {
            if (tokens != null) return tokens;
            MyTokenizer tokenizer = MyTokenizer.getInstance();
            tokens = tokenizer.getTokens(text);
            return tokens;
        }

        public String[] getTokensArr() {
            if (tokens != null) return tokens.toArray(new String[0]);
            MyTokenizer tokenizer = MyTokenizer.getInstance();
            tokens = tokenizer.getTokens(text);
            return tokens.toArray(new String[0]);
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
    public boolean ENABLE_ARGUMENT_WILDCARD = true;

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

    public boolean isENABLE_ARGUMENT_WILDCARD() {
        return ENABLE_ARGUMENT_WILDCARD;
    }

    public void setENABLE_ARGUMENT_WILDCARD(boolean ENABLE_ARGUMENT_WILDCARD) {
        this.ENABLE_ARGUMENT_WILDCARD = ENABLE_ARGUMENT_WILDCARD;
    }

    public boolean inital(List<? extends PatternMinable> toBeMine) {
        parsedCorpus.clear();
        baseSupMap.clear();
        for (int i = 0; i < toBeMine.size(); ++i) {
            PatternMinable corpusM = toBeMine.get(i);

            String tmp = corpusM.getCorpus();
            //tmp = tmp.replace("rn", ";").replace("\\r\\n", ";").replace("\\n", ";").replace(",", ",,").replace("，", "，，");
            //squeeze consecutive space to one
            char[] tmpChars = tmp.toCharArray();
            int actualLen = 0;
            for (int k = 0; k < tmpChars.length;) {
                if (tmpChars[k] != ' ') {tmpChars[actualLen++] = tmpChars[k++]; continue;}
                ++actualLen;
                while (k < tmpChars.length && tmpChars[k++] == ' ');
            }
            tmp = new String(tmpChars, 0, actualLen);
            //tmp += ";";
            ISentSeg sentSeg = new HMMSentSeg();
            List<ISentSeg.Sentence> sents = sentSeg.getSents(tmp);
            SmsPattern sms = new Sms();
            for (ISentSeg.Sentence sent: sents) {
                OrdSent ordSent = new OrdSent();
                for (String token : sent.words) ordSent.add(new Token(token));
                ordSent.punc = sent.punc;
                sms.add(ordSent);
            }
            //SmsPattern sms = SmsPattern.getNew(tmp);
            baseSupMap.put(i, corpusM.getSupport());
            //sms.addSource(i);
            //sms.baseSup = corpusM.getSupport();
            sms.id = i;
            sms.corpusId = i;
            parsedCorpus.add(sms);
        }
        MIN_SUP = SUP_RATIO > 1 ? (int)SUP_RATIO : (int)Math.ceil(toBeMine.size() * SUP_RATIO);
        SUP_RATIO = SUP_RATIO <= 1 ? SUP_RATIO : SUP_RATIO / toBeMine.size();
        return true;
    }

    public List<SmsPattern> getPatWithPositionByRandomPartitions() {
        return getPatWithPositionByRandomPartitions(SUP_RATIO);
    }

    public List<SmsPattern> getPatWithPositionByRandomPartitions(double MIN_PARTITION_PAT_SUP_RATIO) {
        int PARTITION_SIZE = (int)Math.sqrt(parsedCorpus.size()); //todo to find the optimistic one
        if (PARTITION_SIZE == 0) return new ArrayList<SmsPattern>();
        //Patterns = new SmsPattern[PARTITION_SIZE * 4];
        HashSet<SmsPattern> retSet = new HashSet<SmsPattern>();
        for(int itr = 0; itr < MAX_SAMPLE_ITR; ++itr) {
            HashMap<SmsPattern, Integer> itrRet = new HashMap<SmsPattern, Integer>();
            Collections.shuffle(parsedCorpus);
            for (SmsPattern smsPattern: parsedCorpus) smsPattern.reset();
            int from = 0;
            while (from < parsedCorpus.size()) {
                int curPartN = parsedCorpus.size() - from < 2 * PARTITION_SIZE ?  parsedCorpus.size() - from : PARTITION_SIZE;
                List<SmsPattern> partitionRet = getPatWithPosition(from, curPartN, (int)(MIN_PARTITION_PAT_SUP_RATIO * curPartN));
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

    public List<SmsPattern> getPatWithPositionWithMultiThread(int NUM_THREAD, double MIN_PARTITION_PAT_SUP_RATIO) {
        int PARTITION_SIZE = (int)Math.sqrt(parsedCorpus.size()); //todo to find the optimistic one
        if (PARTITION_SIZE == 0) return new ArrayList<SmsPattern>();
        //Patterns = new SmsPattern[parsedCorpus.size() * 2];
        ExecutorService threadPool = Executors.newFixedThreadPool(NUM_THREAD);
        HashSet<SmsPattern> retSet = new HashSet<SmsPattern>();
        final double MIN_PARTITION_PAT_SUP_RATIO_FINAL = MIN_PARTITION_PAT_SUP_RATIO;
        for(int itr = 0; itr < MAX_SAMPLE_ITR; ++itr) {
            final Map<SmsPattern, Integer> itrRet = Collections.synchronizedMap(new HashMap<SmsPattern, Integer>());
            Collections.shuffle(parsedCorpus);
            for (SmsPattern smsPattern: parsedCorpus) smsPattern.reset();

            final int[] from = new int[]{0};
            while (from[0] < parsedCorpus.size()) {
                final int[] curPartN = new int[]{parsedCorpus.size() - from[0] < 2 * PARTITION_SIZE ?  parsedCorpus.size() - from[0] : PARTITION_SIZE};
                threadPool.execute(new Thread() {
                    @Override
                    public void run() {
                        List<SmsPattern> partitionRet = getPatWithPosition(from[0], curPartN[0], (int)(MIN_PARTITION_PAT_SUP_RATIO_FINAL * curPartN[0]));
                        for (SmsPattern smsPattern: partitionRet) {
                            int curSup = smsPattern.getSup(baseSupMap, from[0]);
                            itrRet.put(smsPattern, itrRet.containsKey(smsPattern) ? itrRet.get(smsPattern) + curSup : curSup);
                        }
                    }
                });

                from[0] += curPartN[0];
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

    public List<SmsPattern> getPatWithPositionMultiThread(int NUM_THREAD) {
        return getPatWithPositionWithMultiThread(NUM_THREAD, SUP_RATIO);
    }

    public List<SmsPattern> getPatWithPosition() {
        List<SmsPattern> res = getPatWithPosition(0, parsedCorpus.size(), MIN_SUP);
        for (SmsPattern smsPattern: res) smsPattern.setFinalSup(smsPattern.getSup(baseSupMap, 0));
        return res;
    }

    //生成patterns
    public List<SmsPattern> getPatWithPosition(int from, int PARTITION_SIZE, int MIN_PARTITION_PAT_SUP) {
        logger.info("class capacity(records): " + PARTITION_SIZE);

        List<SmsPattern> ret = new ArrayList<SmsPattern>();
        Patterns = new SmsPattern[PARTITION_SIZE * 2];
        for (int i = 0; i < PARTITION_SIZE; ++i) {
            Patterns[i] = parsedCorpus.get(i + from);
            Patterns[i].addSource(i);
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
                if (!ENABLE_ARGUMENT_WILDCARD) {
                    smsPattern.addSource(i);
                    smsPattern.addSource(j);
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
                if (!ENABLE_ARGUMENT_WILDCARD) {
                    smsPattern.addSource(Patterns[i].sourceIndex);
                    smsPattern.addSource(maxPattern.sourceIndex);
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
                if (!ENABLE_ARGUMENT_WILDCARD && maxPattern.sourceIndex.get(i)) continue;
                if (maxPattern.isSubpatOfFacade(Patterns[i])) {//todo array out of bounds
                    if (ENABLE_ARGUMENT_WILDCARD) maxPattern.updWildcards();
                    //maxPattern.sourceIndex.add(Patterns[i]);
                    maxPattern.addSource(i);
                }
            }
            logger.info("Ended fulfilling the sourceIndex of the maximal pattern.");
            if (maxPattern.getSup(baseSupMap, from) < MIN_PARTITION_PAT_SUP) continue;

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

