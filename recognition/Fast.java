import java.util.Arrays;

/**
 * 
 * @author Timur Nasibullin
 * 
 */
public class Fast {
    private static final String SEPARATOR = " -> ";

    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        Point[] points = readPoints(args);
        int N = points.length;
        // Arrays.sort(points);

        for (int i = 0; i < N; i++) {
            Arrays.sort(points, i + 1, N, points[i].SLOPE_ORDER);
            int k = i + 1;
            for (int j = i + 2; j < N; j++) {
                if (points[i].SLOPE_ORDER.compare(points[j], points[j - 1]) != 0) {
                    if (j - k >= 4) {
                        outputLine(points, k, j);
                        k = j;
                    }
                }
            }
            if (N - k >= 4) {
                outputLine(points, k, N);
            }
        }

        StdDraw.setPenRadius(0.006);
        StdDraw.setPenColor(StdDraw.RED);

        for (int i = 0; i < N; i++) {
            points[i].draw();
        }
    }

    private static void outputLine(Point[] points, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            StdOut.print(points[i]);
            if (i < hi - 1) {
                StdOut.print(SEPARATOR);
            }
        }
        StdOut.println();
        points[lo].drawTo(points[hi - 1]);
    }

    private static Point[] readPoints(String[] args) {
        In in = new In(args[0]);
        Point[] points = new Point[in.readInt()];

        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(in.readInt(), in.readInt());
        }
        return points;
    }
}