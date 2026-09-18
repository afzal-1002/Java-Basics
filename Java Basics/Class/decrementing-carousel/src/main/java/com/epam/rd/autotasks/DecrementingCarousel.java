package com.epam.rd.autotasks;

public class DecrementingCarousel {

    private int[] elements;
    private int size = 0;
    private boolean isRun = false;

    public DecrementingCarousel(int capacity) {
        elements = new int[capacity];
    }

    public boolean addElement(int element) {

        if (element <= 0 || size == elements.length || isRun) {
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
        return new CarouselRun(elements);
    }
}
