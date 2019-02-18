package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BFrog2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] h = in.nextIntArray(n);

        int[] dp = new int[n];

        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + Math.abs(h[i] - h[i - 1]);

            for (int j = i - 2; j >= Math.max(0, i - k); j--) {
                dp[i] = Math.min(dp[i], dp[j] + Math.abs(h[i] - h[j]));
            }
        }

        out.println(dp[n - 1]);
    }
}
