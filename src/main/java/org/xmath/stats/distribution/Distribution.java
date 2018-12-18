package org.xmath.stats.distribution;

import org.xmath.stats.Quantiles;

/**
 * A continous probability density fucntion representing a random variable.
 */
public interface Distribution {

    /**
     * @return The density fonction value evaluated at x
     */
    double p(double x);

    /**
     * @return the cumulative denstity value evaluated at x
     */
    double d(double x);

    /**
     * @return the probability value between 'low' and 'high' to of the density fo
     */
    default double prob(double low, double high){
        return d(high) - d(low);
    }

    /**
     *
     * @param alpha
     * @return The quantile value (P[F < prob]) at the alpha value
     */
    double quantile(double alpha);
    double quantile(Quantiles q);


    /**
     * @return returns a single sample value form the distribution
     */
    double sample();


    /**
     * The actuall true mean (esperance) of the distribution
     * @return
     */
    double mean();


    /**
     * The actual true variance (std squared) of the distribution
     * @return
     */
    double var();

}
