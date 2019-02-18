package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class EKnapsack2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int W = in.nextInt();

        int[] w = new int[n];
        int[] v = new int[n];

        int sumv = 0;
        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
            v[i] = in.nextInt();
            sumv += v[i];
        }

        long[][] dp = new long[sumv + 1][n + 1];

        for (int i = 1; i <= sumv; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = inf;
            }
        }

        for (int i = 1; i <= sumv; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1];

                dp[i][j] = Math.min(dp[i][j], dp[Math.max(0, i - v[j - 1])][j - 1] + w[j - 1]);
            }
        }

        int ans = 0;
        for (int i = 1; i <= sumv; i++) {
            if (dp[i][n] <= W) {
                ans = i;
            }
        }
        out.println(ans);
    }

    long inf = 1000000000000l;
}
