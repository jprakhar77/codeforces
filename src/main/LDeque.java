package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class LDeque {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        long[][] dp = new long[n][n];

        long tot = 0;

        for (int val : a) {
            tot += val;
        }

        if ((n - 1) % 2 == 0) {
            for (int i = 0; i < n; i++) {
                dp[i][i] = a[i];
            }
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;

                int rec = n - l;

                if (rec % 2 == 0) {
                    dp[i][j] = Math.max(dp[i][j - 1] + a[j], a[i] + dp[i + 1][j]);
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        out.println(2 * dp[0][n - 1] - tot);
    }
}
