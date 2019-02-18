package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DSumInTheTree {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        g = new List[n];

        in.readTree(g, n, 1);

        s = in.nextLongArray(n);

        dfs(0, -1, 0);

        if (!isp) {
            out.println(-1);
            return;
        }

        out.println(ans);
    }

    List[] g;
    long[] s;

    long ans;

    boolean isp = true;

    void dfs(int u, int p, int ch) {
        if (u != 0) {
            if (g[u].size() == 1) {
                if (ch % 2 == 0) {
                    ans += s[u] - s[p];
                }
                return;
            }
        }

        if (ch % 2 == 0) {
            ans += s[u];

            if (p != -1) {
                ans -= s[p];
            }

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (v != p) {
                    dfs(v, u, ch + 1);
                }
            }
        } else {
            long min = s[p];

            List<Long> l = new ArrayList<>();

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (v != p) {
                    //dfs(v, u, ch + 1);
                    l.add(s[v]);
                }
            }

            l.sort(Comparator.naturalOrder());

            long max = l.get(0);

            if (min > max) {
                isp = false;
                return;
            }

            s[u] = max;

            ans += max - s[p];

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (v != p) {
                    dfs(v, u, ch + 1);
                }
            }
        }
    }
}
