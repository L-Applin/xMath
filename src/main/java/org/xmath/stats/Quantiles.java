package org.xmath.stats;

public enum Quantiles {

    q90(0.9),    // bilateral alpha = 0.20
    q95(0.95),   // bilateral alpha = 0.10
    q975(0.975), // bilateral alpha = 0.05
    q99(0.99),   // bilateral alpha = 0.02
    q995(0.995), // bilateral alpha = 0.01
    q999(0.999); // bilateral alpha = 0.002

    private double alpha;
    Quantiles(double alpha){ this.alpha = alpha; }
    public double getAlpha() { return alpha; }
    public double getBillateralAlpha() { return alpha/2; }

}
