package org.xmath.stats.statTests;

import org.xmath.stats.Quantiles;
import org.xmath.stats.Sample;
import org.xmath.stats.distribution.Distribution;
import org.xmath.stats.intervals.ConfidenceInterval;

public class FisherTest implements StatTest {

    @Override
    public double testStatistic() {
        return 0;
    }

    @Override
    public Distribution test() {
        return null;
    }

    @Override
    public ConfidenceInterval confInterval() {
        return null;
    }

    @Override
    public double estimate() {
        return 0;
    }

    @Override
    public String nullHypothesis() {
        return null;
    }

    @Override
    public String altHypothesis() {
        return null;
    }

    @Override
    public Sample sample() {
        return null;
    }

    @Override
    public Quantiles testLevel() {
        return null;
    }

    @Override
    public void fit(Sample sample) {

    }
}
