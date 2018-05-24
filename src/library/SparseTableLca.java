package library;

import java.util.List;

public class SparseTableLca {
    List[] g;
    int[] p;
    int[] pw;
    int[] h;
    int n;
    int log = 0;

    SparseTableLca.node r;

    int[][] stp;
    int[][] stpw;

    static class node {
        int v;
        int w;

        public node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public SparseTableLca(List[] g, node r) {
        this.g = g;
        this.r = r;
        this.n = g.length;
        this.p = new int[n];
        this.pw = new int[n];
        this.h = new int[n];
        int cn = n;
        while (cn > 0) {
            cn /= 2;
            log++;
        }
        this.stp = new int[n][log + 1];
        this.stpw = new int[n][log + 1];
        dfs(r, r.v, 0);
        for (int i = 0; i < n; i++) {
            stp[i][0] = p[i];
            stpw[i][0] = pw[i];
        }
        init();
    }

    void dfs(node u, int cp, int ch) {
        p[u.v] = cp;
        pw[u.v] = u.w;
        h[u.v] = ch;

        for (int i = 0; i < g[u.v].size(); i++) {
            node v = (node) g[u.v].get(i);

            if (v.v != cp) {
                dfs(v, u.v, ch + 1);
            }
        }
    }

    public SparseTableLca(int[] p, int[] pw, int[] h) {
        this.p = p;
        this.pw = pw;
        this.h = h;
        this.n = p.length;

        int cn = n;
        while (cn > 0) {
            cn /= 2;
            log++;
        }

        this.stp = new int[n][log + 1];
        this.stpw = new int[n][log + 1];

        for (int i = 0; i < n; i++) {
            stp[i][0] = p[i];
            stpw[i][0] = pw[i];
        }
    }

    void init() {
        for (int i = 1; i <= log; i++) {
            for (int j = 0; j < n; j++) {
                stp[j][i] = stp[stp[j][i - 1]][i - 1];
                stpw[j][i] = Math.max(stpw[j][i - 1], stpw[stp[j][i - 1]][i - 1]);
            }
        }
    }

    int lca(int u, int v) {
        if (h[u] < h[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int h1 = h[u];
        int h2 = h[v];

        if (h1 > h2) {
            int d = h1 - h2;
            int cu = u;
            for (int i = log; i >= 0; i--) {
                if (d >= 1 << i) {
                    cu = stp[cu][i];
                    d -= (1 << i);
                }
            }
            u = cu;
        }

        if (u == v) {
            return u;
        }

        for (int i = log; i >= 0; i--) {
            if (stp[u][i] != stp[v][i]) {
                u = stp[u][i];
                v = stp[v][i];
            }
        }

        return p[u];
    }

    int maxdis(int u, int v) {
        int lca = lca(u, v);

        if (u == v) {
            return 0;
        }

        if (lca == u || lca == v) {
            return maxdisAncestor(u, v);
        }

        return Math.max(maxdisAncestor(u, lca), maxdisAncestor(v, lca));
    }

    int maxdisAncestor(int u, int v) {
        if (h[u] < h[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = h[u] - h[v];

        int cu = u;
        int max = 0;
        for (int i = log; i >= 0; i--) {
            if ((1 << i) <= diff) {
                diff -= (1 << i);
                max = Math.max(max, stpw[cu][i]);
                cu = stp[cu][i];
            }
        }

        return max;
    }

}
