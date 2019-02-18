package library;

public class Combinatorics {

    int[][] nCr(int n, int r) {
        int[][] ncr = new int[n + 1][r + 1];

        ncr[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            ncr[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= r; j++) {
                ncr[i][j] = ncr[i - 1][j - 1] + ncr[i - 1][j];
            }
        }

        return ncr;
    }

    int[][] nCr(int n, int r, int mod) {
        int[][] ncr = new int[n + 1][r + 1];

        ncr[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            ncr[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= r; j++) {
                ncr[i][j] = ncr[i - 1][j - 1] + ncr[i - 1][j];
                ncr[i][j] %= mod;
            }
        }

        return ncr;
    }
}
