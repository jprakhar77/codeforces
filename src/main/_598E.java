package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _598E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        dp = new int[31][31][51];

        for (int i = 0; i <= 30; i++) {
            for (int j = 0; j <= 30; j++) {
                for (int l = 0; l <= 50; l++) {
                    dp[i][j][l] = -1;
                }
            }
        }

        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();

            out.println(rec(n, m, k));
        }


    }

    int[][][] dp;
    int inf = 1000000;

    int rec(int n, int m, int k) {
        if (dp[n][m][k] != -1) {
            return dp[n][m][k];
        }

        if (k == 0) {
            return dp[n][m][k] = 0;
        }

//        if (n == 1) {
//            if (m == k)
//                return dp[n][m][k] = 0;
//            else
//                return dp[n][m][k] = 1;
//        }
//
//        if (m == 1) {
//            if (n == k)
//                return dp[n][m][k] = 0;
//            else
//                return dp[n][m][k] = 1;
//        }

        if (n * m == k) {
            return dp[n][m][k] = 0;
        }

        dp[n][m][k] = inf;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= Math.min(k, i * m); j++) {
                dp[n][m][k] = Math.min(dp[n][m][k], rec(i, m, j) + rec(n - i, m, k - j) + m * m);
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j <= Math.min(k, n * i); j++) {
                dp[n][m][k] = Math.min(dp[n][m][k], rec(n, i, j) + rec(n, m - i, k - j) + n * n);
            }
        }


        return dp[n][m][k];
    }
}
