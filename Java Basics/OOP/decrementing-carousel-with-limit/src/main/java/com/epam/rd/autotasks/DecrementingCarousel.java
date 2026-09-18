package com.epam.rd.autotasks;

import java.util.Arrays;

public class DecrementingCarousel {

    private final int[] elements;
    private int size;
    private boolean isRunning;

    public DecrementingCarousel(final int capacity) {

        this.elements = new int[capacity];
        this.size = 0;
        this.isRunning = false;
    }

    public boolean addElement(final int element) {

        if (isRunning || element <= 0 || size >= elements.length) {
            return false;
        }

        elements[size] = element;
        size++;

        return true;
    }

    public CarouselRun run() {

        if (isRunning) {  return null; }
        isRunning = true;
        return createRun();
    }

    protected CarouselRun createRun() {
        return new CarouselRun( Arrays.copyOf(elements, size));
    }
}