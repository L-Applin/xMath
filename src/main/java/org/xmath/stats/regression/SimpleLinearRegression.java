package org.xmath.stats.regression;

import org.xmath.linearAlgebra.DoubleMatrix;
import org.xmath.linearAlgebra.Matrices;
import org.xmath.stats.intervals.ConfidenceInterval;
import org.xmath.stats.Quantiles;
import org.xmath.stats.Sample;
import org.xmath.stats.distribution.Distributions;
import org.xmath.stats.statTests.BasicAbstractTest;
import org.xmath.stats.statTests.StatTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SimpleLinearRegression implements Regression{

    private int dataSize;

    private Sample data;
    private Sample y, yhat;
    private Beta1Test beta1Test;
    private Beta2Test beta2Test;
    private ConfidenceInterval beta1Interval, beta2Interval;

    private double sxx, sxy, syy, sumXSquared, xMean, yMean, scr, sce, sci, sigmaSquared;
    private DoubleMatrix H;
    private double[] coefficient;

    public SimpleLinearRegression(Sample data) {
        this.data = data;
    }

    /**
     * Fit the linear regression to the Sample provided. Lanches every calculation required to get
     * the informations of the simple linear regression (2d).
     * @param y
     * @return
     */
    public Regression fit(Sample y){
        this.y = y;
        dataSize = Math.min(data.size(), y.size());

        // todo : fit unidentical dataSet ?
        if (data.size() != y.size()) {
            // data = data.subSample(dataSize);
            // this.y = y.subSample(dataSize);
            throw new RuntimeException("DataSize must be identical");
        }

        coefficient = new double[2];

        xMean = data.mean();
        yMean = this.y.mean();

        sxx = data.diffSquare();
        syy = this.y.diffSquare();
        sxy = xySumCalc();
        sumXSquared = getSumXSquared();

        double h11 = sxx/data.size() + (xMean * xMean);
        H = Matrices.doubleMatrix(new double[][]{{h11, -xMean}, {-xMean, 1}}).scalarProduct(1 / sxx);

        coefficient[0] = yMean - ((sxy/sxx) * xMean);
        coefficient[1] = sxy/sxx;

        yhat = yHatCalc();
        scr = SCRCalc();
        sigmaSquared = scr/(dataSize - 2);

        beta1Test = this.new Beta1Test(coefficient[0]);
        beta2Test = this.new Beta2Test(coefficient[1]);
        beta1Test.fit();
        beta2Test.fit();

        beta1Interval = beta1Test.confInterval();
        beta2Interval = beta2Test.confInterval();

        return this;
    }

    /**
     * Calculte the sxy value
     * @return
     */
    private double xySumCalc(){
        double total = 0;
        for (int i = 0; i < data.size(); i++) {
            total += (data.value(i) - xMean) * (y.value(i) - yMean);
        }
        return total;
    }

    /**
     * Calculate the yHat values. Theses are the estimated y values based on the model.
     * @return
     */
    private Sample yHatCalc(){
        double[] tmp = new double[y.size()];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = yMean + ((sxy/sxx)*(data.value(i)-xMean));
        }
        return new Sample(tmp);
    }

    /**
     * Calculate the sum of all the value in the X data squared
     * @return
     */
    private double getSumXSquared(){
        double total = 0;
        for (int i = 0; i < data.size(); i++) {
            double val = data.value(i);
            total += val*val;
        }
        return total;
    }

    private double SCRCalc(){
        double total = 0;
        for (int i = 0; i < data.size(); i++) {
            double diff = y.value(i) - yhat.value(i);
            total += diff*diff;
        }
        return total;
    }

    private double calcSCE(){
        //todo
        return Double.NaN;
    }










    /* ****************** *
     *  PUBLIC INTERFACE  *
     * ****************** */

    /**
     * For every coefficient of the linear regression a t-test is produced against the null hypothesis = 0.
     * In this case, the linear regression only has beta1 and beta2 for coefficient so the List will always
     * be of length 2.
     * @return the list of all Student t-test for each coefficient.
     */
    public List<StatTest> betaTests(){
        List<StatTest> tests = new ArrayList<>();
        tests.add(beta1Test);
        tests.add(beta2Test);
        return List.copyOf(tests);
    }


    public List<ConfidenceInterval> intervals(){
        List<ConfidenceInterval> intervals = new ArrayList<>();
        intervals.add(beta1Interval);
        intervals.add(beta2Interval);
        return List.copyOf(intervals);
    }


    public List<Double> coefficient(){
        return List.of(coefficient[0], coefficient[1]);
    }


    public void summary() {
        // todo
    }

    /**
     * Abstract Helper parent classe for Beta t-tests
     */
    abstract class BetaTest extends BasicAbstractTest  {

        BetaTest(double beta) {
            super(beta, Quantiles.q95);
        }

        public double estimate() { return valueTested; }
        public Sample sample() { return new Sample(valueTested); }
        public void fit(Sample sample) { throw new RuntimeException("Cannot for beta test with sample"); }

        private void fit() {
            testDistribution = Distributions.student(dataSize - 2);
        }
    }

    /**
     * Test for Beat1
     */
    class Beta1Test extends BetaTest {

        Beta1Test(double beta) {
            super(beta);
        }

        public String nullHypothesis() {
            return "Beta1 = 0";
        }
        public String altHypothesis() {
            return "Beta1 != 0";
        }

        private void fit() {
            super.fit();
            double err = (1/(double) dataSize) * sxx * sigmaSquared * sumXSquared;
            statValue = Math.abs(valueTested) / err;
            interval = new BetaInterval(valueTested, err).fit(Quantiles.q975);
        }

    }


    /**
     * test for beta2
     */
    class Beta2Test extends BetaTest {

        Beta2Test(double beta) {
            super(beta);
        }

        public String nullHypothesis() { return "Beta2 = 0"; }
        public String altHypothesis() { return "Beta2 != 0"; }

        private void fit() {
            super.fit();
            double err = Math.sqrt(sxx/sigmaSquared);
            statValue = Math.abs(valueTested) / err;
            interval = new BetaInterval(valueTested, err).fit(Quantiles.q975);
        }
    }


    /**
     * Confidence interval for all Beta
     */
    class BetaInterval implements ConfidenceInterval {

        private double beta, err, error;

        BetaInterval(double beta, double err) {
            this.beta = beta;
            this.err = err;
        }
        public double error() { return error; }
        public double centralValue() { return beta; }

        public BetaInterval fit(Quantiles level) {
            error = Distributions.student(dataSize - 2).quantile(level) * err;
            return this;
        }
    }

}
