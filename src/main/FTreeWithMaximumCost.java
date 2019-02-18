package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.List;

public class FTreeWithMaximumCost {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        a = in.nextIntArray(n);

        for (int val : a) {
            tasum += val;
        }

        g = new List[n];

        in.readUndirectedGraph(g, n, n - 1, 1);

        asum = new long[n];
        sa = new long[n];
        ta = new long[n];

        dfs0(0, -1);
        dfs1(0, -1);

        ta[0] = sa[0];

        dfs2(0, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, ta[i]);
        }

        out.println(ans);
    }

    int[] a;
    long tasum = 0;
    List[] g;
    long[] asum;
    long[] sa;
    long[] ta;

    void dfs0(int u, int p) {
        asum[u] = a[u];

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs0(v, u);
                asum[u] += asum[v];
            }
        }
    }

    void dfs1(int u, int p) {
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs1(v, u);
                sa[u] += sa[v] + asum[v];
            }
        }
    }

    void dfs2(int u, int p) {
        if (u != 0) {
            long pt = ta[p];

            long rem = pt - sa[u] - asum[u];

            long rema = tasum - asum[u];

            rem += rema;

            ta[u] = sa[u] + rem;
        }

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs2(v, u);
            }
        }
    }
}
