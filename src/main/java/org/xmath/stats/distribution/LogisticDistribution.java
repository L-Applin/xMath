package org.xmath.stats.distribution;

import org.xmath.stats.Quantiles;

public class LogisticDistribution implements Distribution {

    private final double mu, s;

    public LogisticDistribution(double mu, double s) {
        this.mu = mu;
        this.s = s;
    }

    @Override
    public double p(double x) {
        return 1 / (4*s) * Math.pow(1/Math.cosh((x-mu) / (2*s)), 2);
    }

    @Override
    public double d(double x) {
        double denum = Math.exp(-(x - mu)/s);
        return 1 / (1 + denum);
    }

    @Override
    public double quantile(double alpha) {
        if (alpha>1||alpha<0) throw new RuntimeException("Probability must bebetween 0 and 1");
        return mu + s * Math.log(alpha / (1 - alpha));
    }

    @Override
    public double quantile(Quantiles q) {
        return quantile(q.getAlpha());
    }

    @Override
    public double sample() {
        return quantile(Math.random());
    }

    @Override
    public double mean() {
        return 0;
    }

    @Override
    public double var() {
        return s*s*Math.PI*Math.PI/3D;
    }

}
