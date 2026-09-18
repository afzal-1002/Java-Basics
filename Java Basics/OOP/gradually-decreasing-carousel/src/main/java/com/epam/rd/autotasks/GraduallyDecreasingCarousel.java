package com.epam.rd.autotasks;

public class GraduallyDecreasingCarousel extends DecrementingCarousel {

    public GraduallyDecreasingCarousel(final int capacity) {
        super(capacity);
    }

    @Override
    protected CarouselRun createRun() {

        return new CarouselRun(elements) {

            private final int[] decrement = new int[elements.length];

            @Override
            protected int decrease(int element, int index) {

                decrement[index]++;

                return element - decrement[index];
            }
        };
    }
}