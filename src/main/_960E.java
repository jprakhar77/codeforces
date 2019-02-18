package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _960E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] v = new int[n];

        for (int i = 0; i < n; i++) {
            v[i] = in.nextInt();
        }

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

        long[] dp = new long[n];
        dp2 = new long[n];

        d = new int[n];

        s = new int[n];

        dfs1(0, g, dp, v, 0, -1);

        dfs2(0, g, dp, v, -1);

        if (ans < 0)
            ans += mod;

        out.println(ans);
    }

    int mod = (int) 1e9 + 7;

    long[] dp2;

    int[] d;

    int[] s;

    void dfs1(int u, List[] g, long[] dp, int[] vv, int cd, int p) {

        dp[u] = 1;

        d[u] = cd;

        s[u] = 1;

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v == p)
                continue;

            dfs1(v, g, dp, vv, cd + 1, u);

            s[u] += s[v];
        }

        dp2[u] = s[u];

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v == p)
                continue;

            dp[u] += -dp[v];
            dp2[u] += (-dp[v] * (s[u] - s[v]));

            dp[u] %= mod;
            dp2[u] %= mod;
        }
    }

    long ans = 0;

    void dfs2(int u, List[] g, long[] dp, int[] vv, int p) {

        int cd = d[u];

        long numo = 0;
        long numi = 0;
        if (cd % 2 == 0) {
            numo = dp[0] - dp[u];
            numo %= mod;

            numi = dp[u];
            numi %= mod;
        } else {
            numo = -dp[0] - dp[u];
            numo %= mod;

            numi = dp[u];
            numi %= mod;
        }

        numo *= (s[u]);
        numo %= mod;

        numi *= (s[0] - s[u]);
        numi %= mod;

        ans += ((numo + numi + dp2[u]) * vv[u]);

        ans %= mod;


        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v == p)
                continue;

            dfs2(v, g, dp, vv, u);
        }
    }
}
