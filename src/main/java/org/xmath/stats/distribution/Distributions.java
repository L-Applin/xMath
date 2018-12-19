package org.xmath.stats.distribution;

import org.apache.commons.math3.distribution.HypergeometricDistribution;
import org.apache.commons.math3.special.Erf;

import java.util.function.UnaryOperator;

import static org.xmath.stats.Tables.erfInverseCoeff;


/**
 * Entry point class for all Probability Distributions used in the library.
 */
public final class Distributions {

    private Distributions() throws IllegalAccessException
    { throw new IllegalAccessException("Unallowed to instanciate static class Distributions"); }

    public static final double SQRTPI = Math.sqrt(Math.PI);

    /**
     * A Standrard normal distribution (centered at 0 with variance = 1)
     */
    public static final NormalStandardDistribution Z;
    static {
        Z = new NormalStandardDistribution();
    }

    /**
     * A uniform distribution in range (0, 1)
     */
    public static final UniformDistribution U;
    static {
        U = new UniformDistribution();
    }

    /**
     * A Student distribution with its degree of freedom.
     * @param df degree of freedom of the student distribution
     */
    public static StudentDistribution student(int df){
        return new StudentDistribution(df);
    }

    /**
     * A normal (Gaussian) distribution
     * @param mu
     * @param sigma
     * @return
     */
    public static NormalDistribution normal(double mu, double sigma){
        return new NormalDistribution(mu, sigma);
    }


    /**
     * A low - high uniform distribution
     * @param low
     * @param high
     * @return
     */
    public static UniformDistribution uniform(double low, double high){
        return new UniformDistribution(low, high);
    }












    /**
     * A Function to calculate the distribution of a normal distribution
     * @param mu the mean
     * @param sigma the standard deviation (sqrt of the variance)
     * @return
     */
    public static UnaryOperator<Double> normalDensityFunction(double mu, double sigma){
        return x -> (1 / (sigma * Math.sqrt(2 * Math.PI))) * Math.exp(-(Math.pow(x - mu, 2))/(2*Math.pow(sigma, 2)));
    }

    /**
     * The error function, approximated through Taylor expansion
     */
    public static final UnaryOperator<Double> errorFunctionApprox =
            x -> (2 / Math.sqrt(Math.PI) *
                    (x - (Math.pow(x, 3)/3) + (Math.pow(x, 5)/10) - (Math.pow(x, 7)/42) + (Math.pow(x, 9)/216) - (Math.pow(x, 11)/1320) + (Math.pow(x, 13)/9360)));


    public static double erf(double z){
        return Erf.erf(z);
        // // extremem values => use better approximation
        // if (z <= 2 || z >= 2) {
        //     return erf(z, precisionRequired(z));
        // }
        // return errorFunctionApprox.apply(z);
    }

    private static double erf(double z, int precision){
        double sum = 0;
        for (int n = 0; n <= precision; n++) {
            double tmp = z / (2*n + 1);
            for (int k = 1; k <= n; k++) {
                double tmp2 = (-Math.pow(z, 2)/k);
                tmp *= tmp2;
            }
            sum += tmp;
        }
        double val = 2 * sum / Math.sqrt(Math.PI);
        // if (val < -1) return -1d;
        // if (val > 1) return 1d;
        return val;

    }

    private static int precisionRequired(double z){
        //todo
        return 100;
    }

    // https://introcs.cs.princeton.edu/java/91float/Gamma.java.html
    private static double logGamma(double x){
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173    / (x + 0)   - 86.50532033    / (x + 1)
                + 24.01409822    / (x + 2)   -  1.231739516   / (x + 3)
                +  0.00120858003 / (x + 4)   -  0.00000536382 / (x + 5);
        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
    }

    public static double gamma(double x) { return Math.exp(logGamma(x)); }


    /**
     * Inverse error function, approximated through MacLauren Series expansion. Uses only four terms
     * @param x value to evaluate
     * @return the result
     */
    public static double inverseErf(double x){
        //todo : cheat, use own function
        return Erf.erfInv(x);

        // if (x > 1 || x < -1) return Double.NaN;
        // double sum =
        //         0.5 * x + (1/24d) * Math.PI * Math.pow(x, 3)
        //                 + (7/960d) * Math.pow(Math.PI, 2) * Math.pow(x, 5)
        //                 + (127/80640d) * Math.pow(Math.PI, 3) * Math.pow(x, 7);
        // return Math.sqrt(Math.PI) * sum;
    }


    public static double preciseInverseErf(double x){
        double alpha = x * (SQRTPI / 2);
        double sum = 0;
        for (int i = 0; i < erfInverseCoeff.length; i++) {
            double coef = erfInverseCoeff[i];
            int pow = 2 * (i+1) - 1;
            sum += coef*Math.pow(alpha, pow);
        }
        return sum;
    }


}
