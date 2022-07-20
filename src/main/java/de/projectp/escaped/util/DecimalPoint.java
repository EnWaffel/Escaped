package de.projectp.escaped.util;

public class DecimalPoint {

    public double x;
    public double y;

    public DecimalPoint() {
    }

    public DecimalPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return (int) x;
    }

    public int y() {
        return (int) y;
    }

    public DecimalPoint add(DecimalPoint point) {
        return new DecimalPoint(x + point.x, y + point.y);
    }

    public DecimalPoint sub(DecimalPoint point) {
        return new DecimalPoint(x - point.x, y - point.y);
    }

    @Override
    public String toString() {
        return "DecimalPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
