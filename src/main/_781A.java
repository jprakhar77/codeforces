package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _781A {
    List[] g;
    int n, k;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();

        g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < n - 1; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;

            g[x].add(y);
            g[y].add(x);
        }

        int maxd = 0;
        int maxdv = -1;

        for (int i = 0; i < n; i++) {
            if (g[i].size() > maxd) {
                maxd = g[i].size();
                maxdv = i;
            }
        }

        k = maxd + 1;

        color = new int[n];

        color[maxdv] = 1;

        int cc = 2;
        for (int i = 0; i < g[maxdv].size(); i++) {
            int v = (int) g[maxdv].get(i);

            color[v] = cc;
            cc++;
        }

        for (int i = 0; i < g[maxdv].size(); i++) {
            int v = (int) g[maxdv].get(i);

            dfs(v, maxdv, color[v], 1);
        }

        out.println(k);

        for (int i = 0; i < n; i++) {
            out.print(color[i] + " ");
        }
    }

    int[] color;

    void dfs(int u, int p, int c, int pc) {
        int cc = 1;
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                while (cc == c || cc == pc)
                    cc++;
                color[v] = cc;
                dfs(v, u, cc, c);
                cc++;
            }
        }
    }
}
