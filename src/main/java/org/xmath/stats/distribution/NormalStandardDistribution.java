package org.xmath.stats.distribution;

import org.xmath.stats.Quantiles;
import org.xmath.stats.Tables;

import java.util.function.UnaryOperator;

import static org.xmath.stats.distribution.Distributions.*;

public final class NormalStandardDistribution implements Distribution {

    private final UnaryOperator<Double> density = x -> Math.exp(-Math.pow(x, 2)/2) / Math.sqrt(2*Math.PI);
    private Double cachedNextGeneratedValue;

    @Override
    public double p(double x) {
        return density.apply(x);
    }


    @Override
    public double d(double x) {

        /* Note :
         * Because of the precision of the erf() function, extreme values greater than 4 (or les than -4)
         * will result in poor precision, and even response that are innaccurate. Use with caution.
         * The error function is approximated and the precision of the approximation has its limits.
         */
        return 0.5 * (1 + erf(x / Math.sqrt(2)));

    }

    @Override
    public double quantile(double alpha) {
        if (alpha>1||alpha<0) throw new RuntimeException("Probability must bebetween 0 and 1");

        // todo find something better : quantile functions
        double step = 0.0001;
        double test = 0.0001;
        while (d(test) < alpha){
            test += step;
        }
        return test;
    }


    /**
     * Box-Muller approximation
     */
    public double sample() {

        if (cachedNextGeneratedValue == null) {

            double first = Math.random();
            double second = Math.random();
            double a = Math.sqrt(-2 * Math.log(first));
            double b = 2 * Math.PI * second;
            cachedNextGeneratedValue = a*Math.cos(b);
            return a*Math.sin(b);
        }

        else {

            double res = cachedNextGeneratedValue;
            cachedNextGeneratedValue = null;
            return res;
        }

    }

    @Override
    public double mean() {
        return 0;
    }

    @Override
    public double var() {
        return 1;
    }

    @Override
    public double quantile(Quantiles q) {
        switch (q) {
            case q90: return Tables.stdNormalLookup(0);
            case q95: return Tables.stdNormalLookup(1);
            case q975: return Tables.stdNormalLookup(2);
            case q99: return Tables.stdNormalLookup(3);
            case q995: return Tables.stdNormalLookup(4);
            case q999: return Tables.stdNormalLookup(5);
            default: return Tables.stdNormalLookup(2);
        }

    }
}
