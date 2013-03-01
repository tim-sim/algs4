/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {
    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point p1, Point p2) {
            int dx1 = p1.x - x;
            int dx2 = p2.x - x;
            int dy1 = p1.y - y;
            int dy2 = p2.y - y;
            if (dy1 == 0 && dx1 == 0) {
                return (dy2 == 0 && dx2 == 0 ? 0 : -1);
            }
            if (dy2 == 0 && dx2 == 0) {
                return 1;
            }
            if (dx1 == 0) {
                return (dx2 == 0 ? 0 : 1);
            }
            if (dx2 == 0) {
                return -1;
            }
            return Integer.signum(dy1 * dx2 - dy2 * dx1) 
                    * Integer.signum(dx1) * Integer.signum(dx2);
        }
    };

    private final int x; // x coordinate
    private final int y; // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (x == that.x) {
            if (y == that.y) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        if (y == that.y) {
            return 0.0;
        }
        return ((double) (that.y - y)) / ((double) (that.x - x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (y == that.y) {
            return x < that.x ? -1 : (x == that.x ? 0 : 1);

        }
        return y < that.y ? -1 : 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        Point p0 = new Point(1, 1);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);
        Point p3 = new Point(2, 1);
        Point p4 = new Point(2, 2);
        Point p5 = new Point(0, 1);

        assert p0.compareTo(p1) == 0;

        assert p1.compareTo(p2) == -1;
        assert p2.compareTo(p1) == 1;

        assert p1.compareTo(p3) == -1;
        assert p3.compareTo(p1) == 1;

        assert p1.compareTo(p4) == -1;
        assert p4.compareTo(p1) == 1;

        assert p1.compareTo(p5) == 1;
        assert p5.compareTo(p1) == -1;

        assert Double.compare(0.0, p1.slopeTo(p3)) == 0;
        assert p1.slopeTo(p2) == Double.POSITIVE_INFINITY;
        assert p1.slopeTo(p0) == Double.NEGATIVE_INFINITY;

        assert p1.SLOPE_ORDER.compare(p3, p3) == 0;
        assert p1.SLOPE_ORDER.compare(p4, p3) == 1;
        assert p1.SLOPE_ORDER.compare(p2, p4) == 1;
        assert p1.SLOPE_ORDER.compare(p3, p0) == 1;
    }
}
