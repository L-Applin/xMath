package org.xmath.stats.statTests;

import org.xmath.stats.Interval;
import org.xmath.stats.Sample;
import org.xmath.stats.distribution.Distribution;

public class NormalityDistributionStatTest implements StatTest {

    @Override
    public double testStatistic() {
        return 0;
    }

    @Override
    public Distribution test() {
        return null;
    }

    @Override
    public double pValue() {
        return 0;
    }

    @Override
    public Interval confInterval() {
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
    public boolean reject() {
        return false;
    }

    @Override
    public void fit(Sample sample) {

    }
}
