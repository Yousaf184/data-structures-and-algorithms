import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] percolationThresholds;
    private double mean, stdDev;
    private final double zValue;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("invalid arguments");
        }

        this.percolationThresholds = new double[trials];

        this.zValue = 1.96;
        int randomRow, randomCol;
        double threshold;
        Percolation percolation;

        // Monte Carlo simulation
        for (int i = 1; i <= trials; i++) {
            percolation = new Percolation(n);

            while (!percolation.percolates()) {
                // (0, 0) --> grid[0][0]
                randomRow = StdRandom.uniform(1, n + 1);
                randomCol = StdRandom.uniform(1, n + 1);

                percolation.open(randomRow, randomCol);
            }

            threshold = (double) percolation.numberOfOpenSites() / (n * n);
            this.percolationThresholds[i - 1] = threshold;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        mean = StdStats.mean(percolationThresholds);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        stdDev = StdStats.stddev(percolationThresholds);
        return stdDev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - ((zValue * stdDev) / Math.sqrt(percolationThresholds.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + ((zValue * stdDev) / Math.sqrt(percolationThresholds.length));
    }

   // test client (see below)
   public static void main(String[] args) {
        int gridSize = Integer.parseInt(args[0]);
        int trialCount = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(gridSize, trialCount);
//        PercolationStats ps = new PercolationStats(200, 100);

        System.out.println("mean   \t\t\t\t\t = " + ps.mean());
        System.out.println("stddev \t\t\t\t\t = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
   }
}
