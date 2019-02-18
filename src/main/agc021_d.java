package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class agc021_d {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        String s = in.next();

        int n = s.length();

        int k = in.nextInt();

        int[][][] dp = new int[n][n][k + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l <= k; l++) {
                    dp[i][j][l] = -1;
                }
            }
        }

        out.println(rec(dp, 0, n - 1, k, s));
    }

    int rec(int[][][] dp, int i, int j, int k, String s) {
        if (i > j)
            return 0;

        if (dp[i][j][k] != -1)
            return dp[i][j][k];

        if (i == j) {
            return dp[i][j][k] = 1;
        }

        int ans = 0;
        if (s.charAt(i) == s.charAt(j)) {
            ans = 2 + rec(dp, i + 1, j - 1, k, s);
        } else {
            ans = Math.max(rec(dp, i, j - 1, k, s), rec(dp, i + 1, j, k, s));
            if (k > 0) {
                ans = Math.max(ans, 2 + rec(dp, i + 1, j - 1, k - 1, s));
            }

        }

        return dp[i][j][k] = ans;
    }
}
