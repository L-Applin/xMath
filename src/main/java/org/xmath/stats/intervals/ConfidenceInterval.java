package org.xmath.stats.intervals;

import org.xmath.stats.Quantiles;

public interface ConfidenceInterval {

    double error();
    double centralValue();
    ConfidenceInterval fit(Quantiles level);


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
