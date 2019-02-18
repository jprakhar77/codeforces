package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _678E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        double[][] p = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                p[i][j] = in.nextDouble();
            }
        }

        double[][] dp = new double[1 << n][n];

        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        double ans = 0;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, rec(0, i, n, dp, p));
        }

        out.println(ans);
    }

    double rec(int mask, int cn, int n, double[][] dp, double[][] p) {
        if (dp[mask][cn] != -1) {
            return dp[mask][cn];
        }

        if (mask == ((1 << n) - 2)) {
            return dp[mask][cn] = 1;
        }

        dp[mask][cn] = 0;

        for (int i = 0; i < n; i++) {
            if (((mask & (1 << i)) == 0) && i != cn) {
                if (cn == 0) {
                    dp[mask][cn] = Math.max(dp[mask][cn], p[0][i] * rec(mask | (1 << i), 0, n, dp, p));
                } else if (i == 0) {
                    dp[mask][cn] = Math.max(dp[mask][cn], p[0][cn] * rec(mask | (1 << cn), 0, n, dp, p));
                } else {
                    double t = 0;
                    t = p[i][cn] * rec(mask | (1 << cn), i, n, dp, p);
                    t += p[cn][i] * rec(mask | (1 << i), cn, n, dp, p);
                    dp[mask][cn] = Math.max(dp[mask][cn], t);
                }
            }
        }

        return dp[mask][cn];
    }
}
