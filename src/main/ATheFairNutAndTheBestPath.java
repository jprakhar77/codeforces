package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ATheFairNutAndTheBestPath {
    class edge {
        int v;
        int c;

        public edge(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        w = in.nextIntArray(n);

        g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int c = in.nextInt();

            g[u].add(new edge(v, c));
            g[v].add(new edge(u, c));
        }

        dp = new long[n];

        dfs(0, -1);

        out.println(ans);
    }

    long ans = 0;
    long[] dp;
    int[] w;
    List[] g;

    void dfs(int u, int p) {

        if ((u != 0 && g[u].size() == 1) || g[u].size() == 0) {
            dp[u] = w[u];
            ans = Math.max(dp[u], ans);
            return;
        }

        long max = w[u];

        List<Long> l = new ArrayList<>();
        for (int i = 0; i < g[u].size(); i++) {
            edge edge = (edge) g[u].get(i);

            if (edge.v == p)
                continue;

            dfs(edge.v, u);
            l.add(dp[edge.v] - edge.c);
        }

        l.sort(Comparator.reverseOrder());

        if (l.size() == 1) {
            if (l.get(0) > 0) {
                max += l.get(0);
            }
            dp[u] = max;
        } else {
            long fi = l.get(0);
            long se = l.get(1);

            if (fi > 0 && se > 0) {
                max += fi;
                dp[u] = max;
                max += se;
            } else if (fi > 0) {
                max += fi;
                dp[u] = max;
            } else {
                dp[u] = max;
            }
        }

        ans = Math.max(ans, max);
        ans = Math.max(ans, dp[u]);
    }
}
