package com.epam.rd.autotasks;

public class HalvingCarousel extends DecrementingCarousel {

    public HalvingCarousel(final int capacity) {
        super(capacity);
    }

    @Override
    protected CarouselRun createRun() {

        return new CarouselRun(elements) {

            @Override
            protected int decrease(int element) {
                return element / 2;
            }
        };
    }
}