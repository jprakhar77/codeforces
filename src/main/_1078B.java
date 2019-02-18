package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class _1078B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        int sum = 0;
        for (int val : a) {
            sum += val;
        }

        int[][][] dp = new int[2][sum + 1][n + 1];

        dp[0][0][0] = 1;
        dp[1][0][0] = 1;

        int[] cnt = new int[101];

        Set<Integer> s = new HashSet<>();
        for (int val : a) {
            cnt[val]++;
            s.add(val);
        }

        for (int i = 1; i <= 100; i++) {
            if (cnt[i] == 0)
                continue;

            for (int j = 0; j <= sum; j++) {
                for (int k = 0; k <= n; k++) {
                    if (dp[0][j][k] > 0) {
                        for (int l = 1; l <= cnt[i]; l++) {
                            dp[1][j + l * i][k + l] += dp[0][j][k];

                            if (dp[1][j + l * i][k + l] > 1) {
                                dp[1][j + l * i][k + l] = 2;
                            }
                        }
                    }
                }
            }

//            for (int j = 0; j <= sum; j++) {
//                for (int k = 0; k <= n; k++) {
//                    dp[0][j][k] = dp[1][j][k];
//                }
//            }

            dp[0] = dp[1].clone();
        }

        if (s.size() <= 2) {
            out.print(n);
            return;
        }

        int ans = 1;
        for (
                int i = 1;
                i <= 100; i++) {
            if (cnt[i] > 1) {
                for (int j = 2; j <= cnt[i]; j++) {
                    int val = j * i;

                    if (dp[0][val][j] == 1) {
                        ans = Math.max(ans, j);
                    }
                }
            }
        }

        out.print(ans);
    }
}
