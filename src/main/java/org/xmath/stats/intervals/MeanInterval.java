package org.xmath.stats.intervals;

import org.xmath.stats.Quantiles;
import org.xmath.stats.Sample;
import org.xmath.stats.distribution.Distribution;
import org.xmath.stats.distribution.Distributions;

public class MeanInterval extends AbstractConfidenceInterval{

    private Sample sample;

    public MeanInterval(Sample sample) {
        this.sample = sample;
    }

    /**
     * default level is 1 - alpha/2 = 0.975
     * @return the MeanInterval of confidence for the mean of the sample.
     */
    public MeanInterval fit() {
        return fit(Quantiles.q975);
    }

    public MeanInterval fit(double sigma) {
        return fit(Quantiles.q975, sigma);
    }

    /**
     * Confidence intervall with unknown variance. Uses Student distribution to build the interval
     * @param level desired level of confidence 1 - alpha of the interval.
     * @return thecomputed interval
     */
    public MeanInterval fit(Quantiles level) {
        center = sample.mean();
        Distribution t = Distributions.student(sample.size() - 1);
        error = t.quantile(level) * sample.std() * (1 / Math.sqrt(sample.size()));
        low = center - error;
        high = center + error;
        return this;
    }

    private MeanInterval fit(Quantiles level, double sigma) {
        center = sample.mean();
        error = Distributions.Z.quantile(level) * sigma * (1 / Math.sqrt(sample.size()));
        low = center - error;
        high = center + error;
        return this;
    }


}

