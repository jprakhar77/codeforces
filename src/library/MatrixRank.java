package library;

import java.util.Arrays;

public class MatrixRank {

    //Rank of a matrix over FF(2) (xor add) where rows can be represented
    //by integers, ie, upper range of a number in a row is 2^30 - 1.
    int matrixRankFF2(int[] a) {
        int n = a.length;

        int ans = 0;

        for (int i = 29; i >= 0; i--) {
            int num = -1;

            for (int j = 0; j < n; j++) {
                if (((1 << i) & a[j]) != 0 && a[j] <= (1 << (i + 1)) - 1) {
                    if (num == -1) {
                        num = a[j];
                        ans++;
                    } else {
                        a[j] ^= num;
                    }
                }
            }
        }

        return ans;
    }

    //Rank of a matrix over FF(2) (xor add)
    int matrixRankFF2(int[][] a) {
        int n = a.length;
        int m = a[0].length;

        int rank = 0;

        boolean[] done = new boolean[n];

        for (int i = 0; i < m; i++) {
            int ind = -1;
            for (int j = 0; j < n; j++) {
                if (a[j][i] == 1 && !done[j]) {
                    ind = j;
                    break;
                }
            }

            if (ind == -1)
                continue;

            done[ind] = true;
            rank++;

            for (int j = ind + 1; j < n; j++) {
                if (a[j][i] == 1) {
                    for (int k = i; k < m; k++) {
                        a[j][k] ^= a[ind][k];
                    }
                }
            }
        }

        return rank;
    }
}