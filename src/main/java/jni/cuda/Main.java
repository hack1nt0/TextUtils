package jni.cuda;

/**
 * Created by dy on 17-10-25.
 */
public class Main {
    public static void main(String[] args) {
        CuMatrix A = new CuMatrix(1, 1);
        System.out.println(A);
        System.out.println(A.sum());
        CuMatrix B = A.sqrt().sqrt();
        System.out.println(B);
        System.out.println(B.sum());
        CuMatrix s = A.add(B);
        System.out.println(s);
        System.out.println(s.sum());
        A.destruct();
        B.destruct();
        s.destruct();
    }
}

