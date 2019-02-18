package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class EOddSubrectangles {
    class MatrixRank {

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

    long pow(long a, long p, int mod) {
        if (p == 0) {
            return 1;
        }

        long t = pow(a, p / 2, mod);

        if (p % 2 != 0) {
            return (((t * t) % mod) * a) % mod;
        } else {
            return (t * t) % mod;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();
        int m = in.nextInt();
        int[][] a = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }

        MatrixRank rank = new MatrixRank();

        int r = rank.matrixRankFF2(a);

        long ans = pow(2, n + m - 1, mod);
        ans -= pow(2, n + m - r - 1, mod);

        ans %= mod;

        if (ans < 0) {
            ans += mod;
        }

        out.println(ans);
    }

    int mod = 998244353;
}
