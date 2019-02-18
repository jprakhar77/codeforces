package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AFrog1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] h = in.nextIntArray(n);

        int[] dp = new int[n];

        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + Math.abs(h[i] - h[i - 1]);

            if (i > 1) {
                dp[i] = Math.min(dp[i], dp[i - 2] + Math.abs(h[i] - h[i - 2]));
            }
        }

        out.println(dp[n - 1]);
    }
}
