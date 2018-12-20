package org.xmath.stats.statTests;

import org.xmath.stats.*;
import org.xmath.stats.distribution.Distributions;
import org.xmath.stats.intervals.Intervals;

/**
 * H0 : mu = x
 * H1 : mu != x
 *
 */
public class StudentTest extends BasicAbstractTest {

    private Sample sample;
    private double sampleMean;


    public StudentTest(double meanTested, Quantiles testLevel) {
        super(meanTested, testLevel);
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
        statValue = (Math.sqrt(sample.size()) / sample.std()) * Math.abs(sample.mean() - valueTested);
        interval = Intervals.meanInterval(sample);
        sampleMean = sample.mean();
    }

    public double estimate() {
        return sampleMean;
    }

    public String nullHypothesis() {
        return String.format("null hypothesis (H0): mu = %s", valueTested);
    }

    public String altHypothesis() {
        return String.format("null hypothesis (H1): mu != %s", valueTested);
    }

    public Sample sample() {
        return sample;
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
