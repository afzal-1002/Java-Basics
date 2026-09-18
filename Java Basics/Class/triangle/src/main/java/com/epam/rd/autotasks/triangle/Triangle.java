package com.epam.rd.autotasks.triangle;

class Triangle {

    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {

        if (a == null || b == null || c == null) {
            throw new IllegalArgumentException();
        }

        this.a = a;
        this.b = b;
        this.c = c;

        if (this.area() == 0.0) {
            throw new IllegalArgumentException();
        }
    }

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        this.b = b;
    }

    public Point getC() {
        return c;
    }

    public void setC(Point c) {
        this.c = c;
    }

    public double area() {

        double part1 = a.getX() * (b.getY() - c.getY());
        double part2 = b.getX() * (c.getY() - a.getY());
        double part3 = c.getX() * (a.getY() - b.getY());

        double result = part1 + part2 + part3;

        return Math.abs(result) / 2.0;
    }

    public Point centroid() {

        double x = (a.getX() + b.getX() + c.getX()) / 3.0;
        double y = (a.getY() + b.getY() + c.getY()) / 3.0;

        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "Triangle{" + "a=" + a + ", b=" + b + ", c=" + c + '}';
    }
}
