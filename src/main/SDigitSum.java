package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class SDigitSum {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int d = in.nextInt();

        int n = s.length();
        long[][][] dp = new long[n][d][2];

        //1 - restric

        int num = s.charAt(0) - '0';

        for (int i = 0; i < num; i++) {
            dp[0][i % d][0]++;
        }

        dp[0][num % d][1]++;

        for (int i = 1; i < n; i++) {
            //1

            num = s.charAt(i) - '0';

            for (int j = 0; j < d; j++) {
                dp[i][(j + num) % d][1] += dp[i - 1][j][1];
                dp[i][(j + num) % d][1] %= mod;
            }

            //0

            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < d; k++) {
                    dp[i][(k + j) % d][0] += dp[i - 1][k][0];
                    dp[i][(k + j) % d][0] %= mod;
                }
            }

            for (int j = 0; j < num; j++) {
                for (int k = 0; k < d; k++) {
                    dp[i][(k + j) % d][0] += dp[i - 1][k][1];
                    dp[i][(k + j) % d][0] %= mod;
                }
            }
        }

        long ans = dp[n - 1][0][0] + dp[n - 1][0][1];

        ans--;

        ans %= mod;

        if (ans < 0)
            ans += mod;

        out.println(ans);
    }

    int mod = 1000000007;
}
