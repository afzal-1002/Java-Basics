package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure {

    private Point a;
    private Point b;
    private Point c;
    private Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {

        if (a == null || b == null || c == null || d == null) {
            throw new IllegalArgumentException();
        }

        double first = cross(a, b, c);
        double second = cross(b, c, d);
        double third = cross(c, d, a);
        double fourth = cross(d, a, b);

		boolean firstPositive = first > EPS;
		boolean secondPositive = second > EPS;
		boolean thirdPositive = third > EPS;
		boolean fourthPositive = fourth > EPS;

		boolean allPositive = firstPositive && secondPositive && thirdPositive && fourthPositive;

		boolean firstNegative = first < -EPS;
		boolean secondNegative = second < -EPS;
		boolean thirdNegative = third < -EPS;
		boolean fourthNegative = fourth < -EPS;

		boolean allNegative = firstNegative && secondNegative && thirdNegative && fourthNegative;

        if (!allPositive && !allNegative) { throw new IllegalArgumentException();}

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

	@Override
	public Point centroid() {

		double firstArea = Math.abs(cross(a, b, c)) / 2.0;
		double secondArea = Math.abs(cross(a, c, d)) / 2.0;

		double firstX = (a.getX() + b.getX() + c.getX()) / 3.0;
		double firstY = (a.getY() + b.getY() + c.getY()) / 3.0;

		double secondX = (a.getX() + c.getX() + d.getX()) / 3.0;
		double secondY = (a.getY() + c.getY() + d.getY()) / 3.0;

		Point firstCentroid = new Point(firstX, firstY);
		Point secondCentroid = new Point(secondX, secondY);

		double totalArea = firstArea + secondArea;

		double x1 = firstCentroid.getX() * firstArea;
		double x2 = secondCentroid.getX() * secondArea;
		double x = (x1 + x2) / totalArea;

		double y1 = firstCentroid.getY() * firstArea;
		double y2 = secondCentroid.getY() * secondArea;
		double y = (y1 + y2) / totalArea;

		return new Point(x, y);
	}

    @Override
    public boolean isTheSame(Figure figure) {

        if (figure == null || getClass() != figure.getClass()) { return false; }

        Quadrilateral other = (Quadrilateral) figure;

        Point[] first = {a, b, c, d};
        Point[] second = { other.a, other.b, other.c, other.d };

        return sameVertices(first, second);
    }
}