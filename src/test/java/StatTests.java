import org.junit.Test;
import org.xmath.stats.Sample;
import org.xmath.stats.statTests.StatTest;
import org.xmath.stats.statTests.StudentTest;

public class StatTests {

    private Sample sample1 = TestData.normalStdMedCount;
    private Sample sample2 = TestData.normalNegMedCount;
    private Sample sample3 = TestData.normalPosMedCount;


    @Test
    public void studentTest(){
        StatTest t1 = new StudentTest();
        t1.fit(sample1);
        System.out.println(t1);

        StatTest t2 = new StudentTest();
        t2.fit(sample2);
        System.out.println(t2);

        StatTest t3 = new StudentTest(5);
        t3.fit(sample3);
        System.out.println(t3);
    }

    private static void logTest(StatTest t){
        System.out.println("\n\n");
        System.out.println(t.nullHypothesis());
        System.out.println(t.altHypothesis());
        System.out.println(String.format("confidence interval : %s", t.confInterval()));
        System.out.println(String.format("test statistic value : %.4f ", t.testStatistic()));
        System.out.println(String.format("p-value : %s",1 - t.pValue()));
        System.out.println(String.format("mean estimate : %.4f", t.estimate()));
        System.out.println(String.format("reject null hypothesis : %s",t.reject()));
        System.out.println(t.sample());

    }

}
