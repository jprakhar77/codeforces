package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class C755 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();

        int ans = 0;

        for (int t = n - 3; t >= 0; t--) {
            int[][][] dp = new int[n][2][8];

            for (int i = t; i < n; i++) {
                int cn = s.charAt(i) - '0';
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 3; k++) {
                        int nj = j | (1 << k);

                        int num = 3 + k * 2;

                        //0
                        if (i > t) {
                            dp[i][0][nj] += (dp[i - 1][0][j]);
                            if (cn > num) {
                                dp[i][0][nj] += dp[i - 1][1][j];
                            }
                        } else {
                            if (j == 0) {
                                if (t > 0 || cn > num)
                                    dp[i][0][nj]++;
                            }
                        }

                        //1

                        if (i > t) {
                            if (cn == num) {
                                dp[i][1][nj] += dp[i - 1][1][j];
                            }
                        } else {
                            if (t == 0 && cn == num && j == 0) {
                                dp[i][1][nj]++;
                            }
                        }
                    }
                }
            }

            ans += dp[n - 1][0][7] + dp[n - 1][1][7];
        }

//        int ans = 0;
//
//        for (int i = 2; i < n; i++) {
//            ans += dp[i][1][7] + dp[i][0][7];
//        }

        out.println(ans);
    }
}
