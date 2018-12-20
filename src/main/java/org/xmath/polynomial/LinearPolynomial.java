package org.xmath.polynomial;

public class LinearPolynomial {

    private double[] coefficient;

    public LinearPolynomial(double[] coefficient) {
        this.coefficient = coefficient;
    }

    public double eval(double[] values){
        if (values.length - 1 != coefficient.length){
            throw new IllegalArgumentException("Wrong number of values to evaluate Linear Polynomial");
        }

        double total = 0;
        for (int i = 0; i < values.length; i++) {
            total += coefficient[i+1] * values[i];
        }
        return total;
    }
}
