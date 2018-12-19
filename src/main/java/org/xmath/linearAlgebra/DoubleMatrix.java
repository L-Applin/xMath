package org.xmath.linearAlgebra;

import org.xmath.num.DoubleNumber;
import org.xmath.num.IntegerNumber;

public class DoubleMatrix extends Matrix<DoubleNumber> {

    public DoubleMatrix(DoubleNumber[][] content) {
        super(content);
    }

    public DoubleMatrix scalarProduct(double scalar){
        DoubleMatrix sum = clone();
        for (int i = 0; i < sum.content.length; i++) {
            for (int j = 0; j < content[0].length; j++) {
                sum.content[i][j] = content[i][j].mult(scalar);
            }
        }
        return sum;

    }


    public DoubleMatrix scalarAddition(double scalar) {
        DoubleMatrix sum = clone();
        for (int i = 0; i < sum.content.length; i++) {
            for (int j = 0; j < content[0].length; j++) {
                sum.content[i][j] = content[i][j].add(scalar);
            }
        }
        return sum;
    }

    protected DoubleMatrix clone() {
        DoubleNumber[][] tmp = new DoubleNumber[content[0].length][content.length];
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[0].length; j++) {
                tmp[i][j] = content[i][j];
            }
        }
        return new DoubleMatrix(tmp);
    }

    public double at(int i, int j){
        return content[i][j].value();
    }


}
