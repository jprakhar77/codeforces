package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.List;

public class VSubtree {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();

        m = in.nextInt();

        g = new List[n];

        in.readUndirectedGraph(g, n, n - 1, 1);

        sub = new long[n];
        dp = new long[n];
        ans = new long[n];

        dfs(0, -1);

        dfs2(0, -1, -1, -1);

        for (int i = 0; i < n; i++) {
            out.println(ans[i]);
        }

    }

    List[] g;
    int n;
    int m;

    long[] sub;

    void dfs(int u, int p) {
        sub[u] = 1;
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs(v, u);
                sub[u] *= (1 + sub[v]);
                sub[u] %= m;
            }
        }
    }

    long[] dp;

    long[] ans;

    void dfs2(int u, int p, long prep, long sufp) {

        if (u == 0) {
            dp[0] = 1;
            ans[0] = sub[0];
        } else {
            long val = ((prep * sufp) % m) * dp[p];
            dp[u] = 1 + val;
            dp[u] %= m;

            ans[u] += (sub[u] * dp[u]) % m;
            ans[u] %= m;
        }

        int cc = g[u].size();

        if (u != 0) {
            cc--;
        }

        if (cc == 0)
            return;

        long[] val = new long[cc];
        long[] pre = new long[cc + 1];
        long[] suf = new long[cc + 2];

        for (int i = 0, j = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                val[j] = 1 + sub[v];
                val[j] %= m;
                j++;
            }
        }

        pre[0] = 1;

        for (int i = 0; i < cc; i++) {
            pre[i + 1] = pre[i] * val[i];
            pre[i + 1] %= m;
        }

        suf[cc + 1] = 1;

        for (int i = cc - 1; i >= 0; i--) {
            suf[i + 1] = suf[i + 2] * val[i];
            suf[i + 1] %= m;
        }

        for (int i = 0, j = 1; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs2(v, u, pre[j - 1], suf[j + 1]);
                j++;
            }
        }
    }
}
