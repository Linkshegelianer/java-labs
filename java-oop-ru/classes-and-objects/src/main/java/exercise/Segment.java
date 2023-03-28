package exercise;

// BEGIN
public class Segment {

    Point begin;
    Point end;
    public Segment(Point begin, Point end) {
        if (begin == null || end == null) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        if (begin.equals(end)) {
            throw new IllegalArgumentException("The points must differ.");
        }
        this.begin = begin;
        this.end = end;
    }

    public Point getBeginPoint() {
        return begin;
    }

    public Point getEndPoint() {
        return end;
    }

    public Point getMidPoint() {
        int midpoint_x = (begin.x + end.x) / 2;
        int midpoint_y = (begin.y + end.y) / 2;
        Point midpoint = new Point(midpoint_x, midpoint_y);
        return midpoint;
    }
}
// END
