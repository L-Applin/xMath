package org.xmath.linearAlgebra;

import org.xmath.num.Operations;

import java.util.Collection;
import java.util.Iterator;

public interface IMatrix <T extends Operations> extends Collection <T> {

    T get(int i, int j);

    int rows();
    int cols();

    T sum();
    T det();

    Vector<T> getColum(int colIndex);
    Vector<T> getRow(int rowIndex);

    <U extends Operations> IMatrix<T> scalarProduct(U scalar);
    <U extends Operations> IMatrix<T> scalarAddition(U scalar);


    IMatrix<T> addition(IMatrix<T> other);
    IMatrix<T> crossProduct(IMatrix<T> other);
    IMatrix<T> transpose();

    IMatrix<T> inverse() throws NonInversibleMatricException;


    /**
     * Iterates over the whole matrix elements row by row
     * @return the iterator
     */
    default Iterator<T> rowIterator() {
        return new Iterator<>() {
            private int currentRowIndex = 0;
            private int currentColIndex = 0;
            @Override
            public boolean hasNext() {
                return currentColIndex < cols();
            }

            @Override
            public T next() {
                T elem = get(currentRowIndex % rows(), currentColIndex);
                currentRowIndex = (currentRowIndex + 1) % rows();
                currentColIndex = currentRowIndex == 0 ? currentColIndex + 1 : currentColIndex;
                return elem;
            }
        };

    }


    /**
     * Iterates over the whole matrix elements columns by colums
     * @return the iterator
     */
    default Iterator<T> columnIterator() {
        return new Iterator<>() {
            private int currentRowIndex = 0;
            private int currentColIndex = 0;
            @Override
            public boolean hasNext() {
                return currentRowIndex < rows();
            }

            @Override
            public T next() {
                T elem = get(currentColIndex % cols(), currentRowIndex);
                currentColIndex = (currentColIndex + 1) % cols();
                currentRowIndex = currentColIndex == 0 ? currentRowIndex + 1 : currentRowIndex;
                return elem;
            }
        };

    }

}
