package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _474D {
    int max = 100000;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();
        int k = in.nextInt();

        long[] dp = new long[100002];

        dp[0] = 1;

        for (int i = 1; i <= max; i++) {
            dp[i] = dp[i - 1];

            if (i >= k) {
                dp[i] += dp[i - k];
            }

            dp[i] %= mod;
        }

        for (int i = 1; i <= max; i++) {
            dp[i] = dp[i] + dp[i - 1];
            dp[i] %= mod;
        }

        while (t-- > 0) {
            int a = in.nextInt();
            int b = in.nextInt();

            long ans = dp[b] - dp[a - 1];

            ans %= mod;

            if (ans < 0)
                ans += mod;

            out.println(ans);
        }

    }

    int mod = (int) (1e9 + 7);
}
