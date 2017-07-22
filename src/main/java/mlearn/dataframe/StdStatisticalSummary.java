package mlearn.dataframe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-5-11.
 */
public class StdStatisticalSummary {
    private double[] sortedXs;
    private double minValue = Double.MAX_VALUE;
    private double maxValue = Double.MIN_VALUE;
    private double meanValue;
    private double medianValue;
    private double varianceValue;
    private double sdValue;
    private double sumValue;
    private double firstQua;
    private double thirdQua;
    private int nas;

    private StdStatisticalSummary() {}

    public static StdStatisticalSummary of(double[] doubleFrame) {
        StdStatisticalSummary summary = new StdStatisticalSummary();
        double[] xs = doubleFrame;
        int n = xs.length;
        if (n == 0) return summary;
        for (double x : xs) {
            if (x == Double.NaN) summary.nas++;
            summary.sumValue += x;
            summary.minValue = Math.min(summary.minValue, x);
            summary.maxValue = Math.max(summary.maxValue, x);
        }
        summary.meanValue = summary.sumValue / xs.length;
        summary.sortedXs = xs.clone();
        Arrays.parallelSort(summary.sortedXs);
        if (xs.length % 2 == 0) {
            summary.medianValue = (summary.sortedXs[xs.length / 2] + summary.sortedXs[xs.length / 2 + 1]) / 2;
        } else {
            summary.medianValue = summary.sortedXs[xs.length / 2];
        }
        summary.firstQua = summary.sortedXs[(int)(xs.length * 0.25)];
        summary.thirdQua = summary.sortedXs[(int)(xs.length * 0.75)];
        for (double x : xs) {
            if (x == Double.NaN) continue;
            summary.varianceValue += (x - summary.meanValue) * (x - summary.meanValue);
        }
        summary.varianceValue /= xs.length;
        summary.sdValue = Math.sqrt(summary.varianceValue);
        return summary;
    }

    public double getMin() {
        return minValue;
    }

    public double getMax() {
        return maxValue;
    }

    public double getMean() {
        return meanValue;
    }

    public double getMedian() {
        return medianValue;
    }

    public double getFirstQua() {
        return firstQua;
    }

    public double getThirdQua() {
        return thirdQua;
    }

    public double getVariance() {
        return varianceValue;
    }

    public double getStandardDeviation() {
        return sdValue;
    }

    public int getNAs() {
        return nas;
    }

    @Override
    public String toString() {
        return "StdStatisticalSummary{" +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", meanValue=" + meanValue +
                ", medianValue=" + medianValue +
                ", varianceValue=" + varianceValue +
                ", sdValue=" + sdValue +
                ", sumValue=" + sumValue +
                ", firstQua=" + firstQua +
                ", thirdQua=" + thirdQua +
                ", nas=" + nas +
                '}';
    }
}
