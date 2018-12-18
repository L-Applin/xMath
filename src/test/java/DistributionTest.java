import org.junit.Assert;
import org.junit.Test;
import org.xmath.stats.distribution.StudentDistribution;
import org.xmath.stats.Generators;

import static org.xmath.stats.distribution.Distributions.*;


public class DistributionTest {

    @Test
    public void stdNormalTest(){
        System.out.println(Generators.normal(1000, 0, 1));
    }

    @Test
    public void student(){
        StudentDistribution t = new StudentDistribution(100);
        System.out.println(t.p(0));
    }


    @Test
    public void gammaTest(){
        System.out.println(gamma(5/2d));
    }


    @Test
    public void erfTest(){
        System.out.println("\nERROR FUNCTION TEST");
        for (double i = -4; i < 4.01; i+=1) {
            System.out.printf("error function at %.2f = %.16f\n", i, erf(i));
        }

        Assert.assertEquals(0, erf(0), 0.00001);
        System.out.println("\ninverseErf");
        for (double i = -1; i < 1; i+=0.05) {
            System.out.printf("inverse error function at %.2f = %.16f\n", i, inverseErf(i));
        }

    }

}
