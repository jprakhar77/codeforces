package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class FLCS {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();
        String t = in.next();

        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n + 1][m + 1];

        int[][] dpr = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    //dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    dpr[i][j] = 3;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    if (dp[i - 1][j] >= dp[i][j - 1]) {
                        dpr[i][j] = 1;
                    } else {
                        dpr[i][j] = 2;
                    }
                }
            }
        }

        int sx = n;
        int sy = m;

        StringBuilder ans = new StringBuilder();

        while (sx > 0 && sy > 0) {
            if (dpr[sx][sy] == 1) {
                sx--;
            } else if (dpr[sx][sy] == 2) {
                sy--;
            } else {
                ans.insert(0, s.charAt(sx - 1));
                sx--;
                sy--;
            }
        }

        out.println(ans.toString());
        // out.println(dp[n][m]);
    }
}
