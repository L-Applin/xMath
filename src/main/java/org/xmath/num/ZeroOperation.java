package org.xmath.num;

public class ZeroOperation implements Operations {

    private final int value;

    public ZeroOperation() {
        this.value = 0;
    }

    @Override
    public <T extends Operations> T add(T other) {
        return other;
    }

    @Override
    public ZeroOperation mult(Operations other) {
        return new ZeroOperation();
    }

    @Override
    public ZeroOperation div(Operations other) {
        return new ZeroOperation();
    }

    @Override
    public ZeroOperation pow(Operations other) {
        return new ZeroOperation();
    }

    @Override
    public ZeroOperation sqrt() {
        return new ZeroOperation();
    }
}
