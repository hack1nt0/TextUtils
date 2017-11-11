package jni.cuda;

/**
 * Created by dy on 17-10-25.
 */
public class CuMatrix {
    static {
        System.loadLibrary("jni_cuda_matrix");
    }
    long pointer;
    int row, col;
    boolean needFree = false;

    public void destruct() {
        if (needFree) destruct(pointer);
    }

    private native void destruct(long pointer);

    public CuMatrix(int row, int col) {
        pointer = init(row, col);
        this.row = row;
        this.col = col;
        this.needFree = true;
    }

    private native long init(int row, int col);

    private CuMatrix(long cPointer, int row, int col) {
        this.pointer = cPointer;
        this.row = row;
        this.col = col;
    }

    public CuMatrix add(CuMatrix o) {
        return new CuMatrix(add(pointer, o.pointer), row, col);
    }

    private native long add(long pointerA, long pointerB);

    public CuMatrix add(double o) {
        return new CuMatrix(addDouble(pointer, o), row, col);
    }

    private native long addDouble(long pointerA, double o);


    public CuMatrix pow(double p) {
        return new CuMatrix(pow(pointer, p), row, col);
    }

    private native long pow(long pointer, double p);

    public CuMatrix sqrt() {
        return new CuMatrix(sqrt(pointer), row, col);
    }

    private native long sqrt(long pointer);

    public double sum() {
        return sum(pointer);
    }

    private native double sum(long pointer);

    @Override
    public String toString() {
        return "CuMatrix{" +
                "pointer=0x" + Long.toHexString(pointer) +
                '}';
    }

}
