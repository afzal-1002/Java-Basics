package com.epam.rd.autotasks.figures;

class Triangle extends Figure {

    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {

        double first = a.getX() * (b.getY() - c.getY());
        double second = b.getX() * (c.getY() - a.getY());
        double third = c.getX() * (a.getY() - b.getY());

        return Math.abs(first + second + third) / 2;
    }

    @Override
    public String pointsToString() {
        return a.toString() + b.toString() + c.toString();
    }

    @Override
    public Point leftmostPoint() {

        Point leftmost = a;

        if (b.getX() < leftmost.getX()) {  leftmost = b; }
        if (c.getX() < leftmost.getX()) {  leftmost = c;}

        return leftmost;
    }
}