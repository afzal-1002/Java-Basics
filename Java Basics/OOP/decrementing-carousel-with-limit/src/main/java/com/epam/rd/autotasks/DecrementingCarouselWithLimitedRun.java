package com.epam.rd.autotasks;

public class DecrementingCarouselWithLimitedRun extends DecrementingCarousel {

    private final int actionLimit;

    public DecrementingCarouselWithLimitedRun( final int capacity, final int actionLimit) {
        super(capacity);
        this.actionLimit = actionLimit;
    }

    @Override
    protected CarouselRun createRun() {
        return new LimitedCarouselRun( actionLimit, super.createRun() );
    }

    private static class LimitedCarouselRun extends CarouselRun {

        private int remainingActions;
        private final CarouselRun originalRun;

        LimitedCarouselRun(  int actionLimit, CarouselRun originalRun) {
            super(new int[0]);
            this.remainingActions = actionLimit;
            this.originalRun = originalRun;
        }

        @Override
        public int next() {

            if (isFinished()) {  return -1; }
            remainingActions--;
            return originalRun.next();
        }

        @Override
        public boolean isFinished() {

            return remainingActions <= 0 || originalRun.isFinished();
        }
    }
}