package org.xmath.polynomial;

import org.xmath.num.Operations;
import org.xmath.num.complex.Complex;

/**
 * java.polynomial with integer coefficient
 */
public class Polynomial {

    private static char default_char = 'x';

    private int[] coefficients;
    private char variableName = 0;
    public Polynomial setVariableName(char variableName){ this.variableName = variableName; return  this; }

    public Polynomial(int[] coefficients) {
        this.coefficients = coefficients.clone();
    }

    public Polynomial(int[] coefficients, char variableName) {
        this(coefficients);
        this.variableName = variableName;
    }

    @Override
    public String toString() {

        char ch = variableName == 0  ? default_char : variableName;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < coefficients.length; i++){
            int loopCoefficient = coefficients[i];
            if (i == 0) {
                sb.append(loopCoefficient);
            } else {
                if (loopCoefficient >= 0) {
                    sb.append("+ ").append(loopCoefficient == 1 ? "" : loopCoefficient);
                } else {

                    sb.append("- ").append(loopCoefficient == -1 ? "" : Math.abs(loopCoefficient));
                }
                sb.append(ch);
                if (i >=2){
                    sb.append("^").append(i);
                }

            }
            sb.append(" ");

        }

        return sb.toString();
    }

    /**
     * Allows to calculate a java.polynomial based on the given roots.
     *
     * @param roots
     * @return
     */
    public static Polynomial fromRoots(int[] roots){
        // basic iterative algorithm
        Polynomial total = fromSingleRoot(roots[0]);
        for (int i = 1; i < roots.length; i++){
            total = total.multiply(fromSingleRoot(roots[i]));
        }
        return total;
    }

    private static Polynomial fromSingleRoot(int r){
        return new Polynomial(new int[]{-r, 1});
    }

    /**
     * multiply two polynomials together
     * @param p
     * @return
     */
    public Polynomial multiply(Polynomial p){
        // todo : replace with nlogn DaQ algo

        // basic O(n^2) algorithme.
        int m = coefficients.length;
        int n = p.coefficients.length;

        int[] prod = new int[m+n-1];

        // Initialize the porduct java.polynomial
        for (int i = 0; i < prod.length; i++) {
            prod[i] = 0;
        }

        // Multiply two polynomials term by term
        for (int i=0; i < m; i++)  {
            for (int j=0; j < n; j++)
                prod[i+j] += coefficients[i] * p.coefficients[j];
        }

        return new Polynomial(prod);

    }

    public double eval(int x){
        int total = coefficients[0];
        for (int p = 1; p < coefficients.length; p++){
            total += coefficients[p] * (int) Math.pow(x, p);
        }
        return total;
    }

    public <T extends Operations> T eval(T x){
        // todo

        return null;
    }

    public Complex eval(Complex x){
        // todo
        Complex total = new Complex(coefficients[0],0);
        for (int p = 1; p < coefficients.length; p++){
            total.add(x.pow(p).mult(coefficients[p]));
        }
        return total;
    }

}
