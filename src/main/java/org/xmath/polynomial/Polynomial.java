package org.xmath.polynomial;

import org.xmath.num.Operations;

public interface Polynomial {

    <T extends Operations> double eval(T x);

}
