package org.xmath.recurrence;

import java.util.List;

public class Recurrence {


    /**
     * The final value calculated by the call
     */
    private double evaluatedValue;
    public double getEvaluatedValue() { return evaluatedValue; }

    /**
     * The actual call fontion that will be evaluated
     */
    private final RFunction recurenceFunction;

    public Recurrence(RFunction recurenceFunction) {
        this.recurenceFunction = recurenceFunction;
    }

    /**
     * All values of the evaluated call fontion for all the steps.
     */
    public List<Double> getValues() {
        return recurenceFunction.getValues();
    }


    public Recurrence eval(int steps){
        if (steps < 0) throw new IllegalArgumentException("steps must be greater than 0");
        evaluatedValue = recurenceFunction.call(steps);
        return this;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Double i : getValues()){
            sb.append(i).append("\n");
        }
        return sb.toString();
    }

    public String table(){

        StringBuilder sb = new StringBuilder();
        sb.append("\n| i | T(i) |\n").append("------------\n");
        int i = 0;
        for (double val : getValues()){
            sb.append(String.format("| %s | %s |\n", i++, val));
        }
        return sb.toString();
    }

    public static Recurrence create(RFunction recurenceFunction){
        return new Recurrence(recurenceFunction);
    }

}
