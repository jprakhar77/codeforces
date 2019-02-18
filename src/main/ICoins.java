package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ICoins {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        double[] p = new double[n];

        for (int i = 0; i < n; i++) {
            p[i] = in.nextDouble();
        }

        double[][] dp = new double[n + 1][n + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] * p[i - 1];
        }

        for (int i = 1; i <= n; i++) {
            dp[0][i] = dp[0][i - 1] * (1 - p[i - 1]);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i + j > n)
                    break;

                dp[i][j] = p[i + j - 1] * dp[i - 1][j];

                dp[i][j] += (1 - p[i + j - 1]) * dp[i][j - 1];
            }
        }

        double ans = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i + j > n)
                    break;

                if (i > j && i + j == n)
                    ans += dp[i][j];
            }
        }

        out.println(ans);
    }
}
