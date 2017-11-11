package jni.cuda;

/**
 * Created by dy on 17-10-25.
 */
public class CuDenseExpr {

    static CuDenseExpr add(CuDenseExpr a, CuDenseExpr... bs) {
        CuDenseExpr sum = a;
        for (CuDenseExpr b : bs) sum = sum.add(b);
        return sum;
    }


}
