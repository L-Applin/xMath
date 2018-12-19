package org;

import org.xmath.linearAlgebra.Vectors;
import org.xmath.polynomial.Polynomial;
import org.xmath.recurrence.RFunction;
import org.xmath.recurrence.Recurrence;


public class Main {

    public static void main(String[] args) {

        Recurrence dev2_4 = Recurrence.create(new RFunction() {
                public double eval(int n) {
                    if (n == 0) return 0;
                    return  1 / (4 - call(n - 1));
                }
            });

        System.out.println(dev2_4.eval(10));


        Polynomial p1 = new Polynomial(new int[]{1, -3, 4, 7, 1}, 'y');
        Polynomial p2 = new Polynomial(new int[]{1, -2}, 'z');

        System.out.println(p1.multiply(p2).setVariableName('a'));

        System.out.println(Polynomial.fromRoots(new int[]{-2, 3}));

        var intVect = Vectors.intVector(10, 20, 30);
        var intVect2 = Vectors.intVector(1, 2, 3);

        System.out.println(intVect);
        System.out.println(intVect.sum());
        System.out.println(intVect.scalarProduct(intVect2));
        System.out.println(intVect.stream().map(x -> x.mult(2)).collect(Vectors.collect()));
        System.out.println(intVect.mult(2));



    }



}
