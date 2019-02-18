package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CVacation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[][] hap = new int[n][3];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                hap[i][j] = in.nextInt();
            }
        }

        int[][] dp = new int[n][3];

        for (int j = 0; j < 3; j++)
            dp[0][j] = hap[0][j];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j == k)
                        continue;

                    dp[i][k] = Math.max(dp[i][k], dp[i - 1][j] + hap[i][k]);
                }
            }
        }

        int max = 0;

        for (int j = 0; j < 3; j++)
            max = Math.max(max, dp[n - 1][j]);

        out.println(max);
    }
}
