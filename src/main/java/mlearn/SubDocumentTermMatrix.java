package mlearn;

/**
 * @author dy[jealousing@gmail.com] on 17-5-22.
 */
public class SubDocumentTermMatrix extends DocumentTermMatrix {
    private DocumentTermMatrix rootDtm;
    private int[] index;

    protected SubDocumentTermMatrix(DocumentTermMatrix parentDtm, int[] index) {
        if (parentDtm instanceof SubDocumentTermMatrix) {
            this.rootDtm = ((SubDocumentTermMatrix) parentDtm).rootDtm;
        } else {
            this.rootDtm = parentDtm;
        }
        this.index = index;
    }

    @Override
    public int[] getIndex() {
        return index;
    }
}

