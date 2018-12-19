package org.xmath.stats;

import org.xmath.stats.distribution.Distribution;
import org.xmath.stats.distribution.Distributions;

import static org.xmath.stats.distribution.Distributions.*;

public final class Generators {

    public static Sample generate(int amount, Distribution dist){
        Generator gen = new Generator(dist);
        return new Sample(gen.random(amount));
    }

    /**
     * A sample that is normally distributed that has a Standard Normal distribution. Mean is 0 and std is 1.
     * @param amount the number of individual iid observation te be generated.
     * @return
     */
    public static Sample normal(int amount){
        return generate(amount, Z);
    }

    public static Sample normal(int amount, double mu, double sigma){
        double[] data = new Generator(Z).random(amount);
        for (int i = 0; i < data.length; i++) {
            data[i] = (data[i] * sigma) + mu;
        }
        return new Sample(data);
    }

    public static void normal(double[] tab, double mu, double sigma){
        double[] data = new Generator(Z).random(tab.length);
        for (int i = 0; i < tab.length; i++) {
            tab[i] = (data[i] * sigma) + mu;
        }
    }

    /**
     * A sample that is uniformely distributed between 0 and 1
     * @param amount
     * @return
     */
    public static Sample uniform(int amount){
        return generate(amount, U);
    }

    public static Sample uniform(int amount, double low, double high){
        return generate(amount, Distributions.uniform(low, high));
    }

    public static void uniform(double[] tab, double low, double high){
        double[] data = new Generator(Distributions.uniform(low, high)).random(tab.length);
        for (int i = 0; i < tab.length; i++) {
            tab[i] = data[i];
        }
    }

}
