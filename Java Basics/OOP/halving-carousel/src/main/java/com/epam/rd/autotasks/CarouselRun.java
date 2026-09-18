package com.epam.rd.autotasks;

import java.util.Arrays;

public class CarouselRun {

    private final int[] elements;
    private int index = 0;

    public CarouselRun(int[] elements) {
        this.elements = Arrays.copyOf(elements, elements.length);
    }

    public int next() {

        if (isFinished()) {
            return -1;
        }

        while (elements[index] <= 0) {
            index++;

            if (index == elements.length) {
                index = 0;
            }
        }

        int result = elements[index];

        elements[index] = decrease(elements[index]);

        index++;

        if (index == elements.length) {
            index = 0;
        }

        return result;
    }

    protected int decrease(int element) {
        return element - 1;
    }

    public boolean isFinished() {

        for (int element : elements) {
            if (element > 0) {
                return false;
            }
        }

        return true;
    }
}