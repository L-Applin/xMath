package org.xmath.num;

public class DoubleNumber implements Operations {

    private final double value;
    public Double value() { return value; }

    public DoubleNumber(double value) { this.value = value; }
    public DoubleNumber(int value) { this.value = (double) value; }


    @Override
    @SuppressWarnings({"unchecked"})
    public DoubleNumber add(Operations other) {
        return new DoubleNumber(value + ((DoubleNumber) other).value);
    }
    public DoubleNumber add(double other) {
        return new DoubleNumber(value + other);
    }


    @Override
    @SuppressWarnings({"unchecked"})
    public DoubleNumber mult(Operations other) {
        if (!(other instanceof DoubleNumber)) { throw new IllegalArgumentException("Can only multiply Doubles"); }
        return new DoubleNumber(value * ((DoubleNumber) other).value);
    }

    public DoubleNumber mult(double other) {
        return new DoubleNumber(value * other);
    }


    @Override
    @SuppressWarnings({"unchecked"})
    public DoubleNumber div(Operations other) {
        if (!(other instanceof DoubleNumber)) { throw new IllegalArgumentException("Can only multiply Doubles"); }
        if (((DoubleNumber) other).value == 0) { throw new ArithmeticException("Division by zero"); }
        return new DoubleNumber(value / ((DoubleNumber) other).value);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public DoubleNumber pow(Operations other) {
        if (!(other instanceof DoubleNumber)) { throw new IllegalArgumentException("Can only multiply Doubles"); }
        return new DoubleNumber(Math.pow(value, ((DoubleNumber) other).value));
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public DoubleNumber sqrt() {
        return new DoubleNumber(Math.sqrt(value));
    }


    @Override
    public String toString() {
        return ""+value;
    }
}
