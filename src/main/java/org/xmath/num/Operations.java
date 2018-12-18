package org.xmath.num;

public interface Operations {
    <T extends Operations>  T   add    (T other);
    <T extends Operations>  T   mult   (T other);
    <T extends Operations>  T   div    (T other);
    <T extends Operations>  T   pow    (T other);
    <T extends Operations>  T   sqrt   ();

}
