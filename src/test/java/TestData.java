import org.xmath.stats.Generators;
import org.xmath.stats.Sample;
import org.xmath.stats.distribution.Distributions;

import java.util.Date;

public class TestData {

    static Sample normalStdLowCount, normalStdMedCount, normalStdHighCount,
            normalNegLowCount, normalNegMedCount, normalNegHighCount,
            normalPosLowCount, normalPosMedCount, normalPosHighCount,
            normalNegSigmaLowCount, normalNegSigmaMedCount, normalNegSigmaHighCount,
            normalPosSigmaLowCount, normalPosSigmaMedCount, normalPosSigmaHighCount;

    static {
        normalStdLowCount = Generators.normal(25);
        normalStdMedCount = Generators.normal(150);
        normalNegMedCount = Generators.normal(150, -5, 2);
        normalPosMedCount = Generators.normal(150, 5, 2);
        normalStdHighCount = Generators.normal(1500);
        normalNegLowCount = Generators.normal(25, -5, 2);
        normalNegHighCount = Generators.normal(1500, -5, 2);;
        normalPosLowCount = Generators.normal(25, 5, 2);
        normalPosHighCount = Generators.normal(1500, 5, 2);;
        normalNegSigmaLowCount = null;
        normalNegSigmaMedCount = null;
        normalNegSigmaHighCount = null;
        normalPosSigmaLowCount = null;
        normalPosSigmaMedCount = null;
        normalPosSigmaHighCount = null;
    }


}
