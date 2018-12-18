package org.xmath.stats.distribution;

import org.xmath.stats.Quantiles;
import org.xmath.stats.Tables;
import static org.xmath.stats.distribution.Distributions.*;


public class StudentDistribution implements Distribution {

    private int degreeOfFreedom;
    public final double q90, q95, q975, q99, q995, q999;

    public StudentDistribution(int degreeOfFreedom) {

        if (degreeOfFreedom < 1)
        { throw new IllegalArgumentException("Student distribution must have at least one degree of freedom"); }

        this.degreeOfFreedom = degreeOfFreedom;

        q90  = Tables.studentLookup(degreeOfFreedom, 0);
        q95  = Tables.studentLookup(degreeOfFreedom, 1);
        q975 = Tables.studentLookup(degreeOfFreedom, 2);
        q99  = Tables.studentLookup(degreeOfFreedom, 3);
        q995 = Tables.studentLookup(degreeOfFreedom, 4);
        q999 = Tables.studentLookup(degreeOfFreedom, 5);

    }

    @Override
    public double p(double x) {
        double num = gamma((degreeOfFreedom + 1)/2d);
        double denum = Math.sqrt(degreeOfFreedom * Math.PI) * gamma(degreeOfFreedom / 2d);
        double base = 1 + ((Math.pow(x, 2) / (double) degreeOfFreedom));
        double exponent = - (degreeOfFreedom + 1)/2d;
        return (num / denum) * Math.pow(base, exponent);
    }

    @Override
    public double d(double x) {
        // slice approach ?
        return 0;
    }

    @Override
    public double quantile(double alpha) {
        // todo
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
