package org.xmath.stats.distribution;

import org.xmath.stats.Quantiles;

public class UniformDistribution implements Distribution {

    private final double low, high;

    public UniformDistribution(double low, double high) {
        this.low = low;
        this.high = high;
    }

    public UniformDistribution(double high) {
        this.low = 0;
        this.high = high;
    }

    public UniformDistribution() {
        this.low = 0;
        this.high = 1;
    }

    @Override
    public double p(double x) {
        if (x > high || x < low) return 0;
        return 1 / (high - low);
    }

    @Override
    public double d(double x) {
        if (x < low) return 0;
        if (x >= high) return 1;
        return (x - low) / (high - low);
    }


    @Override
    public double quantile(double alpha) {
        // todo
        return 0;
    }

    @Override
    public double quantile(Quantiles q) {
        return quantile(q.getAlpha());
    }

    @Override
    public double mean() {
        return 0.5 * (low + high) ;
    }

    @Override
    public double var() {
        return 1/12d * Math.pow(high - low, 2);
    }

    @Override
    public double sample() {
        return (Math.random() * (high - low)) + low;
    }


}
