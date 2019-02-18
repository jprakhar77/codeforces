package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class _799D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();

        int h = in.nextInt();
        int w = in.nextInt();

        int n = in.nextInt();

        Integer[] ar = new Integer[n];

        for (int i = 0; i < n; i++)
            ar[i] = in.nextInt();

        Arrays.sort(ar, (x, y) -> y - x);

        int max = Math.max(a, b);

        long[][] dp = new long[41][max + 1];

        for (int i = h; i >= 0; i--) {
            dp[0][i] = w;
        }

//        for (int i = w; i >= 0; i--) {
//            dp[0][i] = Math.max(dp[0][i], h);
//        }

        if (dp[0][a] >= b || dp[0][b] >= a) {
            out.println(0);
            return;
        }

        for (int i = 1; i <= Math.min(n, 40); i++) {

            long num = ar[i - 1];
            for (int j = 0; j <= max; j++) {
                dp[i][(int) Math.min(num * j, max)] = Math.max(dp[i][(int) Math.min(num * j, max)], dp[i - 1][j]);
            }

            for (int j = 0; j <= max; j++) {
                dp[i][j] = Math.max(dp[i][j], Math.min(dp[i - 1][j] * num, max));
            }

            for (int j = max - 1; j >= 0; j--) {
                dp[i][j] = Math.max(dp[i][j], dp[i][j + 1]);
            }

            if (dp[i][a] >= b || dp[i][b] >= a) {
                out.println(i);
                return;
            }
        }

        out.println(-1);
    }
}
