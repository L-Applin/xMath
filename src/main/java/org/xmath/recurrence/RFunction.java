package org.xmath.recurrence;

import java.util.ArrayList;

public abstract class RFunction {

    private ArrayList<Double> values = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public ArrayList<Double> getValues(){
        return (ArrayList<Double>) values.clone();
    }

    private ArrayList<Double> calculatedValues = new ArrayList<>();

    public abstract double eval(int currentStep);
    public double funcOf(int eval){ return Double.NaN; }

    private double setValue(int index, double value){
        values.add(index, value);
        return value;
    }

    public double call(int currentStep){
        double res = eval(currentStep);
        setValue(currentStep, res);
        return res;
    }

}
