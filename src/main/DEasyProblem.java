package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class DEasyProblem {
    Set<Character> cs = new HashSet<>();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        cs.add('h');
        cs.add('a');
        cs.add('r');
        cs.add('d');

        int n = in.nextInt();

        String s = in.next();

        int[] a = in.nextIntArray(n);

        long[][] dp = new long[n + 1][4];

        //Arrays.fill(dp, Long.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            char ch = s.charAt(i - 1);

            for (int j = 0; j < 4; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
                }
            }

            if (ch == 'h') {
                dp[i][0] = dp[i - 1][0] + a[i - 1];
            } else if (ch == 'a') {
                dp[i][1] = min(dp[i - 1][0], dp[i - 1][1] + a[i - 1]);
            } else if (ch == 'r') {
                dp[i][2] = min(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2] + a[i - 1]);
            } else if (ch == 'd') {
                dp[i][3] = min(min(dp[i - 1][0], dp[i - 1][1]), min(dp[i - 1][2], dp[i - 1][3] + a[i - 1]));
            }
        }

        long ans = Long.MAX_VALUE;

        for (int j = 0; j < 4; j++) {
            ans = Math.min(dp[n][j], ans);
        }

        out.println(ans);
    }

    long min(long a, long b) {
        return Math.min(a, b);
    }

    long min(long a, long b, long c) {
        return min(min(a, b), c);
    }
}
