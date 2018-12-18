package org.xmath.num;

import java.util.Objects;

public class IntegerNumber implements Operations {

    private final int value;
    public Integer value() { return value; }

    public IntegerNumber (int value)     { this.value = value; }

    public IntegerNumber (double value)  { this.value = (int) value; }
    public IntegerNumber (float value)   { this.value = (int) value; }
    public IntegerNumber (long value)    { this.value = (int) value; }
    public IntegerNumber (short value)   { this.value = (int) value; }
    public IntegerNumber (byte value)    { this.value = (int) value; }


    @Override
    @SuppressWarnings({"unchecked"})
    public IntegerNumber add(Operations other) {
        return new IntegerNumber(value + ((IntegerNumber) other).value);
    }

    @SuppressWarnings({"unchecked"})
    public IntegerNumber add(int other) {
        return new IntegerNumber(value + other);
    }


    @Override
    @SuppressWarnings({"unchecked"})
    public IntegerNumber mult(Operations other) {
        if (!(other instanceof IntegerNumber)) { throw new IllegalArgumentException("Can only multiply Doubles"); }
        return new IntegerNumber(value * ((IntegerNumber) other).value);
    }

    @SuppressWarnings({"unchecked"})
    public IntegerNumber mult(int other) {
        return new IntegerNumber(value * other);
    }


    @Override
    @SuppressWarnings({"unchecked"})
    public IntegerNumber div(Operations other) {
        if (!(other instanceof IntegerNumber))  { throw new IllegalArgumentException("Can only multiply Doubles"); }
        if (((IntegerNumber) other).value == 0) { throw new ArithmeticException("Division by zero"); }
        return new IntegerNumber(value / ((IntegerNumber) other).value);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public IntegerNumber pow(Operations other) {
        if (!(other instanceof IntegerNumber)) { throw new IllegalArgumentException("Can only multiply Doubles"); }
        return new IntegerNumber((int) Math.pow(value, ((IntegerNumber) other).value));
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public DoubleNumber sqrt() {
        return new DoubleNumber(Math.sqrt(value));
    }

    public DoubleNumber toDouble(){
        return new DoubleNumber(value);
    }

    @Override
    public String toString() {
        return ""+value;
    }

    @Override @SuppressWarnings("all")
    public boolean equals(Object o) {
        if (o instanceof Integer){ return ((Integer) o) == value; }
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerNumber that = (IntegerNumber) o;
        return value == that.value;
    }

    public boolean equals(int o) {
        return o == value;
    }


    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static IntegerNumber i(int i){
        return new IntegerNumber(i);
    }
}
