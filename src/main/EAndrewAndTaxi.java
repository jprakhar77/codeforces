package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class EAndrewAndTaxi {
    static class SparseTableLca {
        List[] g;
        int[] p;
        int[] pw;
        int[] h;
        int n;
        int log = 0;

        SparseTableLca.node r;

        int[][] stp;
        int[][] stpw;
        int[][] stpwe;

        static class node {
            int v;
            int w;
            int en;

            public node(int v, int w, int en) {
                this.v = v;
                this.w = w;
                this.en = en;
            }
        }

        boolean[] vis;

        public SparseTableLca(List[] g, node r) {
            this.g = g;
            this.r = r;
            this.n = g.length;
            this.p = new int[n];
            this.pw = new int[n];
            this.h = new int[n];
            this.vis = new boolean[n];
            int cn = n;
            while (cn > 0) {
                cn /= 2;
                log++;
            }
            this.stp = new int[n][log + 1];
            this.stpw = new int[n][log + 1];
            this.stpwe = new int[n][log + 1];
            for (int i = 0; i < n; i++) {
                if (!vis[i])
                    dfs(new node(i, r.w, -1), i, 0, -1);
            }
            for (int i = 0; i < n; i++) {
                stp[i][0] = p[i];
                stpw[i][0] = pw[i];
            }
            init();
        }

        void dfs(node u, int cp, int ch, int ce) {
            p[u.v] = cp;
            pw[u.v] = u.w;
            h[u.v] = ch;
            vis[u.v] = true;
            stpwe[u.v][0] = ce;

            for (int i = 0; i < g[u.v].size(); i++) {
                node v = (node) g[u.v].get(i);

                if (!vis[v.v]) {
                    dfs(v, u.v, ch + 1, v.en);
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

                    int v1 = stpw[j][i - 1];
                    int v2 = stpw[stp[j][i - 1]][i - 1];

                    stpw[j][i] = Math.min(v1, v2);

                    if (v1 <= v2) {
                        stpwe[j][i] = stpwe[j][i - 1];
                    } else {
                        stpwe[j][i] = stpwe[stp[j][i - 1]][i - 1];
                    }
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

    int inf = 2000000000;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            u--;
            v--;

            g[u].add(new SparseTableLca.node(v, w, i + 1));
        }

        SparseTableLca st = new SparseTableLca(g, new SparseTableLca.node(0, inf, -1));

        stp = st.stp;
        stpw = st.stpw;
        h = st.h;
        log = st.log;
        stpwe = st.stpwe;

        recstack = new boolean[n];
        vis = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!vis[i])
                dfs(i);
        }

        out.println(ans + " " + l.size());

        for (int i = 0; i < l.size(); i++) {
            out.print(l.get(i) + " ");
        }

    }

    int[][] stp;
    int[][] stpw;

    int[][] stpwe;
    int log;

    List[] g;

    int ans = 0;

    boolean[] recstack;
    boolean[] vis;

    int[] h;

    List<Integer> l = new ArrayList<>();

    void dfs(int u) {

        recstack[u] = true;

        vis[u] = true;
        for (int i = 0; i < g[u].size(); i++) {
            SparseTableLca.node v = (SparseTableLca.node) g[u].get(i);

            int vv = v.v;

            if (recstack[v.v]) {
                int ca = v.w;

                int diff = h[u] - h[vv];

                int cn = u;
                int mine = v.en;
                for (int j = log; j >= 0; j--) {
                    if (diff >= (1 << j)) {
                        if (stpw[cn][j] < ca) {
                            mine = stpwe[cn][j];
                            ca = stpw[cn][j];
                        }
                        diff -= (1 << j);
                    }
                }

                l.add(mine);
                ans = Math.max(ca, ans);
            }
            if (!vis[vv]) {
                dfs(vv);
            }
        }

        recstack[u] = false;
    }
}
