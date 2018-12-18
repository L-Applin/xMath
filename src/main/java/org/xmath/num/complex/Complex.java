package org.xmath.num.complex;

import org.xmath.num.Operations;

public class Complex implements Operations {

    private final double real, c;

    public double real() { return real; }
    public double complex() { return c; }

    private final double[] values;

    public Complex(double real, double c) {
        this.real = real; this.c = c;
        values = new double[]{real, c};
    }

    public Complex(double[] values) {
        this(values[0], values[1]);
    }

    public int square(){
        return -1;
    }

    public double[] asArray(){
        return values.clone();
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public Complex add(Operations other) {
        return new Complex(real + ((Complex) other).real, c + ((Complex) other).c);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public Complex mult(Operations other) {
        Complex otherComplex = (Complex) other;
        double newReal, newComplex;
        newReal = (real * otherComplex.real) - (c * otherComplex.c);
        newComplex = (real * otherComplex.c) + (c * otherComplex.real);
        return new Complex(newReal, newComplex);
    }

    public Complex mult(int n) {
        return new Complex(real * n, c * n);
    }


    public Complex mult(double d) {
        return new Complex(real * d, c * d);
    }


    @Override
    @SuppressWarnings({"unchecked"})
    public Complex div(Operations other) {
        //todo;
        return null;
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public Complex pow(Operations other) {
        //todo
        return null;
    }

    public Complex pow(int other) {
        //todo
        return null;
    }

    public Complex pow(double other) {
        //todo
        return null;
    }

    @Override
    public Complex sqrt() {
        //todo
        return null;
    }

    public double magnitude(){
        return Math.sqrt(Math.pow(real, 2) + Math.pow(c, 2));
    }
}
