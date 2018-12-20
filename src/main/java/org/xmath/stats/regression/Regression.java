package org.xmath.stats.regression;

import org.xmath.stats.Sample;
import org.xmath.stats.intervals.ConfidenceInterval;
import org.xmath.stats.statTests.StatTest;

import java.util.List;

public interface Regression {

    Regression fit(Sample y);
    List<StatTest> betaTests();
    List<ConfidenceInterval> intervals();
    List<Double> coefficient();
    void summary();

}
