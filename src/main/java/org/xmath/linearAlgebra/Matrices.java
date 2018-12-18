package org.xmath.linearAlgebra;

import org.xmath.num.IntegerNumber;

public class Matrices {

    private Matrices() throws IllegalAccessException
    { throw new IllegalAccessException("Unallowed to instanciate static class Matrices"); }


    public static IntMatrix intMatrix(int[][] content){
        IntegerNumber[][] realContent = new IntegerNumber[content.length][content[0].length];
        for (int i = 0; i < content.length; i++) {
            int[] row = content[i];
            for (int j = 0; j < row.length; j++) {
                realContent[i][j] = new IntegerNumber(content[i][j]);
            }
        }
        return new IntMatrix(realContent);
    }

    public static IMatrix<IntegerNumber> intMatrixfromVectors(IntVector... vectors){
        //todo
        return null;
    }


}
