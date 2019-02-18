package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _403D {
    int mod = (int) 1e9 + 7;

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        long[][][] dp = new long[2][1001][1001];
        long[][] ans = new long[1001][51];

        for (int i = 1; i <= 1000; i++) {
            for (int j = 0; j <= 1000; j++) {
                if (j > 0)
                    dp[1][i][j] = dp[1][i][j - 1];

                dp[1][i][j] += (dp[1][i - 1][j] - (j > 0 ? dp[1][i - 1][j - 1] : 0));

                if (i >= j + 1) {
                    dp[1][i][j]++;
                }

                dp[1][i][j] %= mod;
            }
            ans[i][1] = (dp[1][i][1000] + mod) % mod;
        }

        long[] fact = new long[1001];

        fact[0] = 1;

        for (int i = 1; i <= 1000; i++) {
            fact[i] = fact[i - 1] * i;
            fact[i] %= mod;
        }

        for (int k = 2; k <= 50; k++) {
            int kp2 = k % 2;
            for (int i = 1; i <= 1000; i++) {
                for (int j = 0; j <= 1000; j++) {
                    if (j > 0)
                        dp[kp2][i][j] = dp[kp2][i][j - 1];
                    else
                        dp[kp2][i][j] = 0;

                    dp[kp2][i][j] += (dp[kp2][i - 1][j] - (j > 0 ? dp[kp2][i - 1][j - 1] : 0));

                    if (i >= j + 1 && j > 0) {
                        dp[kp2][i][j] += dp[1 - kp2][i - j - 1][j - 1];
                    }

                    dp[kp2][i][j] %= mod;
                }
                ans[i][k] = (((dp[kp2][i][1000] * fact[k]) % mod) + mod) % mod;
            }
        }

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            if (k > 50) {
                out.println(0);
                continue;
            }
            out.println(ans[n][k]);
        }
    }
}
