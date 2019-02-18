package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CColorfulBricks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int k = in.nextInt();

        long[][] dp = new long[n][k + 1];

        dp[0][0] = m;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                //sae
                dp[i][j] += dp[i - 1][j];
                dp[i][j] %= mod;

                //diff
                if (j < k) {
                    dp[i][j + 1] += dp[i - 1][j] * (m - 1);
                    dp[i][j + 1] %= mod;
                }
            }
        }

        out.println(dp[n - 1][k]);
    }

    int mod = 998244353;
}
