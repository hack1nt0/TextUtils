package com.xiaomi.nlp.classification.spamsms.smsspam;

import com.xiaomi.nlp.classification.spamsms.smsspam.feature.FeatureManager;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.AddressType;

import java.util.List;
import java.util.Set;

public class Corpus{
    public final static String BODY = "body";
    public final static String SPAM = "spam";
    public final static String ADDRESS = "address";
    public final static String ID = "id";

    private Set<String> mSegments;
    private List<String> mSortedSegments;
    private List<String> mProccessedSplits;

    private String mOrigBody;
    private String mCleanBody;

    private boolean mIsSpam;  // true is spam, false is normal
    private int[] mRules;
    public List<String> mUrls;

    private String mAddress;
    private int mAddressType;
    private boolean mMarked = false;
    private boolean mCatChanged = false;

    private int mId;

    private boolean mHasPhish = false;
    private boolean mIsRemittance = false;

    public List<SMSNumber> mNumbers;
    public double value;

    public static final int STRUCTURE_TYPE_ALL_SMALL_SEGS = 1;
    public static final int STRUCTURE_TYPE_HAS_PHISH = 2;
    private int mStructType = 0;
    private int mSMSType = 0;

    public Corpus(String address, String body){
        mAddress = removePre(address);
        mAddressType = AddressType.type(mAddress);
        mOrigBody = SundriesManager.tryRemove(body);
    }

    public void setSMSType(int t){
        mSMSType = t;
    }

    public int getSMSType(){
        return mSMSType;
    }

    public void addStructType(int t){
        mStructType |= t;
    }

    public int getStructType(){
        return mStructType;
    }

    public boolean hasStrongSpamFeature(){
        return isRemittance() || hasPhish();
    }

    public boolean isRemittance() {
        return mIsRemittance;
    }

    public void setIsRemittance(boolean is) {
        mIsRemittance = is;
    }

    public boolean hasPhish(){
        return mHasPhish;
    }

    public void setHasPhish(boolean has){
        mHasPhish = has;
    }

    public boolean isCatChanged() {
        return mCatChanged;
    }

    public void setCatChanged(boolean catChanged) {
        this.mCatChanged = catChanged;
    }

    public boolean isMarked() {
        return mMarked;
    }

    public void setMarked(boolean marked) {
        this.mMarked = marked;
    }

    public String getCleanBody() {
        return mCleanBody;
    }

    public void setCleanBody(String cleanBody) {
        this.mCleanBody = cleanBody;
    }

    public int getId(){
        return mId;
    }

    public void setId(int id){
        mId = id;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public int getAddressType(){
        return mAddressType;
    }

    public String getOrigBody() {
        return mOrigBody;
    }

    public void setOrigBody(String origBody) {
        this.mOrigBody = origBody;
    }

    public int[] getRules() {
        return mRules;
    }

    public void setRules(int[] rules) {
        mRules = rules;
    }

    public Set<String> getSegments(){
        return mSegments;
    }

    public void setSegments(Set<String> segs){
        mSegments = segs;
    }

    public List<String> getSortedSegments(){
        return mSortedSegments;
    }

    public void setSortedSegments(List<String> segs){
        mSortedSegments = segs;
    }

    public void setProccessedSplits(List<String> splits){
        mProccessedSplits = splits;
    }

    public List<String> getProccessedSplits(){
        return mProccessedSplits;
    }

    public boolean isSpam(){
        return mIsSpam;
    }

    public boolean likeNormal(){
        return (mSMSType & FeatureManager.TYPE_LIKE_NORMAL_MASK) != 0;
    }

    public void setSpam(boolean isSpam){
        mIsSpam = isSpam;
    }

    private String removePre(String address){
        if(address.length() > "+86".length() && address.indexOf("+86") == 0){
            address = address.substring(3);
        }
        return address;
    }

    public String toString(){
        return new StringBuilder().append("[")
                                  .append(mIsSpam)
                                  .append("][")
                                  .append(mAddress)
                                  .append("]:")
                                  .append(mOrigBody)
                                  .toString();
    }

    public static class SMSNumber{
        public SMSNumber(String s, int t){
            str = s;
            type = t;
        }

        public int type;
        public String str;
    }
}
