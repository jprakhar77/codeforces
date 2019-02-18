package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1076F {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long k = in.nextInt();

        long[] x = new long[n + 1];
        long[] y = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            x[i] = in.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            y[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            if (!poss(x[i], y[i], k)) {
                out.println("NO");
                return;
            }
        }

        long[][] dp = new long[n + 1][2];

        //x w, y b
        for (int i = 1; i <= n; i++) {
            if (dp[i - 1][0] == 0 && dp[i - 1][1] == 0) {
                //min w
                if (y[i] * k >= x[i]) {
                    dp[i][0] = 0;
                } else {
                    dp[i][0] = x[i] - y[i] * k;
                }
                //min b
                if (x[i] * k >= y[i]) {
                    dp[i][1] = 0;
                } else {
                    dp[i][1] = y[i] - x[i] * k;
                }
            } else {
                if (dp[i - 1][0] > 0) {
                    //min w
                    if (y[i] > x[i] || (y[i] - 1) * k >= x[i]) {
                        dp[i][0] = 0;
                    } else {
                        long nx = x[i] - (k - dp[i - 1][0]);
                        if (y[i] > nx || (y[i] - 1) * k >= nx) {
                            dp[i][0] = 0;
                        } else {
                            dp[i][0] = nx - ((y[i] - 1) * k);
                        }
                    }

                    //min b
                    if (x[i] >= y[i] || (x[i] * k) >= y[i]) {
                        dp[i][1] = 0;
                    } else {
                        dp[i][1] = y[i] - (x[i] * k);
                    }
                } else {
                    //min b
                    if (x[i] > y[i] || (x[i] - 1) * k >= y[i]) {
                        dp[i][1] = 0;
                    } else {
                        long ny = y[i] - (k - dp[i - 1][1]);
                        if (x[i] > ny || (x[i] - 1) * k >= ny) {
                            dp[i][1] = 0;
                        } else {
                            dp[i][1] = ny - ((x[i] - 1) * k);
                        }
                    }

                    //min w
                    if (y[i] >= x[i] || (y[i] * k) >= x[i]) {
                        dp[i][0] = 0;
                    } else {
                        dp[i][0] = x[i] - (y[i] * k);
                    }
                }
            }

            if (dp[i][0] > k || dp[i][1] > k) {
                out.println("NO");
                return;
            }
        }

        out.println("YES");
    }

    boolean poss(long x, long y, long k) {
        if (x > (y + 1) * k || y > (x + 1) * k)
            return false;

        return true;
    }
}
