package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DKnapsack1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        int W = in.nextInt();

        int[] w = new int[n];
        int[] v = new int[n];

        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
            v[i] = in.nextInt();
        }

        long[][] dp = new long[W + 1][n + 1];

        for (int i = 1; i <= W; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1];

                if (w[j - 1] <= i) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - w[j - 1]][j - 1] + v[j - 1]);
                }
            }
        }

        out.println(dp[W][n]);
    }
}
