import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] openSiteFraction;
    private final int trials;
    private double mean;
    private double stddev;
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        Percolation percolation = null;
        this.trials = trials;
        openSiteFraction = new double[trials];
        for (int k = 0; k < trials; k++) {
            percolation = new Percolation(n);
            int openedSites = 0;
            while (!percolation.percolates()) {
                int i = StdRandom.uniform(1, n+1);
                int j = StdRandom.uniform(1, n+1);
                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    openedSites++;
                }
            }
            openSiteFraction[k] = (double) openedSites/(n*n);
        }
    }

    public double mean() {
        this.mean = StdStats.mean(openSiteFraction);
        return this.mean;
    }
    public double stddev() {
        this.stddev = StdStats.stddev(openSiteFraction);
        return this.stddev;
    }
    public double confidenceLo() {
        return this.mean-((CONFIDENCE_95*this.stddev)/Math.sqrt(this.trials));
    }
    public double confidenceHi() {
        return this.mean+((CONFIDENCE_95*this.stddev)/Math.sqrt(this.trials));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);
        StdOut.println("mean="+stats.mean());
        StdOut.println("stddev="+stats.stddev());
        StdOut.println("95% confidence interval=["+stats.confidenceLo()+","+stats.confidenceHi()+"]");
    }
}
