package percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] ifOpen;
    private final int topIndex;
    private final int bottomIndex;
    private final WeightedQuickUnionUF wf;
    private final int size;
    private int openSitesCount;

    public Percolation(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = size;
        this.ifOpen = new boolean[size][size];
        this.bottomIndex = size * size + 1;
        this.topIndex = 0;
        this.openSitesCount = 0;
        this.wf = new WeightedQuickUnionUF(size * size + 2);
    }

    public void open(int i, int j) {
        if (i <= 0 || j <= 0 || i > this.size || j > this.size) {
            throw new IllegalArgumentException();
        }
        if (!this.ifOpen[i - 1][j - 1]) {
            int index = get1DIndex(i, j);
            this.ifOpen[i - 1][j - 1] = true;
            openSitesCount++;
            // any first row block,connect directly to top virtual block(at position 0)
            if (i == 1) {
                wf.union(index, topIndex);
            }

            // any last row block,connect directly to bottom virtual block(at
            // position n*n+1)
            if (i == this.size) {
                wf.union(index, bottomIndex);
            }
            // for remmaining middle blocks
            if (i > 1 && isOpen(i - 1, j)) {
                wf.union(index, get1DIndex(i - 1, j));
            }

            if (i < this.size && isOpen(i + 1, j)) {
                wf.union(index, get1DIndex(i + 1, j));
            }

            if (j > 1 && isOpen(i, j - 1)) {
                wf.union(index, get1DIndex(i, j - 1));
            }

            if (j < this.size && isOpen(i, j + 1)) {
                wf.union(index, get1DIndex(i, j + 1));
            }
        }
        

    }

    public boolean isOpen(int i, int j) {
        if (i <= 0 || j <= 0 || i > this.size || j > this.size) {
            throw new IllegalArgumentException();
        }
        return this.ifOpen[i - 1][j - 1];
    }

    // check if top is connected to bottom
    public boolean percolates() {
        return wf.connected(topIndex, bottomIndex);
    }

    // A full site is an open site that can be connected to an open site in the  top row
    public boolean isFull(int i, int j) {
        if (i > 0 && i <= this.size && j > 0 && j <= this.size) {
            return wf.connected(topIndex, get1DIndex(i, j));
        } else {
            throw new IllegalArgumentException();
        }

    }

    public int numberOfOpenSites() {
        return openSitesCount;
    }

    private int get1DIndex(int i, int j) {
        return this.size * (i - 1) + j;
    }
}
