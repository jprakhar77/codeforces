package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _280C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        List[] g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            g[a].add(b);
            g[b].add(a);
        }

        dp = new double[n];
        dpi = new double[n];

        dfs(0, g, -1, 1);

        out.println(ans);
    }

    double[] dp;
    double[] dpi;

    double ans = 0;

    void dfs(int u, List[] g, int p, int d) {

        ans += 1.0 / d;

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v == p)
                continue;

            dfs(v, g, u, d + 1);
        }
    }
}
