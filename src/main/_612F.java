package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.TreeMap;
import java.util.TreeSet;

public class _612F {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int s = in.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        TreeMap<Integer, TreeSet<Integer>> nim = new TreeMap<>((x, b) -> b - x);

        for (int i = 0; i < n; i++) {
            if (!nim.containsKey(a[i])) {
                nim.put(a[i], new TreeSet<>());
            }
            nim.get(a[i]).add(i);
        }

        int m = nim.size();

        int[][] dp = new int[m + 1][n];

        int inf = 2000 * 3000;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = inf;
            }
        }

        int i = m - 1;
        for (Integer key : nim.keySet()) {
            for (int j = 0; j < n; j++) {
                TreeSet<Integer> l = nim.get(key);

                Integer floor = l.floor(j - 1);
                Integer ceil = l.ceiling(j + 1);
                Integer low = l.first();
                Integer high = l.last();

                if (j >= low && j <= high) {
                    int v1 = 2 * (j - low) + high - j;
                    dp[i][j] = Math.min(dp[i][j], v1 + dp[i + 1][high]);
                    if (ceil != null) {
                        int v2 = n - (ceil - j);
                        dp[i][j] = Math.min(dp[i][j], v2 + dp[i + 1][ceil]);
                    }
                    if (floor != null) {
                        int v3 = n - (j - floor);
                        dp[i][j] = Math.min(dp[i][j], v3 + dp[i + 1][floor]);
                    }
                    int v4 = 2 * (high - j) + (j - low);
                    dp[i][j] = Math.min(dp[i][j], v4 + dp[i + 1][low]);
                } else if (j < low) {
                    int v1 = high - j;
                    dp[i][j] = Math.min(dp[i][j], v1 + dp[i + 1][high]);
                    int v2 = n - (ceil - j);
                    dp[i][j] = Math.min(dp[i][j], v2 + dp[i + 1][ceil]);
                } else if (j > high) {
                    int v3 = n - (j - floor);
                    dp[i][j] = Math.min(dp[i][j], v3 + dp[i + 1][floor]);
                    int v4 = (j - low);
                    dp[i][j] = Math.min(dp[i][j], v4 + dp[i + 1][low]);
                }
            }
            i--;
        }

        out.println(dp[0][s - 1]);
    }

}
