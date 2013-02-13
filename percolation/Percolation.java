

public class Percolation {
    /**
     * The instance of quick union algorithm 
     */
    private WeightedQuickUnionUF uf;

    /**
     * The size of grid.
     */
    private int n;

    /**
     * N-by-N grid, false value indicates that site is blocked.
     */
    private boolean grid[][];
    
    /**
     * Creates N-by-N grid, with all sites blocked.
     */
    public Percolation(int N) {
        this.n = N;
        grid = new boolean[n][n];
        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++)
                grid[i][j] = false;
        uf = new WeightedQuickUnionUF(n*n + 2);
    }

    /**
     * Opens site (row i, column j) if it is not already. 
     */
    public void open(int i, int j) {
        checkIndices(i, j);
        if (!get(i,j)) {
            grid[i-1][j-1] = true;
            int p = getFlatIndex(i, j);
            if (i > 1 && get(i-1, j)) {
                uf.union(p, getFlatIndex(i-1, j));
            }
            if (i < n && get(i+1,j)) {
                uf.union(p, getFlatIndex(i+1, j));
            }
            if (j > 1 && get(i, j-1)) {
                uf.union(p, getFlatIndex(i, j-1));
            }
            if (j < n && get(i, j+1)) {
                uf.union(p, getFlatIndex(i, j+1));
            }
        }
    }

    /**
     * Is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        checkIndices(i, j);
        return get(i,j);
    }

    /**
     * Is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        checkIndices(i, j);
        return get(i,j) && uf.connected(0, getFlatIndex(i,j));
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        return uf.connected(0, uf.count());
    }

    /**
     * Checks if indices are in valid range. 
     */
    private void checkIndices(int i, int j) {
        if (i < 1 || i > n || j < 1 || j > n) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean get(int i, int j) {
        return grid[i - 1][j - 1];
    }

    /**
     * Converts index of site in two-dimensional grid to flat index of one-dimensional array element (WeightedQuickUnionUF).  
     */
    private int getFlatIndex(int i, int j) {
        return (i - 1)*n + j;
    }
}