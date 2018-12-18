import org.junit.Test;
import org.xmath.stats.Sample;

import static org.xmath.stats.Data.*;

public class DataTest {


    private double[] data1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,18,17,19,20,121,-1,-2,-3,1.5,-1,5,-0.6,0.1};


    @Test
    public void sampleTest(){
        Sample test1 = c(data1);
        System.out.println(test1);
    }



}
