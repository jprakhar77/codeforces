package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1036C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            long l = in.nextLong();
            long r = in.nextLong();

            out.println(cal(r) - cal(l - 1));
        }
    }

    long cal(long num) {
        long[][][] dp = new long[19][2][4];

        dp[0][0][0] = 1;
        dp[0][0][1] = num % 10;
        dp[0][1][0] = 1;
        dp[0][1][1] = 9;

        long cnum = num / 10;
        for (int i = 1; i < 19; i++) {
            long cd = (cnum % 10);
            for (int j = 0; j <= 3; j++) {
                //Use non-0
                if (j > 0) {
                    if (cd > 0) {
                        dp[i][0][j] += (cd - 1) * dp[i - 1][1][j - 1];
                        dp[i][0][j] += dp[i - 1][0][j - 1];
                    }
                    dp[i][1][j] = 9 * dp[i - 1][1][j - 1];
                }
                //Use 0
                if (cd > 0) {
                    dp[i][0][j] += dp[i - 1][1][j];
                } else {
                    dp[i][0][j] += dp[i - 1][0][j];
                }
                dp[i][1][j] += dp[i - 1][1][j];
            }
            cnum /= 10;
        }

        long ans = 0;

        for (int i = 0; i < 4; i++) {
            ans += dp[18][0][i];
        }

        return ans - 1;
    }
}
