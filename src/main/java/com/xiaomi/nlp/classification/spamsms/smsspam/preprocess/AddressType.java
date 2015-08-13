package com.xiaomi.nlp.classification.spamsms.smsspam.preprocess;

public class AddressType extends Rule {
    private static final int COMMON = 0;
    private static final int L00XX = 1;
    private static final int L06SHORT = 2;
    private static final int L06COMMON = 3;
    private static final int L06LONG = 4;
    private static final int gPRE5 = 5;
    private static final int OTHER = 6;
    private static final int NULL = 7;
    private static final String[] Names = {
        "Common",
        "100+5",
        "106Short",
        "106Common",
        "106Long",
        "9Pre5",
        "Other",
        "Null"
    };

    public static int getTypeCount(){
        return Names.length;
    }

    @Override
    public String getName() {
        return "AddressType";
    }

    public String getName(int i) {
        return Names[i];
    }

    public static boolean isPersonalAddress(int type){
        return type == COMMON;
    }

    public static boolean isImportantAddress(int type){
        return type == L00XX ||  type == gPRE5;
    }

    public static boolean isOfficalAddress(int type){
        return type == L06SHORT;
    }

    public static int type(String address){
        if(address == null || address.length() == 0){
            return NULL;
        }

        if(address.charAt(0) == '1' && address.length() >= 5){
            char c = address.charAt(1);
            if(address.length() == 11 && (c == '3' || c == '5' || c == '8' ||
                                         (c == '4' && address.charAt(2) == '7'))){
                return COMMON;
            }
            if(c == '0'){
                if(address.length() == 5 && address.charAt(2) == '0'){
                    return L00XX;
                }
                if(address.charAt(2) == '6'){
                    if(address.length() < 9){
                        return L06SHORT;
                    }else if(address.length() < 12){
                        return L06COMMON;
                    }else {
                        return L06LONG;
                    }
                }
            }
        }else if(address.charAt(0) == '9' && address.length() == 5){
            return gPRE5;
        }
        return OTHER;
    }
}
