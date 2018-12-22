package org.xmath.stats.distribution;

import org.apache.commons.math3.special.Beta;
import org.xmath.stats.Quantiles;
import org.xmath.stats.Tables;

public class FisherDistribution implements Distribution {

    private final int df1, df2;

    public FisherDistribution(int df1, int df2) {
        this.df1 = df1;
        this.df2 = df2;
    }

    @Override
    public double p(final double x) {
        double first, second, third, fourth;
        first = 1/ Math.exp(Beta.logBeta(df1 /2d, df2 /2d));
        second = Math.pow((df1 /(double) df2), df1 /2D);
        third = Math.pow(x, (df2 /2D)-1);
        fourth = Math.pow(1 + (df1 /(double) df2)*x, -(df1 + df2)/2d);
        return first*second*third*fourth;
    }

    @Override
    public double d(final double x) {
        double index = (df1 *x)/(df1 *x + df2);
        return Beta.regularizedBeta(index, df1 /2D, df2 /2D);
    }

    @Override
    public double quantile(final double alpha) {
        return 0;
    }

    @Override
    public double quantile(final Quantiles q) {
        switch (q){
            case q95: return Tables.fisherq95Lookup(df1, df2);
        }
        return 0;
    }

    @Override
    public double sample() {
        throw new UnsupportedOperationException("Cannot sample fro Isher distribution");
    }

    @Override
    public double mean() {
        if (df1 <= 2) return Double.NaN;
        return df2 / (double) (df2 - 2);
    }

    @Override
    public double var() {
        if (df1 <= 4) return Double.NaN;
        double num, denum;
        num =2* df2 * df2 *(df1 + df2 -2);
        denum = df1 *(df2 -2)*(df2 -2)*(df2 -4);
        return num / denum;
    }
}
