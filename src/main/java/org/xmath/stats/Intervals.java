package org.xmath.stats;

public final class Intervals {

    public static Interval meanInterval(Sample sample){
        return new Interval(sample).meanInterval();
    }

    public static Interval meanInterval(Sample sample, Quantiles level){
        return new Interval(sample).meanInterval(level);
    }

}
