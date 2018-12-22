package org.xmath.stats.intervals;

public interface ConfidenceInterval {

    double error();
    double centralValue();

    default double low(){
        return centralValue() - error();
    };
    default double high(){
        return centralValue() + error();
    }

    default double[] interval(){
        return new double[]{low(), high()};
    }


}
