package org.xmath.linearAlgebra;

import org.xmath.num.DoubleNumber;
import org.xmath.num.IntegerNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class DoubleVector implements IVector<DoubleNumber> {

    protected final double[] values;


    public DoubleVector(double... values) {
        this.values = values;
    }


    @Override
    public DoubleNumber get(int i) {
        return new DoubleNumber(values[i]);
    }

    @Override
    public DoubleNumber sum() {
        double sum = 0;
        for (int i = 0; i <values.length ; i++) {
            sum+=values[i];
        }
        return new DoubleNumber(sum);
    }

    @Override
    public DoubleNumber scalarProduct(IVector<DoubleNumber> other) {
        return null;
    }

    @Override
    public DoubleVector addition(IVector<DoubleNumber> other) {
        double[] clone = values.clone();
        if (other == null)                             { throw new IllegalArgumentException("Vectors must not be null"); }
        if (values.length != other.toArray().length)   { throw new IllegalArgumentException("Vectors must be of same size"); }

        for (int i = 0; i < clone.length; i++) {
            clone[i] = other.get(i).value() + values[i];
        }
        return new DoubleVector(clone);

    }

    @Override
    public IVector<DoubleNumber> mult(DoubleNumber other) {
        double[] clone = values.clone();
        if (other == null)                             { throw new NullPointerException("Multiplication with null element"); }

        for (int i = 0; i < clone.length; i++) {
            clone[i] = clone[i] * other.value();
        }
        return new DoubleVector(clone);
    }

    public IVector<DoubleNumber> mult(int other) {
        double[] clone = values.clone();
        for (int i = 0; i < clone.length; i++) {
            clone[i] = clone[i] * other;
        }
        return new DoubleVector(clone);
    }


    @Override
    public IVector<DoubleNumber> crossProd(IVector<DoubleNumber> other) {
        //todo
        return null;
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public boolean isEmpty() {
        return values.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return new ArrayList<>(Arrays.asList(values)).contains(o);
    }


    @Override
    public Iterator<DoubleNumber> iterator() {
        ArrayList<Double> tmp = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            tmp.add(values[i]);
        }
        return tmp.stream().map(DoubleNumber::new).iterator();
    }

    @Override
    public DoubleNumber[] toArray() {
        DoubleNumber[] arr = new DoubleNumber[values.length];
        for (int i = 0; i < values.length; i++) {
            arr[i] = new DoubleNumber(values[i]);
        }
        return arr;
    }

    public double[] toDoubleArray() {
        return Arrays.copyOf(values, values.length);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) toArray();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return new ArrayList<>(Arrays.asList(values)).containsAll(c);
    }

    public boolean add       (DoubleNumber t)                       { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public boolean remove    (Object o)                              { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public boolean addAll    (Collection<? extends DoubleNumber> c) { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public boolean removeAll (Collection<?> c)                       { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public boolean retainAll (Collection<?> c)                       { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public void    clear     ()                                      { throw new UnsupportedOperationException("Cannot change immutable Vector"); }

    @Override
    public String toString() {
        var sb =new StringBuilder();
        sb.append("[ ");
        for (Double value : values){
            sb.append(value.toString()).append(" ");
        }
        return sb.append("]").toString();
    }

}
