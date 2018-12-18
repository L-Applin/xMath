package org.xmath.linearAlgebra;

import org.xmath.num.IntegerNumber;

public class IntMatrix extends Matrix<IntegerNumber> {

    public IntMatrix(IntegerNumber[][] content) {
        super(content);
    }

    public IntMatrix scalarProduct(int scalar){
        IntMatrix sum = clone();
        for (int i = 0; i < sum.content.length; i++) {
            for (int j = 0; j < content[0].length; j++) {
                sum.content[i][j] = content[i][j].mult(scalar);
            }
        }
        return sum;

    }


    public IntMatrix scalarAddition(int scalar) {
        IntMatrix sum = clone();
        for (int i = 0; i < sum.content.length; i++) {
            for (int j = 0; j < content[0].length; j++) {
                sum.content[i][j] = content[i][j].add(scalar);
            }
        }
        return sum;
    }

    protected IntMatrix clone() {
        IntegerNumber[][] tmp = new IntegerNumber[content[0].length][content.length];
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[0].length; j++) {
                tmp[i][j] = content[i][j];
            }
        }
        return new IntMatrix(tmp);
    }

    public int at(int i, int j){
        return content[i][j].value();
    }

}
