package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class HGrid1 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        String[] mat = new String[n];

        for (
                int i = 0; i < n; i++) {
            mat[i] = in.next();
        }

        long[][] dp = new long[n][m];

        dp[0][0] = 1;

        for (int i = 1; i < m; i++) {
            if (mat[0].charAt(i) == '.') {
                dp[0][i] = 1;
            } else break;
        }

        for (int i = 1; i < n; i++) {
            if (mat[i].charAt(0) == '.') {
                dp[i][0] = 1;
            } else break;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (mat[i].charAt(j) == '#')
                    continue;

                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                dp[i][j] %= mod;
            }
        }

        out.println(dp[n - 1][m - 1]);
    }

    int mod = 1000000007;
}
