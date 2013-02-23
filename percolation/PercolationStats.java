/**
 * Implementation of Monte Carlo simulation to estimate the percolation
 * threshold.
 * 
 * @author Timur Nasibullin
 */
public class PercolationStats {
    private double mean; // sample mean of percolation threshold

    private double stddev; // sample standard deviation of percolation threshold

    private double confidenceLo; // lower bound of the 95% confidence interval

    private double confidenceHi; // upper bound of the 95% confidence interval

    /**
     * Performs T independent computational experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalStateException();
        }

        int size = N * N;
        double[] x = new double[T];
        for (int i = 0; i < T; i++) {
            int open = run(N);
            x[i] = (double) open / size;
        }
        mean = StdStats.mean(x);
        if (T > 1) {
            stddev = StdStats.stddev(x);
            double confidenceInt = 1.96 * stddev / Math.sqrt(T);
            confidenceLo = mean - confidenceInt;
            confidenceHi = mean + confidenceInt;
        }
    }

    /**
     * Performs one computational experiment on an N-by-N grid
     */
    private int run(int N) {
        Percolation percolation = new Percolation(N);
        int open = 0;
        int i = StdRandom.uniform(N);
        int j = StdRandom.uniform(N);
        do {
            while (percolation.isOpen(i + 1, j + 1)) {
                i = StdRandom.uniform(N);
                j = StdRandom.uniform(N);
            }
            percolation.open(i + 1, j + 1);
            open++;
        } while (!percolation.percolates());
        return open;
    }

    /**
     * Returns sample mean of percolation threshold
     */
    public double mean() {
        return mean;
    }

    /**
     * Returns sample standard deviation of percolation threshold
     */
    public double stddev() {
        return stddev;
    }

    /**
     * Returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return confidenceLo;
    }

    /**
     * Returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            int N = Integer.parseInt(args[0]);
            int T = Integer.parseInt(args[1]);
            PercolationStats stats = new PercolationStats(N, T);
            System.out.println("mean = " + stats.mean());
            System.out.println("stddev = " + stats.stddev());
            System.out.println("95% confidence interval = "
                    + stats.confidenceLo + "," + stats.confidenceHi);
        }
    }
}