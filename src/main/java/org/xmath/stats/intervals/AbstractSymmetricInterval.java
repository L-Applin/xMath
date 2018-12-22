package org.xmath.stats.intervals;

public abstract class AbstractSymmetricInterval extends AbstractConfidenceInterval{

    protected abstract double calcCenter();
    protected abstract double calcError();

    @Override
    protected ConfidenceInterval fit() {
        error= calcError();
        center= calcCenter();
        low=center-error;
        high=center+error;
        return this;
    }
}
