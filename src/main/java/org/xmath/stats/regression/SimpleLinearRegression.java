package org.xmath.stats.regression;

import org.xmath.linearAlgebra.DoubleMatrix;
import org.xmath.linearAlgebra.Matrices;
import org.xmath.stats.intervals.ConfidenceInterval;
import org.xmath.stats.Quantiles;
import org.xmath.stats.Sample;
import org.xmath.stats.distribution.Distribution;
import org.xmath.stats.distribution.Distributions;
import org.xmath.stats.statTests.StatTest;

import java.util.ArrayList;
import java.util.List;

public class SimpleLinearRegression {

    private int dataSize;

    private Sample data;
    private Sample y, yhat;
    private BetaTest beta1Test, beta2Test;
    private BetaInterval beta1MeanInterval, beta2MeanInterval;

    private double sxx, sxy, syy, xMean, yMean, scr, sce, sci;
    private DoubleMatrix H;
    private double[] coefficient;

    public SimpleLinearRegression(Sample data) {
        this.data = data;
    }

    public SimpleLinearRegression fit(Sample y){
        this.y = y;
        dataSize = Math.min(data.size(), y.size());

        // todo : fit unidentical dataSet
        if (data.size() != y.size()) {
            data = data.subSample(dataSize);
            this.y = y.subSample(dataSize);
            throw new RuntimeException("DataSize must be identical");
        }

        coefficient = new double[2];

        xMean = data.mean();
        yMean = this.y.mean();

        sxx = data.diffSquare();
        syy = this.y.diffSquare();
        sxy = xySumCalc();

        double h11 = sxx/data.size() + (xMean * xMean);
        H = Matrices.doubleMatrix(new double[][]{{h11, -xMean},{-xMean, 1}}).scalarProduct(1 / sxx);

        coefficient[0] = yMean - ((sxy/sxx) * xMean);
        coefficient[1] = sxy/sxx;

        yhat = yHatCalc();
        scr = SCRCalc();

        beta1Test = new BetaTest(coefficient[0]);
        beta2Test = new BetaTest(coefficient[1]);
        beta1Test.fit();
        beta2Test.fit();

        beta1MeanInterval = beta1Test.confidenceMeanInterval;
        beta2MeanInterval = beta2Test.confidenceMeanInterval;


        return this;
    }

    private double xySumCalc(){
        double total = 0;
        for (int i = 0; i < data.size(); i++) {
            total += (data.value(i) - xMean) * (y.value(i) - yMean);
        }
        return total;
    }


    private Sample yHatCalc(){
        double[] tmp = new double[y.size()];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = yMean + ((sxy/sxx)*(data.value(i)-xMean));
        }
        return new Sample(tmp);
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










    /* **************** *
     * PUBLIC INTERFACE *
     ****************** */

    public List<StatTest> betaTests(){
        List<StatTest> tests = new ArrayList<>();
        tests.add(beta1Test);
        tests.add(beta2Test);
        return tests;
    }










    class BetaTest implements StatTest {


        private final double meanTested;
        private final Quantiles testLevel;

        private Double statValue;
        private Distribution testDistribution;
        private BetaInterval confidenceMeanInterval;
        private double sampleMean;

        private double betaTested;

        public BetaTest(double betaTested) {
            this.meanTested = 0;
            this.testLevel = Quantiles.q975;
            this.betaTested = betaTested;
        }

        @Override
        public double testStatistic() {
            return (Math.sqrt(sxx) * Math.abs(betaTested))/(Math.sqrt(scr/(dataSize - 2)));
        }

        @Override
        public Distribution test() {
            return Distributions.student(dataSize - 2);
        }


        @Override
        public BetaInterval confInterval() {
            return null;
        }

        @Override
        public double estimate() { return betaTested; }

        @Override
        public String nullHypothesis() {
            return "Beta = 0";
        }

        @Override
        public String altHypothesis() {
            return "Beta != 0";
        }

        @Override
        public Sample sample() { return null; }

        @Override
        public Quantiles testLevel() { return testLevel; }

        @Override
        public void fit(Sample sample)
        { throw new RuntimeException("Cannot for beta test with sample"); }

        private void fit() {

        }
    }


    class BetaInterval implements ConfidenceInterval {

        @Override
        public double error() {
            return 0;
        }

        @Override
        public double centralValue() {
            return 0;
        }

        @Override
        public ConfidenceInterval fit(Quantiles level) {
            return null;
        }

        @Override
        public double low() {
            return 0;
        }

        @Override
        public double high() {
            return 0;
        }

        @Override
        public double[] interval() {
            return new double[0];
        }
    }

}
