package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class TaskF1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();

        int[] a = in.nextIntArray(n);

        long[][][] dp = new long[n][x + 1][k];
        long[][][] dpsum = new long[n][x + 1][k];

        if (k == 1) {
            if (x != n) {
                out.print(-1);
            } else {
                long sum = 0;
                for (int val : a) {
                    sum += val;
                }
                out.print(sum);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= x; j++) {
                for (int l = 0; l < k; l++) {
                    dp[i][j][l] = -1;
                }
            }
        }

        dp[0][1][0] = a[0];
        dp[0][0][1] = 0;

        for (int j = 0; j <= x; j++) {
            dpsum[0][j][0] = dp[0][j][0];
            for (int l = 1; l < k; l++) {
                dpsum[0][j][l] = Math.max(dpsum[0][j][l - 1], dp[0][j][l]);
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= x; j++) {
                for (int l = 0; l < k; l++) {
//                    if (j == 0) {
//                        if (i >= k)
//                            continue;
//                    }
//                    if (l > 0 && j > 0 && dp[i - 1][j - 1][l - 1] != -1) {
//                        dp[i][j][l] = dp[i - 1][j - 1][l - 1] + a[i];
//                    }

                    if (l == 0) {
                        if (j == 0)
                            continue;

                        if (dpsum[i - 1][j - 1][k - 1] != -1)
                            dp[i][j][l] = dpsum[i - 1][j - 1][k - 1] + a[i];
                    } else {
                        dp[i][j][l] = dp[i - 1][j][l - 1];
                    }
                }
            }

            for (int j = 0; j <= x; j++) {
                dpsum[i][j][0] = dp[i][j][0];
                for (int l = 1; l < k; l++) {
                    dpsum[i][j][l] = Math.max(dpsum[i][j][l - 1], dp[i][j][l]);
                }
            }

        }

        out.print(dpsum[n - 1][x][k - 1]);
    }
}
