package org.xmath.stats.distribution;

import org.apache.commons.math3.special.Beta;
import org.xmath.stats.Quantiles;
import org.xmath.stats.Tables;
import static org.xmath.stats.distribution.Distributions.*;


public class StudentDistribution implements Distribution {

    private int degreeOfFreedom;

    public StudentDistribution(int degreeOfFreedom) {

        if (degreeOfFreedom < 1)
        { throw new IllegalArgumentException("Student distribution must have at least one degree of freedom"); }

        this.degreeOfFreedom = degreeOfFreedom;

    }

    @Override
    public double p(double x) {
        double num = gamma((degreeOfFreedom + 1)/2d);
        double denum = Math.sqrt(degreeOfFreedom * Math.PI) * gamma(degreeOfFreedom / 2d);
        double base = 1 + ((x*x / (double) degreeOfFreedom));
        double exponent = - (degreeOfFreedom + 1)/2d;
        return (num / denum) * Math.pow(base, exponent);
    }

    @Override
    /**
     * Copied from org.apache.commons.math3.distribution.TDistribution
     */
    public double d(double x) {
        double ret;
        if (x == 0.0D) {
            ret = 0.5D;
        } else {
            double t = Beta.regularizedBeta(degreeOfFreedom / (degreeOfFreedom + x * x), 0.5D * degreeOfFreedom, 0.5D);
            if (x < 0.0D) {
                ret = 0.5D * t;
            } else {
                ret = 1.0D - 0.5D * t;
            }
        }

        return ret;

    }

    @Override
    public double quantile(double alpha) {
        if (alpha>1||alpha<0) throw new RuntimeException("Probability must bebetween 0 and 1");
        return 0;
    }

    @Override
    public double quantile(Quantiles q) {
        switch (q) {
            case q90:  return Tables.studentLookup(degreeOfFreedom, 0);
            case q95:  return Tables.studentLookup(degreeOfFreedom, 1);
            case q975: return Tables.studentLookup(degreeOfFreedom, 2);
            case q99:  return Tables.studentLookup(degreeOfFreedom, 3);
            case q995: return Tables.studentLookup(degreeOfFreedom, 4);
            case q999: return Tables.studentLookup(degreeOfFreedom, 5);
            default:   return Tables.studentLookup(degreeOfFreedom, 2);
        }
    }

    @Override
    public double sample() {
        // todo
        return Double.NaN;
    }

    @Override
    public double mean() {
        if (degreeOfFreedom == 1) return Double.NaN;
        return 0;
    }

    @Override
    public double var() {
        if (degreeOfFreedom == 1) return Double.NaN;
        if (degreeOfFreedom == 2) return Double.POSITIVE_INFINITY;
        return degreeOfFreedom / (double) (degreeOfFreedom - 2);
    }

    @Override
    public String toString() {
        return "Student Distribution " + "(df=" + degreeOfFreedom+")";
    }
}
