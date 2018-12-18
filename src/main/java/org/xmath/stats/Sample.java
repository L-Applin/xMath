package org.xmath.stats;

import org.xmath.linearAlgebra.DoubleVector;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;

/**
 * Normally distributed data sample.
 * todo : test for normality, adjust in consequence
 */
public class Sample extends DoubleVector implements Observation {

    /** todo
     * CHEAT ! replace with own
     */
    private DoubleSummaryStatistics stat;

    private double[] orderStats;

    public Sample(double... values) {
        super(values);
        stat = Arrays.stream(values).summaryStatistics();
    }

    @Override
    public double mean() {
        return stat.getAverage();
    }

    @Override
    public double var() {
        double mean = mean();
        double diffs = Arrays.stream(values).reduce((acc, val) -> acc + Math.pow(val - mean, 2)).orElseGet(this::diffSquare);
        return diffs / (size() - 1);
    }


    @Override
    public double median() {
        // todo : linear pseudo-median algorithm ?
        double[] tmp = Arrays.copyOf(values, values.length);
        Arrays.sort(tmp);
        orderStats = tmp;
        int size = values.length;
        if (size % 2 == 0){
            return (values[size / 2] + values[(size/2)+ 1]) / 2d;
        } else {
            return values[(size/2)+ 1];
        }
    }

    @Override
    public double[] sorted() {
        if (orderStats == null) {
            double[] tmp = Arrays.copyOf(values, values.length);
            Arrays.sort(tmp);
            orderStats = tmp;
        }
        return Arrays.copyOf(orderStats, orderStats.length);

    }


    private double diffSquare(){
        double total = 0;
        double mean = mean();
        for (int i = 0; i < values.length; i++) {
            total += Math.pow(values[i] - mean, 2);
        }
        return total;
    }


    public Interval meanInterval(){
        return Intervals.meanInterval(this);
    }

    public double max(){
        return stat.getMax();
    }

    public double min(){
        return stat.getMin();
    }

    @Override
    public String toString() {
        return String.format("-=- Sample description -=- \nsize : %s\nmean : %.4f\nmedian : %.4f\nmax : %.4f ,  min : %.4f\n" +
                        "std deviation : %.4f\ntotal : %.4f\nMean interval (0.95) : %s",
                size(), mean(), median(), max(), min(), std(), sum().value(), meanInterval());
    }
}
