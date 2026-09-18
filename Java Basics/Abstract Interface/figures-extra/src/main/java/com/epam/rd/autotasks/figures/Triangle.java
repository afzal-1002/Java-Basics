package com.epam.rd.autotasks.figures;

class Triangle extends Figure {

    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {

        if (a == null || b == null || c == null) { throw new IllegalArgumentException(); }

        if (Math.abs(cross(a, b, c)) <= EPS) { throw new IllegalArgumentException(); }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public Point centroid() {

        double x = (a.getX() + b.getX() + c.getX()) / 3.0;
        double y = (a.getY() + b.getY() + c.getY()) / 3.0;

        return new Point(x, y);
    }

    @Override
    public boolean isTheSame(Figure figure) {

        if (figure == null || getClass() != figure.getClass()) { return false; }

        Triangle other = (Triangle) figure;

        Point[] first = {a, b, c};
        Point[] second = {other.a, other.b, other.c};

        return sameVertices(first, second);
    }
}