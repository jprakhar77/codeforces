package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class KStones {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] a = in.nextIntArray(n);

        int[] dp = new int[k + 1];

        dp[0] = 0;

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= a[j] && dp[i - a[j]] == 0) {
                    dp[i] = 1;
                    break;
                }
            }
        }

        if (dp[k] == 1) {
            out.println("First");
        } else {
            out.println("Second");
        }
    }
}
