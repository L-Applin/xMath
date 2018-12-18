import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xmath.linearAlgebra.IntMatrix;
import org.xmath.linearAlgebra.Matrices;

public class MatrixTest {

    private static int size1 = 10;
    private static int size2 = 20;
    private static int size3 = 4;
    private static IntMatrix test1, test2, test3, testNonSymmetric;

    @BeforeClass
    public static void setup(){

        int[][] arr1 = new int[size1][size1];
        for (int j = 0; j < size1; j++) {
            for (int i = 0; i < size1; i++) {
                arr1[j][i] = i + size1*j;
            }
        }

        int[][] arr2 = new int[size2][size2];
        for (int i = 0; i < size2; i++) {
            for (int j = 0; j < size2; j++) {
                arr2[i][j] = i + size2*j;
            }
        }

        int[][] arr3 = new int[size3][size3];
        for (int i = 0; i < size3; i++) {
            for (int j = 0; j < size3; j++) {
                arr3[i][j] = i + size3*j;
            }
        }

        int[][] arr4 = new int[size1][size2];
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                arr4[i][j] = i + size1*j;
            }
        }

        test1 = Matrices.intMatrix(arr1);
        test2 = Matrices.intMatrix(arr2);
        test3 = Matrices.intMatrix(arr3);
        testNonSymmetric = Matrices.intMatrix(arr4);

    }

    @Test
    public void matricesStream(){
        System.out.println(test1);
    }

    @Test
    public void intMatrixTest(){

        Assert.assertEquals(100, test1.size());

        // int matrices have their equals fonction overload with a basic int argument. It is ok!
        Assert.assertTrue(test1.sum().equals(4950));

        for (int i = 0; i < Math.pow(size1, 2); i++) {
            Assert.assertTrue(test1.contains(i));
        }

        System.out.println(testNonSymmetric);
        System.out.println(testNonSymmetric.transpose());
        System.out.println(test1.scalarAddition(10).at(1,5));

    }

}
