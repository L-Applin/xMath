package org.xmath.linearAlgebra;

import org.xmath.num.IntegerNumber;

import java.util.*;

/**
 * Basic immutable implementation of the {@link IVector} interface that ony supports integer elements.
 */
public class IntVector implements IVector <IntegerNumber> {

    protected final int[] values;

    public IntVector(int... values) {
        this.values = values;
    }


    @Override
    public IntegerNumber get(int i) {
        return new IntegerNumber(values[i]);
    }

    @Override
    public IntegerNumber sum() {
        int sum = 0;
        for (int i = 0; i <values.length ; i++) {
            sum+=values[i];
        }
        return new IntegerNumber(sum);
    }

    @Override
    public IntegerNumber scalarProduct(IVector<IntegerNumber> other) {
        return null;
    }

    @Override
    public IntVector addition(IVector<IntegerNumber> other) {
        int[] clone = values.clone();
        if (other == null)                             { throw new IllegalArgumentException("Vectors must not be null"); }
        if (values.length != other.toArray().length)   { throw new IllegalArgumentException("Vectors must be of same size"); }

        for (int i = 0; i < clone.length; i++) {
            clone[i] = other.get(i).value() + values[i];
        }
        return new IntVector(clone);

    }

    @Override
    public IVector<IntegerNumber> mult(IntegerNumber other) {
        int[] clone = values.clone();
        if (other == null)                             { throw new NullPointerException("Multiplication with null element"); }

        for (int i = 0; i < clone.length; i++) {
            clone[i] = clone[i] * other.value();
        }
        return new IntVector(clone);
    }

    public IVector<IntegerNumber> mult(int other) {
        int[] clone = values.clone();
        for (int i = 0; i < clone.length; i++) {
            clone[i] = clone[i] * other;
        }
        return new IntVector(clone);
    }


    @Override
    public IVector<IntegerNumber> crossProd(IVector<IntegerNumber> other) {
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
    public Iterator<IntegerNumber> iterator() {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            tmp.add(values[i]);
        }
        return tmp.stream().map(IntegerNumber::new).iterator();
    }

    @Override
    public IntegerNumber[] toArray() {
        IntegerNumber[] arr = new IntegerNumber[values.length];
        for (int i = 0; i < values.length; i++) {
            arr[i] = new IntegerNumber(values[i]);
        }
        return arr;
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

    public boolean add       (IntegerNumber t)                       { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public boolean remove    (Object o)                              { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public boolean addAll    (Collection<? extends IntegerNumber> c) { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public boolean removeAll (Collection<?> c)                       { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public boolean retainAll (Collection<?> c)                       { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public void    clear     ()                                      { throw new UnsupportedOperationException("Cannot change immutable Vector"); }

    @Override
    public String toString() {
        var sb =new StringBuilder();
        sb.append("[ ");
        for (Integer value : values){
            sb.append(value.toString()).append(" ");
        }
        return sb.append("]").toString();
    }

}
