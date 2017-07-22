package mlearn;

/**
 * @author dy[jealousing@gmail.com] on 17-6-1.
 */
public class testND4j {
    public static void main(String[] args) {
//        int n = 1000, m = 1000, k = 1000;
//        double[][] A = new double[n][m];
//        double[][] B = new double[m][k];
//        Stopwatch.tic();
//        INDArray C = Nd4j.create(A).mul(Nd4j.create(B));
//        System.out.println(C.getDouble(100,100));
//        Stopwatch.toc();
//        Stopwatch.tic();
//        MatrixUtils.multiply(A, B);
//        Stopwatch.toc();
        int n = 1 << 20;
        int arr[] = new int[n];
        for (int i = 0; i < n; ++i) arr[i] = i + 1;

        long start = System.currentTimeMillis();
        int acc = 0;
        for (int i = 0; i < n; ++i) {
            acc += arr[i];
            arr[i] = acc;
            //cout << arr[i] << endl;
        }

        System.out.printf("%f s\n", (System.currentTimeMillis() - start) / 1000.);
    }
}
