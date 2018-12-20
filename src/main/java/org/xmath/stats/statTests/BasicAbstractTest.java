package org.xmath.stats.statTests;

import org.xmath.stats.Quantiles;
import org.xmath.stats.distribution.Distribution;
import org.xmath.stats.intervals.ConfidenceInterval;

public abstract class BasicAbstractTest implements StatTest {

    protected final double valueTested;
    protected final Quantiles testLevel;

    protected Double statValue;
    protected Distribution testDistribution;
    protected ConfidenceInterval interval;

    public BasicAbstractTest(double valueTested, Quantiles testLevel) {
        this.valueTested = valueTested;
        this.testLevel = testLevel;
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
        return interval;
    }
    public Quantiles testLevel() {
        return testLevel;
    }

}
