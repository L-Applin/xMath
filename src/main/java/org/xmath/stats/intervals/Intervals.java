package org.xmath.stats.intervals;

import org.xmath.stats.Quantiles;
import org.xmath.stats.Sample;

public final class Intervals {

    public static MeanInterval meanInterval(Sample sample){
        return new MeanInterval(sample).fit();
    }

    public static MeanInterval meanInterval(Sample sample, Quantiles level){
        return new MeanInterval(sample).fit(level);
    }

}
