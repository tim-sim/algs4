import java.util.Random;

public class PercolationStats {
	private double mean;
	private double stddev;
	private double confidenceLo;
	private double confidenceHi;

	/**
	 * perform T independent computational experiments on an N-by-N grid
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
			double confidenceInt = 1.96*stddev/Math.sqrt(T);
			confidenceLo = mean - confidenceInt;
			confidenceHi = mean + confidenceInt;
		}
	}

	private int run(int N) {
		Percolation percolation = new Percolation(N);
		int open = 0;
		Random rnd = new Random();
		int i = rnd.nextInt(N);
		int j = rnd.nextInt(N);
		do {
			while (percolation.isOpen(i + 1, j + 1)) {
				i = rnd.nextInt(N);
				j = rnd.nextInt(N);
			}
			percolation.open(i + 1, j + 1);
			open++;
		} while (!percolation.percolates());
		return open;
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
		if (args.length == 2) {
			int N = Integer.parseInt(args[0]);
			int T = Integer.parseInt(args[1]);
			PercolationStats stats = new PercolationStats(N, T);
			System.out.println("mean = " + stats.mean());
			System.out.println("stddev = " + stats.stddev());
			System.out.println("95% confidence interval = " + stats.confidenceLo + "," + stats.confidenceHi);
		}
	}
}