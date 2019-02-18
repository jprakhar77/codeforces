package main;

import fastio.InputReader;
import fastio.OutputWriter;
import jdk.nashorn.api.tree.Tree;

import java.util.*;

public class EDuffInTheArmy {
    static class SparseTableLca {
        List[] g;
        int[] p;
        int[] pw;
        int[] h;
        int n;
        int log = 0;

        int[][] stp;
        int[][] stpw;
        List[][] stpl;

        static class node {
            int v;
            int w;

            public node(int v, int w) {
                this.v = v;
                this.w = w;
            }
        }

        public SparseTableLca(List[] g, List[] nl) {
            this.g = g;
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
            this.stpl = new List[n][log + 1];
            dfs(0, 0, 0);
            for (int i = 0; i < n; i++) {
                stp[i][0] = p[i];
                stpw[i][0] = pw[i];
                stpl[i][0] = new ArrayList();
                stpl[i][0].addAll(nl[i]);
            }
            init();
        }

        void dfs(int u, int cp, int ch) {
            p[u] = cp;
            pw[u] = 1;
            h[u] = ch;

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (v != cp) {
                    dfs(v, u, ch + 1);
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

                    List<Integer> l1 = stpl[j][i - 1];
                    List<Integer> l2 = stpl[stp[j][i - 1]][i - 1];

                    List<Integer> l = new ArrayList<>();

                    int x = 0, y = 0;

                    while (x < l1.size() && y < l2.size() && l.size() < 10) {
                        if (l1.get(x) <= l2.get(y)) {
                            l.add(l1.get(x));
                            x++;
                        } else {
                            l.add(l2.get(y));
                            y++;
                        }
                    }

                    while (x < l1.size() && l.size() < 10) {
                        l.add(l1.get(x));
                        x++;
                    }

                    while (y < l2.size() && l.size() < 10) {
                        l.add(l2.get(y));
                        y++;
                    }

                    stpl[j][i] = l;
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

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();
        int m = in.nextInt();

        int q = in.nextInt();

        List[] g = new List[n];

        in.readUndirectedGraph(g, n, n - 1, 1);

        int[] c = in.nextIntArray(m);

        List[] nl = new List[n];
        for (int i = 0; i < n; i++) {
            nl[i] = new ArrayList();
        }
        for (int i = 0; i < m; i++) {
            nl[c[i] - 1].add(i);
        }

        for (int i = 0; i < n; i++) {
            nl[i].sort(Comparator.naturalOrder());
        }

        st = new SparseTableLca(g, nl);

        h = st.h;

        log = st.log;

        stpl = st.stpl;

        stp = st.stp;

        while (q-- > 0) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            int a = in.nextInt();

            int lca = st.lca(u, v);

            List<Integer> l1 = solve(u, lca, a);
            List<Integer> l2 = solve(v, lca, a);

            TreeSet<Integer> ans = mergeSet(l1, l2, a);

            out.print(ans.size() + " ");
            for (int val : ans) {
                out.print(val + 1 + " ");
            }

            out.println();
        }
    }

    SparseTableLca st;
    int[] h;
    int log;

    List[][] stpl;
    int[][] stp;

    List<Integer> solve(int u, int lca, int a) {
        int d = h[u] - h[lca] + 1;

        int nod = u;

        List<Integer> cl = new ArrayList<>();
        for (int i = log; i >= 0; i--) {
            if ((1 << i) <= d) {
                d -= (1 << i);
                cl = merge(cl, stpl[nod][i], a);
                nod = stp[nod][i];
            }
        }

        return cl;
    }

    List<Integer> merge(List<Integer> l1, List<Integer> l2, int a) {
        List<Integer> l = new ArrayList<>();

        int x = 0, y = 0;

        while (x < l1.size() && y < l2.size() && l.size() < a) {
            if (l1.get(x) <= l2.get(y)) {
                l.add(l1.get(x));
                x++;
            } else {
                l.add(l2.get(y));
                y++;
            }
        }

        while (x < l1.size() && l.size() < a) {
            l.add(l1.get(x));
            x++;
        }

        while (y < l2.size() && l.size() < a) {
            l.add(l2.get(y));
            y++;
        }

        return l;
    }

    TreeSet<Integer> mergeSet(List<Integer> l1, List<Integer> l2, int a) {
        TreeSet<Integer> l = new TreeSet<>();

        int x = 0, y = 0;

        while (x < l1.size() && y < l2.size() && l.size() < a) {
            if (l1.get(x) <= l2.get(y)) {
                l.add(l1.get(x));
                x++;
            } else {
                l.add(l2.get(y));
                y++;
            }
        }

        while (x < l1.size() && l.size() < a) {
            l.add(l1.get(x));
            x++;
        }

        while (y < l2.size() && l.size() < a) {
            l.add(l2.get(y));
            y++;
        }

        return l;
    }
}
