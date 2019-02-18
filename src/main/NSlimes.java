package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class NSlimes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long[] a = in.nextLongArray(n);

        long[][] dp = new long[n][n];

//        for (int i = 0; i < n; i++) {
//            dp[i][i] = a[i];
//        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        long[] pa = in.calculatePrefixSum(a);

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;

                long sum = pa[j];
                if (i > 0)
                    sum -= pa[i - 1];
                for (int k = i; k < j; k++) {
                    long val = 0;

                    if (k < j) {
                        val = dp[k + 1][j];
                    }
                    if (dp[i][j] == -1)
                        dp[i][j] = dp[i][k] + val + sum;
                    else
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + val + sum);
                }
            }
        }

        out.println(dp[0][n - 1]);
    }
}
