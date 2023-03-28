package exercise;

// BEGIN
public class Cottage implements Home {

    private double area;
    private int floorCount;
    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public double getArea() {
        return area;
    }

    @Override
    public String toString() {
        return String.valueOf(floorCount) + " этажный коттедж площадью " + String.valueOf(area) + " метров";
    }

    @Override
    public int compareTo(Home another) {
        double thisArea = this.getArea();
        double otherArea = another.getArea();

        if (thisArea > otherArea) {
            return -1;
        } else if (thisArea < otherArea) {
            return 1;
        } else {
            return 0;
        }
    }
}
// END
