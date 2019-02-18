package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1027E {
    int mod = 998244353;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        long[][][] dp = new long[2][n + 1][n + 1];

        if (k == 1) {
            out.println(0);
            return;
        }

        long[][][] dpl = new long[2][n + 1][n + 1];

        dpl[0][1][1] = 2;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int l = 1; l <= n; l++) {
                    if (dpl[0][j][l] > 0) {
                        if (l < k - 1) {
                            if (l + 1 > j) {
                                dpl[1][l + 1][l + 1] += dpl[0][j][l];
                                dpl[1][l + 1][l + 1] %= mod;
                            } else {
                                dpl[1][j][l + 1] += dpl[0][j][l];
                                dpl[1][j][l + 1] %= mod;
                            }
                        }
                        dpl[1][j][1] += dpl[0][j][l];
                        dpl[1][j][1] %= mod;
                    }
                }
            }
            for (int j = 1; j <= n; j++) {
                for (int l = 1; l <= n; l++) {
                    dpl[0][j][l] = dpl[1][j][l];
                    dpl[1][j][l] = 0;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[0][i][1] += dpl[0][i][j];
            }
            dp[0][i][1] %= mod;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int h = 1; h <= i; h++) {
                    if (dp[0][j][h] > 0) {
                        if ((h + 1) * j < k) {
                            dp[1][j][h + 1] += dp[0][j][h];
                            dp[1][j][h + 1] %= mod;
                        }
                        dp[1][j][1] += dp[0][j][h];
                        dp[1][j][1] %= mod;
                    }
                }
            }
            for (int j = 1; j <= n; j++) {
                for (int l = 1; l <= n; l++) {
                    dp[0][j][l] = dp[1][j][l];
                    dp[1][j][l] = 0;
                }
            }
        }

        long ans = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                ans += dp[0][i][j];
            }
            ans %= mod;
        }

        out.println(ans);
    }

}
