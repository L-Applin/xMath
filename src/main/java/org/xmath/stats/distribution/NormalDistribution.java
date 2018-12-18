package org.xmath.stats.distribution;

import org.xmath.stats.Quantiles;

import static org.xmath.stats.distribution.Distributions.*;

public class NormalDistribution implements Distribution {

    private double mu, sigma;

    public NormalDistribution(double mu, double sigma) {
        this.mu = mu;
        this.sigma = sigma;
    }

    @Override
    public double p(double x) {
        return normalDensityFunction(mu, sigma).apply(x);
    }

    @Override
    public double d(double x) {
        double x1 = erf((x - mu)/(sigma * Math.sqrt(2)));
        return 0.5 * (1 + x1);
    }

    @Override
    public double quantile(double alpha) {
        return mu + sigma * SQRTPI * inverseErf(2 * alpha - 1);
    }

    @Override
    public double quantile(Quantiles q) {
        return quantile(q.getBillateralAlpha());
    }

    @Override
    public double sample() {
        return (Z.sample() * sigma) + mu;
    }

    @Override
    public double mean() {
        return mu;
    }

    @Override
    public double var() {
        return Math.pow(sigma, 2);
    }
}
