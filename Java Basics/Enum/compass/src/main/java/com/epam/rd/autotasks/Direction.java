package com.epam.rd.autotasks;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

    private static final int FULL_CIRCLE = 360;

    Direction(final int degrees) {
        this.degrees = degrees;
    }

    private int degrees;

    private static int normalize(int degrees) {
        return ((degrees % FULL_CIRCLE) + FULL_CIRCLE) % FULL_CIRCLE;
    }

    public static Direction ofDegrees(int degrees) {
        final int normalized = normalize(degrees);
        for (Direction direction : values()) {
            if (direction.degrees == normalized) {
                return direction;
            }
        }
        return null;
    }

    public static Direction closestToDegrees(int degrees) {
        final int normalized = normalize(degrees);
        Direction closest = null;
        int minDifference = Integer.MAX_VALUE;
        for (Direction direction : values()) {
            final int diff = Math.abs(direction.degrees - normalized);
            final int circularDiff = Math.min(diff, FULL_CIRCLE - diff);
            if (circularDiff < minDifference) {
                minDifference = circularDiff;
                closest = direction;
            }
        }
        return closest;
    }

    public Direction opposite() {
        return ofDegrees(this.degrees + 180);
    }

    public int differenceDegreesTo(Direction direction) {
        final int diff = Math.abs(this.degrees - direction.degrees);
        return Math.min(diff, FULL_CIRCLE - diff);
    }
}
