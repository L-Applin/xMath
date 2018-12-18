package org.xmath.linearAlgebra;

import org.xmath.num.Operations;

import java.util.Collection;

/**
 * Column vector representation supporting basic Linear algebra operations.
 * @param <T>
 */
public interface IVector <T extends Operations> extends Collection<T> {

    /**
     * Return the i_th element of the vector
     * @param i
     * @return
     */
    T get(int i);

    T sum();
    T scalarProduct(IVector<T> other);
    IVector<T> addition(IVector<T> other);
    IVector<T> mult(T other);
    IVector<T> crossProd(IVector<T> other);

    default T norm(){
        return sum().sqrt();
    }





}
