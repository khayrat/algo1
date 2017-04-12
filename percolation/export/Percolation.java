import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int boundary;
    private final int N;
    private final boolean[] open;
    private final WeightedQuickUnionUF grid;
    private final WeightedQuickUnionUF full;
    private int opens;
    private final int top;
    private final int bottom;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n <= 0: " + n);
        N = n;
        boundary = N * N;
        open = new boolean[boundary + 2];
        grid = new WeightedQuickUnionUF(boundary + 2);
        full = new WeightedQuickUnionUF(boundary + 1);
        open[0] = true;
        open[boundary + 1] = true;
        top = 0;
        bottom = boundary + 1;
    }

    private int twoDToOneD(int row, int col) {
        return 1 + (row - 1) * N + col - 1;
    }

    private boolean valid(int row, int col) {
        if (row <= 0 || col <= 0) return false;
        if (row > N || col > N) return false;
        return true;
    }

    private void connect(int row1, int col1, int row2, int col2) {
        if (valid(row2, col2)) {
            if (isOpen(row2, col2)) {
                grid.union(twoDToOneD(row1, col1), twoDToOneD(row2, col2));
                full.union(twoDToOneD(row1, col1), twoDToOneD(row2, col2));
            }
        }
    }

    private void connectTop(int row, int col) {
        connect(row, col, row - 1, col);

        if (row == 1) {
            grid.union(twoDToOneD(row, col), top);
            full.union(twoDToOneD(row, col), top);
        }
    }

    private void connectBottom(int row, int col) {
        connect(row, col, row + 1, col);

        if (row == N) {
            grid.union(twoDToOneD(row, col), bottom);
        }
    }

    private void connectLeft(int row, int col) {
        connect(row, col, row, col - 1);
    }

    private void connectRight(int row, int col) {
        connect(row, col, row, col + 1);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;

        opens++;
        open[twoDToOneD(row, col)] = true;

        connectTop(row, col);
        connectBottom(row, col);
        connectLeft(row, col);
        connectRight(row, col);
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!valid(row, col)) {
            throw new IndexOutOfBoundsException("invalid boundary");
        }

        return open[twoDToOneD(row, col)];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!valid(row, col)) {
            throw new IndexOutOfBoundsException("invalid boundary");
        }

        return full.connected(0, twoDToOneD(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return opens;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(0, boundary + 1);
    }
}
