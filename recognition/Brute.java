import java.util.Arrays;

/**
 * 
 * @author Timur Nasibullin
 * 
 */
public class Brute {
    private static final double ACCURACY = 0.0001;
    private static final String SEPARATOR = " -> ";

    public static void main(String[] args) {
        Point[] points = readPoints(args);
        int N = points.length;
        Arrays.sort(points);

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N - 2; j++) {
                for (int k = j + 1; k < N - 1; k++) {
                    for (int l = k + 1; l < N; l++) {
                        Point a = points[i];
                        Point b = points[j];
                        Point c = points[k];
                        Point d = points[l];
                        double slope = a.slopeTo(d)*a.compareTo(d);
                        if (approxEquals(slope, a.slopeTo(b)*a.compareTo(b))
                                && approxEquals(slope, a.slopeTo(c) * a.compareTo(c))) {
                            StdOut.println(a + SEPARATOR + b + SEPARATOR + c + SEPARATOR + d);
                            a.drawTo(d);
                        }
                    }
                }
            }
        }

        StdDraw.setPenRadius(0.006);
        StdDraw.setPenColor(StdDraw.RED);

        for (int i = 0; i < N; i++) {
            points[i].draw();
        }
    }

    private static Point[] readPoints(String[] args) {
        In in = new In(args[0]);
        Point[] points = new Point[in.readInt()];

        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(in.readInt(), in.readInt());
        }
        return points;
    }

    private static boolean approxEquals(double v1, double v2) {
        return Math.abs(v1 - v2) < ACCURACY;
    }
}
