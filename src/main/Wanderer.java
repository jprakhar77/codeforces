package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.List;

public class Wanderer {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        t:
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();

            List[] g = new List[n];

            in.readUndirectedGraph(g, n, m, 1);

            int q = in.nextInt();

            int[] req = new int[k + 1];

            Arrays.fill(req, -1);
            for (int i = 0; i < q; i++) {
                int a = in.nextInt();
                int b = in.nextInt();

                a--;

                if (req[b] != -1) {
                    out.println(0);
                    continue t;
                }

                req[b] = a;
            }

            if (req[0] != -1 && req[0] != 0) {
                out.println(0);
                continue;
            }

            int[][] dp = new int[n][2];

            dp[0][0] = 1;

            for (int i = 1; i <= k; i++) {
                if (req[i] != -1) {
                    int u = req[i];
                    dp[u][1] = dp[u][0];
                    for (int j = 0; j < g[u].size(); j++) {
                        int v = (int) g[u].get(j);

                        dp[u][1] += dp[v][0];
                        dp[u][1] %= mod;
                    }
                } else {
                    for (int l = 0; l < n; l++) {
                        int u = l;
                        dp[u][1] = dp[u][0];
                        for (int j = 0; j < g[u].size(); j++) {
                            int v = (int) g[u].get(j);

                            dp[u][1] += dp[v][0];
                            dp[u][1] %= mod;
                        }
                    }
                }

                for (int j = 0; j < n; j++) {
                    dp[j][0] = dp[j][1];
                    dp[j][1] = 0;
                }
            }

            long ans = 0;

            for (int j = 0; j < n; j++) {
                ans += dp[j][0];
                ans %= mod;
            }

            out.println(ans);
        }
    }

    int mod = 1000_000_000 + 7;
}
