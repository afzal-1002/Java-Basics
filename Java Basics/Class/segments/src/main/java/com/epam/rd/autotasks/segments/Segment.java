package com.epam.rd.autotasks.segments;

public class Segment {

    private Point start;
    private Point end;

    public Segment(Point start, Point end) {

        if (start == null || end == null) {
            throw new IllegalArgumentException();
        }

        if (start == end
                || (start.getX() == end.getX() && start.getY() == end.getY())) {
            throw new IllegalArgumentException();
        }

        this.start = start;
        this.end = end;
    }

    double length() {

        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    Point middle() {
        double mx = (start.getX() + end.getX()) / 2.0;
        double my = (start.getY() + end.getY()) / 2.0;

        return new Point(mx, my);
    }

    Point intersection(Segment another) {

        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();

        double x3 = another.start.getX();
        double y3 = another.start.getY();
        double x4 = another.end.getX();
        double y4 = another.end.getY();

        double d = (x1 - x2) * (y3 - y4)
                - (y1 - y2) * (x3 - x4);

        if (d == 0) {
            return null;
        }

        double px
                = ((x1 * y2 - y1 * x2) * (x3 - x4)
                - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;

        double py
                = ((x1 * y2 - y1 * x2) * (y3 - y4)
                - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

        if (onSegment(px, py, x1, y1, x2, y2)
                && onSegment(px, py, x3, y3, x4, y4)) {
            return new Point(px, py);
        }

        return null;
    }

    private boolean onSegment(double px, double py,
            double x1, double y1,
            double x2, double y2) {

        return px >= Math.min(x1, x2)
                && px <= Math.max(x1, x2)
                && py >= Math.min(y1, y2)
                && py <= Math.max(y1, y2);
    }

}
