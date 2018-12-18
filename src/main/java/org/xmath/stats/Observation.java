package org.xmath.stats;

public interface Observation {

    /**
     * The sample meanas defined by the
     * @return
     */
    double mean();

    int size();

    double median();

    double[] sorted();

    double var();

    default double std(){
        return Math.sqrt(var());
    }

}
