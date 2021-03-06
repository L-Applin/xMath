package org.xmath.stats.statTests;

import org.xmath.stats.intervals.ConfidenceInterval;
import org.xmath.stats.Quantiles;
import org.xmath.stats.Sample;
import org.xmath.stats.distribution.Distribution;

public interface StatTest {

    /**
     * The value of the test Statistic.
     * @return
     */
    double testStatistic();


    /**
     * parameters oif the distribution of the test
     * @return
     */
    Distribution test();


    /**
     * The critical value calculated of the test
     * @return
     */
    default double pValue(){
        return 1 - test().d(testStatistic());
    }


    /**
     * A confidence interval of the value to be tested as specified
     * by the alternative hypothesis
     * @return
     */
    ConfidenceInterval confInterval();

    /**
     * The estimate value used for the test.
     * @return
     */
    double estimate();


    /**
     * A string describing the null hypothesis
     * @return
     */
    String nullHypothesis();


    /**
     * A String describing the alternative hypothesis
     * @return
     */
    String altHypothesis();


    /**
     * The sample used to enerate the test.
     * @return
     */
    Sample sample();


    /**
     * If the test has rejected the null hypothesis.
     * @return
     */
    default boolean reject(){
        return testStatistic() > test().quantile(testLevel());
    }

    /**
     *
     * @return
     */
    Quantiles testLevel();


    /**
     * Calculate the test based on the sample provided
     * @param sample
     */
    void fit(Sample sample);


}
