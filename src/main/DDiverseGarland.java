package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DDiverseGarland {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        String s = in.next();

        int[][] dp = new int[n][3];

        int[][] dpr = new int[n][3];

        char[] col = {'R', 'G', 'B'};

        for (int j = 0; j < 3; j++) {
            if (col[j] != s.charAt(0)) {
                dp[0][j] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = inf;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j == k)
                        continue;

                    int ex = 0;

                    if (s.charAt(i) != col[k]) {
                        ex++;
                    }

                    if (dp[i - 1][j] + ex < dp[i][k]) {
                        dp[i][k] = dp[i - 1][j] + ex;
                        dpr[i][k] = j;
                    }
                }
            }
        }

        int ans = inf;

        int lastmin = -1;
        for (int j = 0; j < 3; j++) {
            if (dp[n - 1][j] < ans) {
                ans = dp[n - 1][j];
                lastmin = j;
            }
        }

        char[] fans = new char[n];
        for (int i = n - 1; i >= 0; i--) {
            fans[i] = col[lastmin];
            lastmin = dpr[i][lastmin];
        }

        out.println(ans);
        out.println(fans);
    }

    int inf = 1000000000;
}
