public class SumProduct {

    int mod = 1000000007;

    public int findSum(int[] amount, int blank1, int blank2) {
        int[][] ncr = nCr(200, 200, mod);

        long[][][][] dp = new long[10][10][200][11];

//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                dp[i][j][0][10] = 1;
//                for (int k = 9; k >= 0; k--) {
//                    int max = amount[k];
//                    if (i == k)
//                        max--;
//                    if (j == k)
//                        max--;
//                    for (int l = 0; l < 200; l++) {
//                        for (int m = 0; m <= Math.min(l, max); m++) {
//                            dp[i][j][l][k] += (ncr[l][m] * dp[i][j][l - m][k + 1]) % mod;
//                            dp[i][j][l][k] %= mod;
//                        }
//                    }
//                }
//            }
//        }

        long[] fac = new long[201];

        fac[0] = 1;

        for (int i = 1; i <= 200; i++) {
            fac[i] = i * fac[i - 1];
            fac[i] %= mod;
        }

        long[] ifac = new long[201];

        ifac[0] = 1;

        for (int i = 1; i <= 200; i++) {
            ifac[i] = pow(i, mod - 2, mod) * ifac[i - 1];
            ifac[i] %= mod;
        }


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                dp[i][j][0][10] = 1;
                for (int k = 9; k >= 0; k--) {
                    int max = amount[k];
                    if (i == k)
                        max--;
                    if (j == k)
                        max--;
                    for (int l = 0; l < 200; l++) {
                        for (int m = 0; m <= Math.min(l, max); m++) {
                            long val = ifac[m];
                            val *= dp[i][j][l - m][k + 1];
                            val %= mod;
                            dp[i][j][l][k] += val;
                            dp[i][j][l][k] %= mod;
                        }
                    }
                }
            }
        }

        long[] tp = new long[201];

        tp[0] = 1;

        for (int i = 1; i <= 200; i++) {
            tp[i] = tp[i - 1] * 10;
            tp[i] %= mod;
        }


        long ans = 0;
        for (int i = 0; i < blank1; i++) {
            for (int j = 0; j < blank2; j++) {
                for (int x = 0; x < 10; x++) {
                    for (int y = 0; y < 10; y++) {
                        long val = x;
                        val *= tp[i];
                        val %= mod;
                        val *= y;
                        val *= tp[j];
                        val %= mod;

                        val *= dp[x][y][blank1 + blank2 - 2][0];
                        val %= mod;

                        val *= fac[blank1 + blank2 - 2];
                        val %= mod;

                        ans += val;
                        ans %= mod;
                    }
                }
            }
        }

        return (int) ans;
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
}
