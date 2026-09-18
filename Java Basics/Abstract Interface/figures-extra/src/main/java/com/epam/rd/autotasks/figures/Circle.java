package com.epam.rd.autotasks.figures;

class Circle extends Figure {

    private Point center;
    private double radius;

    public Circle(Point center, double radius) {

        if (center == null || radius <= 0) { throw new IllegalArgumentException(); }

        this.center = center;
        this.radius = radius;
    }

    @Override
    public Point centroid() { return center; }

    @Override
    public boolean isTheSame(Figure figure) {

        if (figure == null || getClass() != figure.getClass()) { return false; }

        Circle other = (Circle) figure;

        return samePoint(center, other.center) && Math.abs(radius - other.radius) <= EPS;
    }
}