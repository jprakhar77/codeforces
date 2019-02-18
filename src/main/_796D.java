package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _796D {
    class edge {
        int v;
        int i;

        public edge(int v, int i) {
            this.v = v;
            this.i = i;
        }
    }

    int root = -1;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int d = in.nextInt();

        Set<Integer> ks = new HashSet<>();
        for (int i = 0; i < k; i++) {
            ks.add(in.nextInt() - 1);
        }

        k = ks.size();

        List[] g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();

            u--;
            v--;

            g[u].add(new edge(v, i + 1));
            g[v].add(new edge(u, i + 1));
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int val : ks) {
            q.addLast(val);
        }

        int[] c = new int[n];
        boolean[] vis = new boolean[n];

        for (int val : ks) {
            c[val] = val;
            vis[val] = true;
        }

        while (!q.isEmpty()) {
            int u = q.removeFirst();

            for (int i = 0; i < g[u].size(); i++) {
                edge v = (edge) g[u].get(i);

                if (!vis[v.v]) {
                    c[v.v] = c[u];
                    vis[v.v] = true;
                    q.addLast(v.v);
                }
            }
        }

        out.println(ks.size() - 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < g[i].size(); j++) {
                edge v = (edge) g[i].get(j);

                if (i < v.v) {
                    if (c[i] != c[v.v]) {
                        out.print(v.i + " ");
                    }
                }
            }
        }

    }

    List<Integer> ans = new ArrayList<>();

    void dfs(int u, int p, List[] g, int en, Set<Integer> ks, int cd, int d, boolean isw) {
        int ccd = cd + 1;
        boolean cisw = isw;
        if ((ks.contains(u) && en != -1 && !isw) || (cd > d)) {
            ans.add(en);
            if (cd > d && !ks.contains(u)) {
                cisw = true;
            }
            ccd = 1;
        }

        if (ks.contains(u)) {
            ccd = 1;
            cisw = false;
        }

        for (int i = 0; i < g[u].size(); i++) {
            edge v = (edge) g[u].get(i);

            if (v.v != p) {
                dfs(v.v, u, g, v.i, ks, ccd, d, cisw);
            }
        }
    }
}
