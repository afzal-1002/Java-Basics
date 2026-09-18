package com.epam.rd.autotasks;

public class DecrementingCarousel {

    protected final int[] elements;
    private int size = 0;
    private boolean isRun = false;

    public DecrementingCarousel(int capacity) {
        elements = new int[capacity];
    }

    public boolean addElement(int element) {

        if (element <= 0) {
            return false;
        }

        if (size == elements.length) {
            return false;
        }

        if (isRun) {
            return false;
        }

        elements[size] = element;
        size++;

        return true;
    }

    public CarouselRun run() {

        if (isRun) {
            return null;
        }

        isRun = true;

        return createRun();
    }

    protected CarouselRun createRun() {
        return new CarouselRun(elements);
    }
}