package com.epam.rd.autotasks;

public class CarouselRun {

    protected final int[] elements;
    protected int index = 0;

    public CarouselRun(int[] elements) {
        this.elements = elements.clone();
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

        elements[index] = decrease(elements[index], index);

        index++;

        if (index == elements.length) {
            index = 0;
        }

        return result;
    }

    protected int decrease(int element, int index) {
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