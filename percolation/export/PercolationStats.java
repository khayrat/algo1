import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by glende on 11.04.17.
 */
public class PercolationStats {
    private  double mean;
    private  double stddev;
    private  double confidenceLo;
    private  double confidenceHi;

    private void montecarlo(int n, int trails) {
        double[] opens = new double[trails];

        for (int i = 0; i<trails; i++) {
            double threshold = runUntilPerculation(n);
            opens[i] = threshold;
        }

        mean = StdStats.mean(opens);
        stddev = StdStats.stddev(opens);
        confidenceLo = mean - (1.96 * stddev) / Math.sqrt(trails);
        confidenceHi = mean + (1.96 * stddev) / Math.sqrt(trails);
    }

    private double runUntilPerculation(int n) {
        Percolation p = new Percolation(n);

        while (true) {
            if (p.percolates()) break;

            int row = chooseRandom(n);
            int col = chooseRandom(n);
            p.open(row, col);
        }

        return (1.0*p.numberOfOpenSites()) / (n*n);
    }

    private int chooseRandom(int n) {
        return StdRandom.uniform(1, n+1);
    }

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        montecarlo(n, trials);
    }

    // sample mean of percolation threshold
    public double mean()  {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo()  {
        return this.confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()   {
        return this.confidenceHi;
    }

    // test client (described below)
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);

        StdOut.printf("mean                    = %f\n", ps.mean());
        StdOut.printf("stddev                  = %f\n", ps.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceLo());
    }
}
