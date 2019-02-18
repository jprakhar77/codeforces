package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class MCandies {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] a = in.nextIntArray(n);

        long[][] dp = new long[n][k + 1];

        for (int j = 0; j <= a[0]; j++) {
            dp[0][j] = j + 1;
        }

        for (int j = a[0] + 1; j <= k; j++) {
            dp[0][j] = a[0] + 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j > a[i]) {
                    dp[i][j] -= dp[i - 1][j - a[i] - 1];
                }

                dp[i][j] %= mod;
            }

            for (int j = 1; j <= k; j++) {
                dp[i][j] += dp[i][j - 1];
                dp[i][j] %= mod;
            }
        }

        long ans = dp[n - 1][k];

        if (k > 0)
            ans -= dp[n - 1][k - 1];

        if (ans < 0)
            ans += mod;

        out.println(ans);
    }

    int mod = 1000000007;
}
