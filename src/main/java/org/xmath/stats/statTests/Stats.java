package org.xmath.stats.statTests;

import org.xmath.stats.Quantiles;
import org.xmath.stats.Sample;

public class Stats {

    public static StatTest normalTest(){
        return null;
    }

    public static StatTest t_test(){
        return new StudentTest();
    }

}
