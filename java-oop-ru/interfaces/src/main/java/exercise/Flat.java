package exercise;

// BEGIN
public class Flat implements Home {

    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public String toString() {
        return "Квартира площадью " + String.valueOf(getArea()) + " метров " + "на " + String.valueOf(floor) + " этаже"; // "Квартира площадью 56 метров на 5 этаже"
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
