package com.xiaomi.nlp.classification.spamsms.smsspam.preprocess;

import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;

import java.util.ArrayList;
import java.util.List;

/**
 * Only ASCII and UTF digits are considered as normal digits
 * Assume there is a main digit type, either ASCII or UTF
 * The first digit is the main type
 * The last digit is main type too
 * @author qinqiuping
 *
 */
public class Numbers extends RulePrevious {
    
    public static final String NUMBERS_ASCII = "0123456789";
    public static final String NUMBERS_UTF = "０１２３４５６７８９";
    private static final String NUMBERS_UTF1 = "①②③④⑤⑥⑦⑧⑨";
    public static final String NUMBERS_UTF2 = "⒈⒉⒊⒋⒌⒍⒎⒏⒐";
    private static final String NUMBERS_UTF3 = "⓵⓶⓷⓸⓹⓺⓻⓼⓽";
    private static final String NUMBERS_CN = "零一二三四五六七八九";
    private static final String NUMBERS_TW = "零壹贰叁肆伍陆柒捌玖";

    private static final char SPECIAL_DIGIT = '一';  // Do not treat it as digit, as a connector

    private static final String[] FULL_NUMBERS =    {NUMBERS_ASCII, NUMBERS_UTF, NUMBERS_CN, NUMBERS_TW};
    private static final String[] NO_ZERO_NUMBERS = {NUMBERS_UTF1, NUMBERS_UTF2, NUMBERS_UTF3};
    
    private static final String NUMBERS_CONFUSION =     "I|loOBbgqzZ";
    private static final String NUMBERS_CONFUSION_MAP = "11100869922";
    private static final String NUMBERS_CONFUSION_UTF =     "｜ｏｑｌｂＯＩＢＺｚ";
    private static final String NUMBERS_CONFUSION_UTF_Map = "1091601822";

    private static final String[][] CONFUSION_MAP = {{NUMBERS_CONFUSION, NUMBERS_CONFUSION_MAP},
                                                     {NUMBERS_CONFUSION_UTF, NUMBERS_CONFUSION_UTF_Map}};
    private static final int CONFUSION_INDEX = 0;
    private static final int ASCII_INDEX = 1;

    // "一" is always considered as connector, not a CN digit
    private static final String NUMBERS_CONNECTOR = ",-—一~ 　";
    private static final char POINT = '.';
    private static final String COLONS = ":：";
    private static final String RANGE_SYMBOLS = "-—一~";

    // ASCII:0, UTF:1
    protected static final String[] NUMBERS = {
                                                NUMBERS_ASCII,
                                                NUMBERS_UTF,
                                                NUMBERS_UTF1,
                                                NUMBERS_UTF2,
                                                NUMBERS_UTF3,
                                                NUMBERS_CN,
                                                NUMBERS_TW};

    private static final int TAB = 9;
    private static final int NL = 10;
    private static final int CR = 13;

    protected ArrayList<Corpus.SMSNumber> mNumbers = new ArrayList<Corpus.SMSNumber>();

    protected int[] mCounts;// = new int[subClassCount()];

    protected static final int[] BANK_CARD_COUNT = {15, 16, 18, 19};
    protected static final String BANK_CARD_FIRST = "3456";

    public static final int BANK_CARD = 0;
    private static final int PHONE = 1;
    public static final int PHONE_MOBILE = 2;
    private static final int PHONE_400 = 3;
    private static final int RANGE = 4;
    private static final int TIME = 5;
    private static final int CONFUS = 6;

    private static final int OTHER = -1;

    private static final String[] Names = {
                                            "BankCard",
                                            "Phone",
                                            "PhoneMobile",
                                            "Phone400",
                                            "Range",
                                            "Time",
                                            "Confus"
    };


    private static final int ASCII = 0;
    private static final int UTF = 1;
    private static final int PURE = 0;
    private static final int CONFUSION = 1;
    // ASCII:0, UTF:1
    // Pure:0, Confusion:1
/*    private static final String[] TypeNames = {
                                            "PureASCII",
//                                            "PureUTF",
//                                            "ConfASCII",
//                                            "ConfUTF"
    };*/

    public Numbers()
    {
        mCounts = new int[subClassCount()];
    }

//    class Number {
//        String mNumber;
//        boolean mClassified;
//        Number(String n){
//            mNumber = n;
//        }
//    }
    @Override
    protected int subClassCount(){
        return Names.length;
    }

    @Override
    public String getClassName(int i){
        return padding("Number" + "_" + Names[i]);
    }

    protected void reset(){
        super.reset();
        mNumbers.clear();
//        mBankCards.clear();
        for(int i = 0; i < mCounts.length; ++i){
            mCounts[i] = 0;
        }
    }

    @Override
    public boolean doFitting(Corpus cps, int[] vals, int start) {
        boolean flag = false;
        for(int i = 0; i < mCounts.length; ++i){
            if(mCounts[i] > 0){
                flag = true;
            }
            vals[start + i] = mCounts[i];
        }
        if(mNumbers.size() > 0){
            cps.mNumbers = new ArrayList<Corpus.SMSNumber>();
            cps.mNumbers.addAll(mNumbers);
        }
        return flag;
    }

    private static final int MIN_NUMBER_COUNT = 2;
    private static final int RANGE_SECTIONS_COUNT = 2;

    private int dispose(String n, int type, boolean hasConnector, char connector, boolean hasMark, char mark){
        int classId = -1;
        if(n.length() <= MIN_NUMBER_COUNT){
            return classId;
        }
        int pure = isPureType(n) ? PURE : CONFUSION;
        ArrayList<String> sections = new ArrayList<String>();

        // Cut the numbers by connector
        if(hasConnector){
            boolean flag = false;
            int lastPos = 0;
            for(int i = 0; i < n.length(); ++i){
                if(n.charAt(i) == connector){
                    if(!flag){
                        // i should bigger than lastPos, according to the rules
                        sections.add(n.substring(lastPos, i));
                        flag = true;
                    }
                    lastPos = i + 1;
                }else{
                    flag = false;
                }
            }
            sections.add(n.substring(lastPos));

            for(int i = 0; i < sections.size(); ++i){
                sections.set(i, convertToPureASCII(sections.get(i)));
            }

            // In this section, type RANGE is over
            if(RANGE_SYMBOLS.indexOf(connector) != -1 && sections.size() == RANGE_SECTIONS_COUNT){
                if(sections.get(0).charAt(0) == NUMBERS_ASCII.charAt(0) && // area code start with 0
                        !hasMark && //n.indexOf(POINT) == -1 &&                   // no marks
                        (sections.get(0).length() == 3 || sections.get(0).length() == 4) &&  // length of area code == 3/4
                        (sections.get(1).length() == 7 || sections.get(1).length() == 8)){   // length of code == 7/8
                    classId = PHONE;
                }else {
                    String s0 = sections.get(0);
                    String s1 = sections.get(1);
                    if(hasMark && mark != POINT){
                        s0 = s0.replace(mark, POINT);
                        s1 = s1.replace(mark, POINT);
                    }
                    if(validFloat(s0) && validFloat(s1) &&
                            Double.valueOf(s0) < Double.valueOf(s1)){
                        // 400 phone exception
                        if(sections.get(0).equals("400") && s1.length() == 7 && !hasMark){
                            //classId = PHONE_400;
                            classId = PHONE_400;
//                            System.out.println("PHONE_400:" + n);
                        }else{
                            //classId = RANGE;
                            classId = RANGE;
//                            System.out.println("RANGE:" + n);
                        }
                    }
                }
            }
        }else{
            sections.add(convertToPureASCII(n));
        }

        if(classId < 0){
            StringBuilder sb = new StringBuilder();
            for(String s : sections){
                sb.append(s.replaceAll("\\.", ""));
            }
            String pureN = sb.toString();
    //        System.out.println("pureN:" + pureN);
    
            int count = pureN.length();
            if(count > 19){
                classId = OTHER;
//                System.out.println("LONG:" + n);
            }else if(count == 15 || count == 16 || count == 19){ // BankCard
                boolean validBankCard = isValidNumber(pureN);
                if(validBankCard && !(hasMark && hasConnector)){
                    classId = BANK_CARD;
                }else{
                    classId = OTHER;
//                    System.out.println("BANK_CARD_OTHER:" + n);
                }
            }else if(count > 12){
                //classId = LONG;
                classId = OTHER;
//                System.out.println("LONG:" + n);
            }else if(count >= 11){ // phone with area code, or mobile phone
                if(pureN.charAt(0) == NUMBERS_ASCII.charAt(0)){
                    classId = PHONE;
                }
                if(AddressType.isPersonalAddress(AddressType.type(pureN))){
                    classId = PHONE_MOBILE;
//                    System.out.println("PHONE_MOBILE:" + n);
                }
            }else if(count == 10){ // 400 phone
                if("400".equals(pureN.substring(0, 3))){
                    classId = PHONE_400;
                }
            }else if(sections.size() == 1 && hasMark && COLONS.indexOf(mark) != -1){
                classId = TIME;
//                System.out.println("TIME:" + n);
            }else if(!hasMark && !hasConnector && pureN.charAt(0) > '1' && (count == 7 || count == 8)){
                classId = PHONE;
//                System.out.println("PHONE:" + n);
            }else{
                classId = OTHER;
            }
        }

        if(classId >= 0){
            mCounts[classId]++;
            if(pure != 0 || type != 0){
                mCounts[CONFUS]++;
            }
        }
        return classId;
    }

    private static boolean validFloat(String f){
        return f.indexOf(POINT) == f.lastIndexOf(POINT);
    }

    protected List<String> process(String str) {
        if(null == str){
            return null;
        }
        ArrayList<String> ret = new ArrayList<String>();
        int startPos = -1, endPos = -1, lastPos = 0;
        char connector = '0';
        boolean hasConnector = false;
        int firstConnectorPos = -1;

        int numberType = ASCII;

        boolean hasMark = false;  // POINT(.)  COLON(:：)
        char mark = POINT;

        for(int i = 0; i < str.length(); ++i){
            char c = str.charAt(i);
            if(looksLikeNumber(c) && c != SPECIAL_DIGIT){
                if(startPos < 0){
                    if(looksLikeArabic(c)){
                        startPos = i;
                        numberType = regularType(c);
                    }else{
                        continue;
                    }
                }
                if(looksLikeArabic(c)){
                    endPos = i;
                }
            }else if(startPos >= 0){
                if(isConnector(c) && !hasConnector){
                    connector = c;
                    hasConnector = true;
                    firstConnectorPos = i;
                }
                else if(isMark(c) && !hasMark){
                    mark = c;
                    hasMark = true;
                }
                else if(c != connector && c != mark){
                    if(startPos > lastPos){
                        String subSeg = str.substring(lastPos, startPos);
                        ret.add(subSeg);
                    }

                    String nb = str.substring(startPos, endPos + 1);
                    if(hasConnector){
                        hasConnector = firstConnectorPos < endPos;
                    }
                    int classId = dispose(nb, numberType, hasConnector, connector, hasMark, mark);
                    if(classId >= 0){
                        mNumbers.add(new Corpus.SMSNumber(nb, classId));
                    }
                    lastPos = endPos + 1;

                    startPos = -1;
                    hasConnector = false;
                    numberType = ASCII;
                    firstConnectorPos = -1;
                    hasMark = false;
                }
            }
        }
        if(startPos >= 0){
            if(startPos > lastPos){
                ret.add(str.substring(lastPos, startPos));
            }
            if(endPos + 1 < str.length()){
                ret.add(str.substring(endPos + 1));
            }
            String nb = str.substring(startPos, endPos + 1);
            int classId = dispose(nb, numberType, hasConnector, connector, hasMark, mark);
            if(classId >= 0){
                mNumbers.add(new Corpus.SMSNumber(nb, classId));
            }
        }else{
            ret.add(str.substring(lastPos, str.length()));
        }
        return ret;
    }

    protected static int indexOfNumber(char c){
        for(int i = 0; i < NUMBERS.length; ++i){
            String s = NUMBERS[i];
            if(s.indexOf(c) != -1){
                return i;
            }
        }
        return -1;
    }

    protected static String convertToPureASCII(String str){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); ++i){
            char c = str.charAt(i);
            if(!isConnector(c)){
                sb.append(convertToPureASCII(c));
            }
        }
        return sb.toString();
    }

    protected static char convertToPureASCII(char c){
        int index = -1;
        for(String N : FULL_NUMBERS){
            index = N.indexOf(c);
            if(index != -1){
                return NUMBERS_ASCII.charAt(index);
            }
        }

        for(String N : NO_ZERO_NUMBERS){
            index = N.indexOf(c);
            if(index != -1){
                return NUMBERS_ASCII.charAt(index + 1);
            }
        }
        for(int i = 0; i < CONFUSION_MAP.length; ++i){
            index = CONFUSION_MAP[i][CONFUSION_INDEX].indexOf(c);
            if(index != -1){
                return CONFUSION_MAP[i][ASCII_INDEX].charAt(index);
            }
        }
        return c;
    }

    protected static boolean isConfusionNumber(char c){
        return (NUMBERS_CONFUSION.indexOf(c) != -1) ||
               (NUMBERS_CONFUSION_UTF.indexOf(c) != -1);
    }

    protected static boolean looksLikeNumber(char c){
        return (indexOfNumber(c) >= 0) || isConfusionNumber(c);
    }

    protected static boolean isRegularNumber(char c){
        return NUMBERS_ASCII.indexOf(c) != -1 || NUMBERS_UTF.indexOf(c) != -1;
    }

    protected static int regularType(char c){
        int type = ASCII;
        if(NUMBERS_UTF.indexOf(c) != -1){
            type = UTF;
        }
        return type;
    }

    protected static boolean isConnector(char c){
        return (NUMBERS_CONNECTOR.indexOf(c) != -1)
            || ((int)c == NL) || ((int)c == TAB) || ((int)c == CR);
    }

    private static boolean isMark(char c){
        return COLONS.indexOf(c) != -1 || c == POINT;
    }

    protected static boolean isPureType(String s){
        if(null == s || s.length() <= 0){
            return false;
        }

        int type = indexOfNumber(s.charAt(0));
        if(type < 0){
            return false;
        }
        for(int j = 1; j < s.length(); ++j){
            char c = s.charAt(j);
            if(indexOfNumber(c) != type && !isConnector(c) && !isMark(c)){
                return false;
            }
        }
        return true;
    }

    private static boolean isValidNumber(String numbs){
        boolean ret = false;
        if(numbs.charAt(0) == '4' || numbs.charAt(0) == '5' || numbs.charAt(0) == '6'){
            int sum = 0;
            int digit = 0;
            int addend = 0;
            boolean timesTwo = false;
            for(int i = numbs.length() - 1; i >= 0; --i){
                digit = numbs.charAt(i) - '0';
                if(timesTwo){
                    addend = digit * 2;
                    if(addend > 9){
                        addend -= 9;
                    }
                }else{
                    addend = digit;
                }
                sum += addend;
                timesTwo = !timesTwo;
            }
            int modulus = sum % 10;
            ret = (0 == modulus);
        }else{
            ret = false;
        }

        // UnionPay may not conform to "Luhn Check Digit Algorithm"
        if(!ret && (numbs.length() == 19 || numbs.length() == 16)){
            ret = numbs.charAt(0) == '6' && numbs.charAt(1) == '2';
        }
        return ret;
    }

    protected static boolean looksLikeArabic(char c){
        return looksLikeNumber(c) && NUMBERS_CN.indexOf(c) == -1 && NUMBERS_TW.indexOf(c) == -1;
    }

    @Override
    public String getName() {
        return "Numbers";
    }
}
