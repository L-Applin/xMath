package org.xmath.linearAlgebra;

import com.google.common.base.Suppliers;
import com.google.common.collect.Streams;
import jdk.dynalink.Operation;
import org.xmath.num.DoubleNumber;
import org.xmath.num.IntegerNumber;
import org.xmath.num.Operations;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Generi implementation of the IVector interface
 * @param <T> type of the element of the vector
 */
public class Vector<T extends Operations> implements IVector<T> {

    protected final T[] values;

    public Vector(T... values) {
        this.values = values;
    }



    @SuppressWarnings({"unchecked"})
    public Vector(Double... values){
        DoubleNumber[] tmp = new DoubleNumber[values.length];
        for (int i = 0; i < values.length; i++) {
            tmp[i] = new DoubleNumber(values[i]);
        }
        this.values = (T[]) tmp;
    }


    @SuppressWarnings({"unchecked", "all"})
    public Vector(@Nonnull List<T> values) {
        if (values.isEmpty()) { throw new IllegalArgumentException("Cannot instanciate from empty list"); }

        T[] toR = (T[]) java.lang.reflect.Array.newInstance(values.get(0)
                .getClass(), values.size());
        for (int i = 0; i < values.size(); i++) {
            toR[i] = values.get(i);
        }
        this.values = toR;

    }

    @Override
    public T get(int i) {
        return values[i];
    }

    /**
     * Element wise addition
     * @param other
     * @return
     */
    @SuppressWarnings({"unchecked"})
    public IVector<T> addition(IVector<T> other){
        T[] clone = values.clone();
        if (other == null)                             { throw new IllegalArgumentException("Vectors must not be null"); }
        if (values.length != other.toArray().length)   { throw new IllegalArgumentException("Vectors must be of same size"); }

        for (int i = 0; i < clone.length; i++) {
            clone[i] = values[i].add((T)other.toArray()[i]);
        }
        return new Vector<>(clone);
    }

    @Override
    public IVector<T> mult(T other) {
        T[] clone = values.clone();
        if (other == null)                             { throw new NullPointerException("Multiplication with null element"); }

        for (int i = 0; i < clone.length; i++) {
            clone[i] = clone[i].mult(other);
        }
        return new Vector<>(clone);
    }

    @SuppressWarnings({"unchecked"})
    public T sum(){
        AtomicReference<T> val = new AtomicReference<>();
        Arrays.stream(values)
              .reduce(Operations::add)
              .ifPresent(val::set);
        return val.get();
    }


    @SuppressWarnings({"unchecked", "all"})
    public T scalarProduct(IVector<T> other){
        AtomicReference<T> val = new AtomicReference<>();
        Streams.zip(Stream.of(values),
                    Stream.of(other.toArray()),
                    (a, b) -> a.mult((T)b))
                .reduce((acc, elem) -> ((T)elem).add((T)acc))
                .ifPresent(val::set);
        return val.get();
    }

    @Override
    public String toString() {
        var sb =new StringBuilder();
        sb.append("[ ");
        for (T value : values){
            sb.append(value.toString()).append(" ");
        }
        return sb.append("]").toString();
    }

    @Override
    public IVector<T> crossProd(IVector<T> other) {
        return null;
    }




    @Override
    public T[] toArray() {
        return values.clone();
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
    public Iterator<T> iterator() {
        return new ArrayList<>(Arrays.asList(values)).iterator();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) values.clone();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return new ArrayList<>(Arrays.asList(values)).containsAll(c);
    }

    @SuppressWarnings("unchecked")
    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }


    public boolean add       (T t)                       { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public boolean remove    (Object o)                  { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public boolean addAll    (Collection<? extends T> c) { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public boolean removeAll (Collection<?> c)           { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public boolean retainAll (Collection<?> c)           { throw new UnsupportedOperationException("Cannot change immutable Vector"); }
    public void    clear     ()                          { throw new UnsupportedOperationException("Cannot change immutable Vector"); }



}
