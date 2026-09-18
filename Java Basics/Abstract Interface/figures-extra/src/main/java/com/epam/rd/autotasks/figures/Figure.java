package com.epam.rd.autotasks.figures;

abstract class Figure {

    protected static final double EPS = 1e-10;

    public abstract Point centroid();

    public abstract boolean isTheSame(Figure figure);

    protected static double cross(Point a, Point b, Point c) {

        double first = (b.getX() - a.getX()) * (c.getY() - a.getY());
        double second = (b.getY() - a.getY()) * (c.getX() - a.getX());

        return first - second;
    }

    protected static boolean samePoint(Point first, Point second) {

        boolean sameX = Math.abs(first.getX() - second.getX()) <= EPS;
        boolean sameY = Math.abs(first.getY() - second.getY()) <= EPS;

        return sameX && sameY;
    }

    protected static boolean sameVertices(Point[] first, Point[] second) {

        boolean[] used = new boolean[second.length];
        for (Point point : first) {
            boolean found = false;
            for (int i = 0; i < second.length; i++) {
                if (!used[i] && samePoint(point, second[i])) {
                    used[i] = true;
                    found = true;
                    break;
                }
            }

            if (!found) { return false; }
        }
        return true;
    }
}