public class TieForMax {

    long[][] ncr;

    public double getProbability(int t, int n) {

        ncr = nCr(50, 50);

//        double[] pre = new double[t + 1];
//
//        for (int i = 0; i <= t; i++) {
//            pre[i] = cal(n, t, i);
//        }


        double[][][] pre = new double[51][51][51];

        for (int i = 0; i <= 50; i++) {
            for (int j = 0; j <= 50; j++) {
                for (int k = 0; k <= 50; k++) {
                    pre[i][j][k] = cal(i, j, k);
                }
            }
        }
        double[][][][] dp = new double[n][t + 1][t + 1][2];

        for (int x = 0; x <= t; x++) {
            dp[0][t - x][x][0] += pre[n][t][x];
        }

        for (int i = 1; i < n; i++) {
            //j = total rem
            for (int j = 0; j <= t; j++) {
                //k = largest till i - 1
                for (int k = 0; k <= t; k++) {
                    for (int x = 0; x <= j; x++) {
//                        if (i == n - 1 && x == j) {
//                            if (x == k) {
//                                dp[i][j - x][k][1] += (dp[i - 1][j][k][0] + dp[i - 1][j][k][1]);
//                            } else if (x > k) {
//                                dp[i][j - x][x][0] += (dp[i - 1][j][k][0] + dp[i - 1][j][k][1]);
//                            } else if (x < k) {
//                                dp[i][j - x][k][1] += dp[i - 1][j][k][1];
//                                dp[i][j - x][k][0] += dp[i - 1][j][k][0];
//                            }
//                        } else {
                        if (x == k) {
                            dp[i][j - x][k][1] += pre[n - i][j][x] * (dp[i - 1][j][k][0] + dp[i - 1][j][k][1]);
                        } else if (x > k) {
                            dp[i][j - x][x][0] += pre[n - i][j][x] * (dp[i - 1][j][k][0] + dp[i - 1][j][k][1]);
                        } else if (x < k) {
                            dp[i][j - x][k][1] += pre[n - i][j][x] * dp[i - 1][j][k][1];
                            dp[i][j - x][k][0] += pre[n - i][j][x] * dp[i - 1][j][k][0];
                        }
                        //}
                    }
                }
            }
        }

        double ans = 0;
        for (int k = 0; k <= t; k++) {
            ans += dp[n - 1][0][k][1];
        }

        return ans;
    }

    double cal(int n, int t, int x) {
        double val = ncr[t][x];

        for (int i = 0; i < x; i++) {
            val *= (1.0 / n);
        }

        for (int i = 0; i < t - x; i++) {
            val *= (((double) n - 1.0) / n);
        }

        return val;
    }

    long[][] nCr(int n, int r) {
        long[][] ncr = new long[n + 1][r + 1];

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
}
