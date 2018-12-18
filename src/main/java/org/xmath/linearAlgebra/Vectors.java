package org.xmath.linearAlgebra;

import org.xmath.num.Operations;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


public class Vectors {

    private Vectors() throws IllegalAccessException
    { throw new IllegalAccessException("Unallowed to instanciate static class Vectors"); }


    public static <U extends Operations> IVector<U> of(List<U> elems){
        return new Vector<>(elems);
    }

    public static IntVector of(int... values){
        return new IntVector(values);
    }

    public static IntVector intVector(int... values){
        return new IntVector(values);
    }


    public static <U extends Operations> IVector<U> add(@Nonnull IVector<U> v1, @Nonnull IVector<U> v2){
        return v1.addition(v2);
    }

    public static <U extends Operations> U scalarProd(@Nonnull IVector<U> v1, @Nonnull IVector<U> v2){
        return v1.scalarProduct(v2);
    }




    @SuppressWarnings("unchecked")
    public static <U extends Operations> Collector collect(){
        return new VectorCollector<U>();
    }

    private static class VectorCollector <U extends Operations> implements Collector<U, List<U>, IVector<U>> {
        @Override
        public Supplier<List<U>> supplier() { return ArrayList::new; }

        @Override
        @SuppressWarnings("unchecked")
        public BiConsumer<List<U>, U> accumulator() { return List::add; }

        @Override
        public BinaryOperator<List<U>> combiner() {
            return (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            };
        }

        @Override
        public Function<List<U>, IVector<U>> finisher() { return Vectors::of; }

        @Override
        public Set<Characteristics> characteristics() {
            return new TreeSet<>();
        }
    }


}
