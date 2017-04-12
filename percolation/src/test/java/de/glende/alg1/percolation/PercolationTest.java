package de.glende.alg1.percolation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PercolationTest {
    @Test (expectedExceptions = IllegalArgumentException.class)
    public void nIsZero() {
        int N = 0;
        new Percolation(N);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void nIsLessThanZero() {
        int N = -1;
        new Percolation(N);
    }

    @Test
    public void upperLeft_1() {
        int N = 1;
        Percolation p = new Percolation(N);
        int oneDim = p.twoDToOneD(1, 1);
        Assert.assertEquals(oneDim, 1);
    }

    @Test
    public void upperLeft_10() {
        int N = 10;
        Percolation p = new Percolation(N);
        int oneDim = p.twoDToOneD(1, 1);
        Assert.assertEquals(oneDim, 1);
    }

    @Test
    public void upperRight_10() {
        int N = 10;
        Percolation p = new Percolation(N);
        int oneDim = p.twoDToOneD(1, 10);
        Assert.assertEquals(oneDim, 10);
    }

    @Test
    public void secondRowFirstCol() {
        int N = 10;
        Percolation p = new Percolation(N);
        int oneDim = p.twoDToOneD(2, 1);
        Assert.assertEquals(oneDim, 11);
    }

    @Test
    public void last() {
        int N = 10;
        Percolation p = new Percolation(N);
        int oneDim = p.twoDToOneD(10, 10);
        Assert.assertEquals(oneDim, 100);
    }

    @Test
    public void notValid() {
        int N = 10;
        Percolation p = new Percolation(N);

        assertFalse(p, 0, 1);
        assertFalse(p, 1, 0);
        assertFalse(p, 0, 0);
        assertFalse(p, -1, 1);
        assertFalse(p, 1, -1);

        assertFalse(p, 11, 0);
        assertFalse(p, 10, 11);

        assertFalse(p, 11, 1);
        assertFalse(p, 1, 11);
    }

    private void assertFalse(Percolation p, int r, int c) {
        Assert.assertFalse(p.valid(r, c), "r: " +r +", c: " +c);
    }

    @Test
    public void valid() {
        int N = 10;
        Percolation p = new Percolation(N);

        for (int r = 1; r <= N; r++)
            for (int c = 1; c <= N; c++)
                Assert.assertTrue(p.valid(r,c), "r: " +r +", c: " +c);
    }

    @Test (expectedExceptions = IndexOutOfBoundsException.class)
    public void openInvalid() {
        int N = 10;
        Percolation p = new Percolation(N);
        p.open(1, 11);
    }

    @Test
    public void openTest() {
        int N = 10;
        Percolation p = new Percolation(N);

        Assert.assertEquals(p.numberOfOpenSites(), 0, "should be 0.");

        Assert.assertEquals(p.isOpen(1,1), false, "should be closed.");
        p.open(1,1);
        Assert.assertEquals(p.isOpen(1,1), true, "should be open.");

        Assert.assertEquals(p.numberOfOpenSites(), 1, "should be 1.");
    }

    @Test
    public void connectTest() {
        int N = 10;
        Percolation p = new Percolation(N);

        Assert.assertFalse(p.percolates());
        p.open(1,1);
        Assert.assertFalse(p.percolates());
        p.open(2,1);
        Assert.assertFalse(p.percolates());
        p.open(3,1);
        Assert.assertFalse(p.percolates());
        p.open(4,1);
        Assert.assertFalse(p.percolates());
        p.open(5,1);
        Assert.assertFalse(p.percolates());
        p.open(6,1);
        Assert.assertFalse(p.percolates());
        p.open(7,1);
        Assert.assertFalse(p.percolates());
        p.open(8,1);
        Assert.assertFalse(p.percolates());
        p.open(9,1);
        Assert.assertFalse(p.percolates());
        p.open(10,1);
        Assert.assertTrue(p.percolates());
    }
}