import java.util.Arrays;

/**
 * Sorting-based implementation of collinear points recognition. 
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
        Arrays.sort(points);

        for (int i = 0; i < N; i++) {
            Arrays.sort(points, i + 1, N, points[i].SLOPE_ORDER);
            int k = i + 1;
            for (int j = i + 2; j < N; j++) {
                if (points[i].SLOPE_ORDER.compare(points[j], points[j - 1]) != 0) {
                    if (j - k >= 3) {
                        outputLine(points, i, k, j);
                    }
                    k = j;
                }
            }
            if (N - k >= 3) {
                outputLine(points, i, k, N);
            }
        }
    }

    private static void outputLine(Point[] points, int cur, int lo, int hi) {
        Point[] buf = new Point[hi - lo + 1];
        buf[0] = points[cur];
        System.arraycopy(points, lo, buf, 1, buf.length - 1);
        Arrays.sort(buf);
        for (int i = 0; i < buf.length; i++) {
            StdOut.print(buf[i]);
            if (i < buf.length - 1) {
                StdOut.print(SEPARATOR);
            }
        }
        StdOut.println();
        buf[0].drawTo(buf[buf.length - 1]);
    }

    private static Point[] readPoints(String[] args) {
        StdDraw.setPenRadius(0.006);
        StdDraw.setPenColor(StdDraw.RED);

        In in = new In(args[0]);
        Point[] points = new Point[in.readInt()];

        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(in.readInt(), in.readInt());
            points[i].draw();
        }

        StdDraw.setPenRadius();
        StdDraw.setPenColor();
        
        return points;
    }
}