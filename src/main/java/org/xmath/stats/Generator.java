package org.xmath.stats;

import org.xmath.stats.distribution.Distribution;

class Generator {

    private Distribution randomGenerator;

    public Generator(Distribution randomGenerator) {
        this.randomGenerator = randomGenerator;
    }


    public double[] random(int amount){
        double[] res = new double[amount];
        for (int i = 0; i < res.length; i++) {
            res[i] = randomGenerator.sample();
        }
        return res;
    }

    public double random(){
        return randomGenerator.sample();
    }



}
