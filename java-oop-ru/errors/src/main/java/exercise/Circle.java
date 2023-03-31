package exercise;

// BEGIN
public class Circle {

    private final Point point;

    private final int radix;

    public Circle(Point point, int radix) {
        this.point = point;
        this.radix = radix;
    }

    public int getRadius() {
        return radix;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radix < 0) {
            throw new NegativeRadiusException("Не удалось посчитать площадь");
        }
        return Math.PI * (radix * radix);
    }
}
// END
