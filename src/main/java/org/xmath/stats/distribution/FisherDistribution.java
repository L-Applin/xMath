package org.xmath.stats.distribution;

import org.apache.commons.math3.special.Beta;
import org.xmath.stats.Quantiles;

public class FisherDistribution implements Distribution {

    private final int d1, d2;

    public FisherDistribution(int d1, int d2) {
        this.d1 = d1;
        this.d2 = d2;
    }

    @Override
    public double p(final double x) {
        double first, second, third, fourth;
        first = 1/ Math.exp(Beta.logBeta(d1/2d, d2/2d));
        second = Math.pow((d1/(double)d2), d1/2D);
        third = Math.pow(x, (d2/2D)-1);
        fourth = Math.pow(1 + (d1/(double)d2)*x, -(d1+d2)/2d);
        return first*second*third*fourth;
    }

    @Override
    public double d(final double x) {
        double index = (d1*x)/(d1*x + d2);
        return Beta.regularizedBeta(index, d1/2D, d2/2D);
    }

    @Override
    public double quantile(final double alpha) {
        return 0;
    }

    @Override
    public double quantile(final Quantiles q) {
        return 0;
    }

    @Override
    public double sample() {
        return 0;
    }

    @Override
    public double mean() {
        if (d1 <= 2) return Double.NaN;
        return d2 / (double) (d2 - 2);
    }

    @Override
    public double var() {
        if (d1 <= 4) return Double.NaN;
        double num, denum;
        num =2*d2*d2*(d1+d2-2);
        denum = d1*(d2-2)*(d2-2)*(d2-4);
        return num / denum;
    }
}
