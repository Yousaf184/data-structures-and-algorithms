import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
    private int[][] grid;
    private final int gridSize;
    private int openSitesCount;
    // two union find data structures to avoid backwash problem
    // has only top virtual site
    private final WeightedQuickUnionUF wquf;
    // it will be used just to know if system percolates
    // has top and bottom virtual sites
    private final WeightedQuickUnionUF wquf2;
    // all top row sites will be connected to virtual top site
    // will minimize calls to connected method in WeightedQuickUnionUF class
    private final int virtualTopSiteIndex;
    private final int virtualBottomSiteIndex;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("'n' cannot be less than 1");
        }

        // grid
        // 0 --> blocked
        // 1 --> open
        // every index will be initialized with 0 by default
        this.grid = new int[n][n];
        this.openSitesCount = 0;
        this.gridSize = n;
        this.virtualTopSiteIndex = (gridSize * gridSize);
        this.virtualBottomSiteIndex = (gridSize * gridSize) + 1;
        // add 1 to accommodate virtual top site in union find data structure
        this.wquf = new WeightedQuickUnionUF((gridSize * gridSize) + 1);
        // add 2 to accommodate virtual sites in union find data structure
        this.wquf2 = new WeightedQuickUnionUF((gridSize * gridSize) + 2);
    }

    // opens the site (row, col) if it is not open already
    // (1, 1) --> grid[0][0]
    public void open(int row, int col) {
        if (!validateCoordinates(row - 1, col - 1)) {
            throw new IllegalArgumentException("invalid (row, col) value provided");
        }

        if (isOpen(row, col)) return;

        // 1 --> open site
        grid[row - 1][col - 1] = 1;
        openSitesCount++;

        // top neighbour
        if (row > 1 && isOpen(row - 1, col)) {
            performUnion(row, col, row - 1, col);
        }

        // right neighbour
        if (col <= gridSize - 1 && isOpen(row, col + 1)) {
            performUnion(row, col, row, col + 1);
        }

        // down neighbour
        if (row <= gridSize - 1 && isOpen(row + 1, col)) {
            performUnion(row, col, row + 1, col);
        }

        // left neighbour
        if (col > 1 && isOpen(row, col - 1)) {
            performUnion(row, col, row, col - 1);
        }

        // connect to virtual top site if newly opened
        // site is in row 1 (top row)
        if (row == 1) {
            int indexP = xyToOneD(row, col);
            wquf.union(indexP, virtualTopSiteIndex);
            wquf2.union(indexP, virtualTopSiteIndex);
        } else if (row == gridSize) {
            int indexP = xyToOneD(row, col);
            wquf2.union(indexP, virtualBottomSiteIndex);
        }
    }

    // is the site (row, col) open?
    // (1, 1) --> grid[0][0]
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
            throw new IllegalArgumentException("array index out of bounds");
        }

        // 1 --> open site
        return grid[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    // (1, 1) --> grid[0][0]
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }

        int indexP = xyToOneD(row, col);
        return wquf.connected(indexP, virtualTopSiteIndex);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSitesCount;
    }

    // does the system percolate?
    // (1, 1) --> grid[0][0]
    public boolean percolates() {
        if (openSitesCount < gridSize) {
            return false;
        }

        if (gridSize == 1 && openSitesCount == 1) {
            return true;
        }

        return wquf2.connected(virtualTopSiteIndex, virtualBottomSiteIndex);
    }

    private boolean validateCoordinates(int row, int col) {
        return row > -1 && row < grid.length
                && col > -1 && col < grid.length;
    }

    // returns a number between 0 to N
    // corresponding to numbers in 1d array of size N
    // (1, 1) --> grid[0][0]
    private int xyToOneD(int row, int col) {
        return ((row - 1) * grid.length) + (col - 1);
    }

    private void performUnion(int row1, int col1, int row2, int col2) {
        int indexP = xyToOneD(row1, col1);
        int indexQ = xyToOneD(row2, col2);
        wquf.union(indexP, indexQ);
        wquf2.union(indexP, indexQ);
    }

//    public void print() {
//        for (int i=0; i<gridSize; i++) {
//            for (int j=0; j<gridSize; j++) {
//                System.out.print(grid[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }

    // test client (optional)
//   public static void main(String[] args) {
////        Percolation p = new Percolation(5);
////
////        p.open(1, 1);
////        p.open(2, 1);
////        p.open(2, 2);
////        p.open(3, 3);
////        p.open(4, 4);
////        p.open(5, 5);
////        p.open(5, 4);
////        p.open(3, 4);
////        p.open(2, 4);
////        p.open(2, 3);
////        p.open(5, 1);
//
//       Percolation p = new Percolation(3);
//
//       p.open(1, 1);
//       p.open(2, 1);
//       p.open(3, 3);
//       p.open(2, 2);
//       p.open(2, 3);
//
//        p.print();
//
//        System.out.println(p.percolates());
//   }
}
