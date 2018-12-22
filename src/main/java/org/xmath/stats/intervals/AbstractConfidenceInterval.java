package org.xmath.stats.intervals;

public abstract class AbstractConfidenceInterval implements ConfidenceInterval {
    protected double low, high, error, center;

    /**
     * Should set the values of low, high, error, center variables.
     * @return
     */
    protected abstract ConfidenceInterval fit();

    @Override
    public double error() {
        return error;
    }

    @Override
    public double centralValue() {
        return center;
    }

    @Override
    public double low() {
        return low;
    }

    @Override
    public double high() {
        return high;
    }

    @Override
    public double[] interval() {
        return new double[]{low, high};
    }

    @Override
    public String toString() {
        return String.format("[%.4f, %.4f]", low, high);
    }

}
