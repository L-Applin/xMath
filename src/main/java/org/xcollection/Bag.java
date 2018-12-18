package org.xcollection;

import java.util.*;

/**
 * For fast removal. Doesnot support order.
 * @param <T>
 */
public class Bag<T> implements Collection<T> {

    private static final int DEFAULT_SIZE = 1<<8; //aka 256
    private static final double RESIZE_FACTOR = 1.5;

    private Object[] container;

    private int lastIndex;
    private int capacity;

    public Bag(T... elements) {
        this.container = new Object[DEFAULT_SIZE];
        capacity = DEFAULT_SIZE;
        lastIndex = elements.length - 1;
        for (int i = 0; i < elements.length; i++) {
            container[i] = elements[i];
        }
    }


    public Bag(int capacity) {
        this.container = new Object[capacity];
        this.capacity = capacity;
        lastIndex = 0;
    }

    public Bag(int capacity, T... elements) {
        if (capacity < elements.length)     { throw new IllegalArgumentException("Bag capacity is too small to contain elements."); }

        this.container = new Object[elements.length];
        this.capacity = capacity;
        lastIndex = elements.length - 1;
        for (int i = 0; i < elements.length; i++) {
            container[i] = elements[i];
        }

    }


    @Override
    public int size() {
        return lastIndex;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return Arrays.asList(container).contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        // todo [test]
        return new Iterator<>() {
            int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex <= lastIndex;
            }

            @Override
            public T next() {
                return (T) container[currentIndex++];
            }
        };
    }

    @Override
    public T[] toArray() {
        return (T[]) container.clone();
    }

    @Override
    public <U> U[] toArray(U[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (lastIndex + 1 >= capacity){
            resizeUp();
        }
        try {
            container[++lastIndex] = t;
            return true;
        } catch (ArrayIndexOutOfBoundsException ex){
            return false;
        }
    }

    private void resizeUp(){
        capacity = calculateNewCapacity();
        Object[] resized = new Object[capacity];
        for (int i = 0; i < container.length; i++) {
            resized[i] = container[i];
        }
        container = resized;
    }

    private int calculateNewCapacity(){
        return (int) (capacity * RESIZE_FACTOR);
    }

    @Override
    public boolean remove(Object o) {
        boolean found = false;
        for (int i = 0; i < container.length; i++) {
            if(container[i].equals(o)){
                T tmp = (T) container[lastIndex--];
                container[i] = tmp;
                found = true;
                break;
            }
        }

        if (found){
            if (resizeDownRequired()){
                resizeDown();
            }
        }

        return found;
    }

    private boolean resizeDownRequired(){
        return lastIndex < (int) (container.length / RESIZE_FACTOR);
    }

    private void resizeDown(){
        int size = (int) ((lastIndex * RESIZE_FACTOR) - lastIndex) / 2;
        Object[] tmp = new Object[size];
        for (int i = 0; i < lastIndex; i++) {
            tmp[i] = container[i];
        }
        container = tmp;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return Arrays.asList(container).containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        // todo : better
        c.forEach(this::add);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // todo : better
        c.forEach(this::remove);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        container = new Object[lastIndex];
        lastIndex = 0;
        capacity = (int) (lastIndex * RESIZE_FACTOR);
    }

}
