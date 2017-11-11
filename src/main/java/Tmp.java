import template.debug.PrintWriterUTF8;
import template.debug.RandomUtils;
import template.debug.ScannerUTF8;
import template.debug.Stopwatch;

import java.io.*;

/**
 * @author dy[jealousing@gmail.com] on 17-7-18.
 */
public class Tmp {
    public static void main(String[] args) throws Exception {
        int n = 12000;
        double[] xs = new double[n / 2 * n];
        for (int i = 0; i < xs.length; ++i) xs[i] = RandomUtils.uniform();
        Stopwatch timer = new Stopwatch();
        timer.start();
        PrintWriter out = new PrintWriter(new FileOutputStream("data/tmp.in"));
        for (int i = 0; i < xs.length; ++i) {
            out.println(xs[i]);
        }
        out.close();
        timer.stop();
        System.out.println("hello\\tworld");
    }
}
