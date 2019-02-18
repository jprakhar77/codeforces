package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _274B {
    List[] g;
    int[] a;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        g = new List[n];
        for (int i = 0; i < n; i++)
            g[i] = new ArrayList();

        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            g[a].add(b);
            g[b].add(a);
        }

        a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        dp = new long[n][2];

        dfs(0, -1);

        out.println(dp[0][0] + dp[0][1]);
    }

    /**
     * 0->add
     */
    long[][] dp;

    void dfs(int u, int p) {
        for (int v : (List<Integer>) g[u]) {
            if (v != p) {
                dfs(v, u);
            }
        }

        for (int v : (List<Integer>) g[u]) {
            if (v != p) {
                dp[u][0] = Math.max(dp[v][0], dp[u][0]);
                dp[u][1] = Math.max(dp[v][1], dp[u][1]);
            }
        }

        long cv = dp[u][0] - dp[u][1];

        if (cv < a[u]) {
            dp[u][0] += a[u] - cv;
        }
        if (cv > a[u]) {
            dp[u][1] += cv - a[u];
        }
    }
}
