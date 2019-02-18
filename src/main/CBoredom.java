package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CBoredom {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        long[] b = new long[100001];

        long[] dp = new long[100001];

        for (int val : a) {
            b[val] += val;
        }

        dp[0] = b[0];

        dp[1] = Math.max(b[1], b[0]);

        for (int i = 2; i <= 100000; i++) {
            dp[i] = b[i] + dp[i - 2];
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }

        out.println(dp[100000]);
    }
}
