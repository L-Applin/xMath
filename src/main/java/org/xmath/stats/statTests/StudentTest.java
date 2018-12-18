package org.xmath.stats.statTests;

import org.xmath.stats.Interval;
import org.xmath.stats.Intervals;
import org.xmath.stats.Quantiles;
import org.xmath.stats.Sample;
import org.xmath.stats.distribution.Distribution;
import org.xmath.stats.distribution.Distributions;

/**
 * H0 : mu = x
 * H1 : mu != x
 *
 */
public class StudentTest implements StatTest {

    private final double meanTested;
    private final Quantiles testLevel;

    private Sample sample;

    private Double statValue;
    private Distribution testDistribution;
    private Interval confidenceInterval;
    private double sampleMean;


    public StudentTest(double meanTested, Quantiles testLevel) {
        this.meanTested = meanTested;
        this.testLevel = testLevel;
    }

    public StudentTest(double meanTested) {
        this(meanTested, Quantiles.q95);
    }

    public StudentTest(Quantiles testLevel) {
        this(0, testLevel);
    }



    public StudentTest() {
        this(0, Quantiles.q95);
    }


    public void fit(Sample sample) {
        this.sample = sample;
        testDistribution = Distributions.student(sample.size() - 1);
        statValue = (Math.sqrt(sample.size()) / sample.std()) * Math.abs(sample.mean() - meanTested);
        confidenceInterval = Intervals.meanInterval(sample);
        sampleMean = sample.mean();
    }

    public double testStatistic(){
        return statValue;
    }

    public boolean reject(){
        return testStatistic() > test().quantile(testLevel);
    }

    public Distribution test() {
        return testDistribution;
    }

    public double pValue() {
        return testLevel.getAlpha();
    }

    public Interval confInterval() {
        return confidenceInterval;
    }

    public double estimate() {
        return sampleMean;
    }

    public String nullHypothesis() {
        return String.format("null hypothesis (H0): mu = %s", meanTested);
    }

    public String altHypothesis() {
        return String.format("null hypothesis (H1): mu != %s", meanTested);
    }

    public Sample sample() {
        return sample;
    }

    @Override
    public String toString() {
        return "StudentTest{\n" +
                "meanTested=" + meanTested +
                ", testLevel=" + testLevel +
                ", statValue=" + statValue +
                ", testDistribution=" + testDistribution +
                ", confidenceInterval=" + confidenceInterval +
                ", sampleMean=" + sampleMean +
                "\n" + sample +
                '}';
    }
}
