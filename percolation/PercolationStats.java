import java.util.Random;

public class PercolationStats {
    private int N;
    private int T;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    private double meanFactor;

    /**
     * perform T independent computational experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T) {
        this.N = N;
        this.T = T;
        mean = 0;
        stddev = 0;
        meanFactor = (T - 1)/T;
        for (int i = 0; i < T; i++) {
            run();
        }
    }

    private void run() {
        Percolation percolation = new Percolation(N);
        int open = 0;
        Random rnd = new Random();
        int i = rnd.nextInt(N);
        int j = rnd.nextInt(N);
        do {
            while (!percolation.isOpen(i + 1, j + 1)) {
                i = rnd.nextInt(N);
                j = rnd.nextInt(N);
            }
            percolation.open(i + 1, j + 1);
            open++;
        } while (!percolation.percolates());
        updateMean(open);
        updateStddev(open);
    }

    private void updateStddev(int open) {
        mean = mean * meanFactor + open/T;
    }

    private void updateMean(int open) {
    }

    /**
     * sample mean of percolation threshold
     */
    public double mean() {
        return mean;
    }

    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        return stddev;
    }

    /**
     * returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return confidenceLo;
    }

    /**
     * returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        
    }
}