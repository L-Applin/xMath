package org.xmath.linearAlgebra;

import org.xmath.num.IntegerNumber;
import org.xmath.num.Operations;
import org.xmath.num.ZeroOperation;

import javax.annotation.Nonnull;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Immutable Matrix indexed by line / col using basic array.
 * @param <T>
 */
public class Matrix <T extends Operations> implements IMatrix <T>, Cloneable {

    protected final T[][] content;
    protected int rows, col;

    public Matrix(T[][] content) {
        this.content = content;
        this.rows = content[0].length;
        this.col = content.length;
    }



    /* ************************* *
     * COLLECTION IMPLEMENTATION *
     *************************** */
    @Override
    public T get(int i, int j) {
        return content[i][j];
    }


    @Override
    public int size() {
        return col*rows;
    }

    @Override
    public boolean isEmpty() {
        return content.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (T elem:this){
            if (elem.equals(o)){
                return true;
            }
        }
        return false;
    }


    /**
     * default implementation return a row iterator
     * @return
     */
    @Override
    public @Nonnull Iterator<T> iterator() {
        return rowIterator();
    }

    @Override
    public @Nonnull Object[] toArray() {
        Object[] tmp =  new Object[size()];
        int i = 0;
        for (T elem:this){
            tmp[i] = elem;
            i++;
        }
        return tmp;
    }

    @Override
    public @Nonnull <T1> T1[] toArray(@Nonnull T1[] a) {
        int i = 0;
        for (T elem:this){
            a[i] = (T1) elem;
            i++;
        }
        return a;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }










    /* ********************** *
     * IMATRIX IMPLEMENTATION *
     ************************ */

    @Override
    public int rows() { return rows; }

    @Override
    public int cols() { return col; }

    @Override
    public T sum() {
        AtomicReference<Operations> sum = new AtomicReference<>(new ZeroOperation());
        iterator().forEachRemaining(elem -> sum.set(sum.get().add(elem)));
        return (T) sum.get();
    }

    @Override
    public <U extends Operations> IMatrix<T> scalarProduct(U scalar){
        Matrix<T> prod = clone();
        for (T elem : prod) {
            elem = (T) elem.mult(scalar);
        }
        return prod;
    }


    @Override
    public <U extends Operations> IMatrix<T> scalarAddition(U scalar) {
        Matrix<T> sum = clone();
        for (int i = 0; i < sum.content.length; i++) {
            for (int j = 0; j < content[0].length; j++) {
                sum.content[i][j] = (T) content[i][j].add(scalar);
            }
        }
        return sum;
    }

    @Override
    public IMatrix<T> addition(IMatrix<T> other) {
        return null;
    }

    @Override
    public IMatrix<T> crossProduct(IMatrix<T> other) {
        return null;
    }

    @Override
    public T det() {
        return null;
    }


    @Override
    public Vector<T> getColum(int colIndex) {
        T[] tmp = content[0].clone();
        for (int i = 0; i < rows; i++) {
            tmp[i] = content[i][colIndex];
        }
        return new Vector<>(tmp);
    }

    @Override
    public Vector<T> getRow(int rowIndex) {
        return new Vector<>(content[rowIndex]);
    }

    @Override
    public IMatrix<T> transpose() {
        T[][] tmp = (T[][]) Array.newInstance(content[0][0].getClass(), content[0].length, content.length);
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[0].length; j++) {
                tmp[j][i] = content[i][j];
            }
        }
        return new Matrix<>(tmp);
    }

    @Override
    public IMatrix<T> inverse() throws NonInversibleMatricException {
        //todo
        return null;
    }


    /* **************************
     * IMMUTABLE IMPLEMENTATION *
     * ************************** */

    public boolean add(T t)                          { throw new UnsupportedOperationException("Cannot change immutable Matrix"); }
    public boolean remove(Object o)                  { throw new UnsupportedOperationException("Cannot change immutable Matrix"); }
    public boolean addAll(Collection<? extends T> c) { throw new UnsupportedOperationException("Cannot change immutable Matrix"); }
    public boolean removeAll(Collection<?> c)        { throw new UnsupportedOperationException("Cannot change immutable Matrix"); }
    public boolean retainAll(Collection<?> c)        { throw new UnsupportedOperationException("Cannot change immutable Matrix"); }
    public void    clear()                           { throw new UnsupportedOperationException("Cannot change immutable Matrix"); }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(T[] row : content){
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }


    @Override
    protected Matrix<T> clone() {
        T[][] tmp = (T[][]) Array.newInstance(content[0][0].getClass(), content[0].length, content.length);
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[0].length; j++) {
                tmp[i][j] = content[i][j];
            }
        }
        return new Matrix<>(tmp);
    }
}
