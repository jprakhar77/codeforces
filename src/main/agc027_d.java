package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class agc027_d {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long[][] ans = new long[n][n];

        ans[0][0] = 3;

        for (int i = 1; i < n; i++) {
            ans[0][i] = i + 2;
        }

        long ln = ans[0][n - 1];
        long nn = -1;
        if (ln % 2 == 1) {
            nn = ln + 2;
        } else {
            nn = ln + 1;
        }

        for (int i = 1; i < n; i++) {
            ans[i][0] = nn + i - 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                long g = gcd(ans[i - 1][j], ans[i][j - 1]);

                long num = ans[i - 1][j] / g;
                num *= ans[i][j - 1];

                ans[i][j] = num + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.print(ans[i][j] + " ");
            }
            out.println();
        }
    }

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
