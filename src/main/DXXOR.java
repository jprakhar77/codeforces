package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DXXOR {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long k = in.nextLong();


        long[] a = in.nla(n);

        long[] pd = new long[40];

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < n; j++) {
                if ((a[j] & (1l << i)) != 0) {
                    pd[i]++;
                }
            }
        }

        long[][] dp = new long[41][2];

        boolean fd = false;
        for (int i = 39; i >= 0; i--) {
            long val = (1l << i);

            //tight
            if (((1l << i) & k) == 0) {
                dp[i][1] = dp[i + 1][1] + pd[i] * (1l << i);
            } else {
                dp[i][1] = dp[i + 1][1] + (n - pd[i]) * (1l << i);
            }

            //loose
            if (((1l << i) & k) != 0) {
                dp[i][0] = dp[i + 1][1] + pd[i] * val;
                if (fd)
                    dp[i][0] = Math.max(dp[i][0],
                            dp[i + 1][0] + Math.max(pd[i], n - pd[i]) * val);
            } else if (fd) {
                dp[i][0] = dp[i + 1][0] + Math.max(pd[i], n - pd[i]) * val;
            }

            if ((k & (1l << i)) != 0) {
                fd = true;
            }
        }

        out.println(Math.max(dp[0][0], dp[0][1]));
    }
}
