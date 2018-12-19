package org.xmath.stats.statTests;

import org.xmath.stats.*;
import org.xmath.stats.distribution.Distribution;
import org.xmath.stats.distribution.Distributions;
import org.xmath.stats.intervals.ConfidenceInterval;
import org.xmath.stats.intervals.Intervals;
import org.xmath.stats.intervals.MeanInterval;

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
    private MeanInterval confidenceMeanInterval;
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
        confidenceMeanInterval = Intervals.meanInterval(sample);
        sampleMean = sample.mean();
    }

    public double testStatistic(){
        return statValue;
    }

    public boolean reject(){
        return statValue > testDistribution.quantile(testLevel);
    }

    public Distribution test() {
        return testDistribution;
    }

    public ConfidenceInterval confInterval() {
        return confidenceMeanInterval;
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
    public Quantiles testLevel() {
        return testLevel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n");
        sb.append(nullHypothesis()).append("\n");
        sb.append(altHypothesis()).append("\n");
        sb.append(String.format("confidence interval : %s\n", confInterval()));
        sb.append(String.format("test statistic value : %.4f\n", testStatistic()));
        sb.append(String.format("p-value : %s\n", pValue()));
        sb.append(String.format("mean estimate : %.4f\n", estimate()));
        sb.append(String.format("reject null hypothesis : %s\n",reject()));
        sb.append(sample());
        return sb.toString();
    }
}
