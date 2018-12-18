package org.xmath.stats;

import org.xmath.stats.distribution.Distribution;
import org.xmath.stats.distribution.Distributions;

public class Interval {

    private double low, high;
    private Sample sample;

    public Interval(Sample sample) {
        this.sample = sample;
    }

    /**
     * default level is 1 - alpha/2 = 0.975
     * @return the Interval of confidence for the mean of the sample.
     */
    public Interval meanInterval() {
        return meanInterval(Quantiles.q975);
    }

    public Interval meanInterval(double sigma) {
        return meanInterval(Quantiles.q975, sigma);
    }

    /**
     * Confidence intervall with unknown variance. Uses Student distribution to build the interval
     * @param level desired level of confidence 1 - alpha of the interval.
     * @return thecomputed interval
     */
    public Interval meanInterval(Quantiles level) {
        double mean = sample.mean();
        Distribution t = Distributions.student(sample.size() - 1);
        double interv = t.quantile(level) * sample.std() * (1 / Math.sqrt(sample.size()));
        low = mean - interv;
        high = mean + interv;
        return this;
    }

    public Interval meanInterval(Quantiles level, double sigma) {
        double mean = sample.mean();
        double interv = Distributions.Z.quantile(level) * sigma * (1 / Math.sqrt(sample.size()));
        low = mean - interv;
        high = mean + interv;
        return this;
    }




    @Override
    public String toString() {
        return String.format("[%.4f, %.4f]", low, high);
    }

}
