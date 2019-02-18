package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _1051F {
    static class node {
        int v;
        int w;

        public node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    class DsuInteger {

        public DsuInteger(int n) {
            this.n = n;
            this.parent = new int[n];
            this.rank = new int[n];
        }

        int[] parent;
        int[] rank;
        int n;


        int createSet(int x) {
            parent[x] = x;
            rank[x] = 0;
            return x;
        }

        int findSet(int x) {
            int par = parent[x];
            if (x != par) {
                parent[x] = findSet(par);
                return parent[x];
            }
            return par;
        }

        int mergeSets(int x, int y) {
            int rx = findSet(x);
            int ry = findSet(y);

            if (rx == ry) {
                return rx;
            }

            int fp = -1;

            if (rank[rx] > rank[ry]) {
                parent[ry] = rx;
                fp = rx;
            } else {
                parent[rx] = ry;
                fp = ry;
            }

            if (rank[rx] == rank[ry]) {
                rank[ry] = rank[ry] + 1;
            }

            return fp;
        }
    }

    static class SparseTableLca {
        List[] g;
        int[] p;
        int[] pw;
        int[] h;
        int n;
        int log = 0;

        node r;

        int[][] stp;
        long[][] stpw;


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
            this.stpw = new long[n][log + 1];
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
            this.stpw = new long[n][log + 1];

            for (int i = 0; i < n; i++) {
                stp[i][0] = p[i];
                stpw[i][0] = pw[i];
            }
        }

        void init() {
            for (int i = 1; i <= log; i++) {
                for (int j = 0; j < n; j++) {
                    stp[j][i] = stp[stp[j][i - 1]][i - 1];
                    stpw[j][i] = stpw[j][i - 1] + stpw[stp[j][i - 1]][i - 1];
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
                    int _2i = 1 << i;
                    if (d >= _2i) {
                        cu = stp[cu][i];
                        d -= _2i;
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

        long maxdis(int u, int v) {
            int lca = lca(u, v);

            if (u == v) {
                return 0;
            }

            if (lca == u || lca == v) {
                return maxdisAncestor(u, v);
            }

            return maxdisAncestor(u, lca) + maxdisAncestor(v, lca);
        }

        long maxdisAncestor(int u, int v) {
            if (h[u] < h[v]) {
                int t = u;
                u = v;
                v = t;
            }

            int diff = h[u] - h[v];

            int cu = u;
            long max = 0;
            for (int i = log; i >= 0; i--) {
                int _2i = 1 << i;
                if (_2i <= diff) {
                    diff -= _2i;
                    max += stpw[cu][i];
                    cu = stp[cu][i];
                }
            }

            return max;
        }

    }

    class edge {
        int u;
        int v;
        int w;

        public edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();
        int m = in.nextInt();

        List[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        DsuInteger dsu = new DsuInteger(n);

        for (int i = 0; i < n; i++) {
            dsu.createSet(i);
        }

        List<edge> edges = new ArrayList<>();

        Set<Integer> exnodes = new HashSet<>();

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();

            int fs1 = dsu.findSet(u);
            int fs2 = dsu.findSet(v);

            if (fs1 != fs2) {
                g[u].add(new node(v, w));
                g[v].add(new node(u, w));
                dsu.mergeSets(fs1, fs2);
            } else {
                edges.add(new edge(u, v, w));
                exnodes.add(u);
                exnodes.add(v);
            }
        }

        SparseTableLca lca = new SparseTableLca(g, new node(0, 0));

        //Map<Integer, Map<Integer, Long>> fw = new HashMap<>();

        int ex = exnodes.size();

        int[] ccex = new int[n];

        Arrays.fill(ccex, -1);

        long[][] fw = new long[ex][ex];

        for (int i = 0; i < ex; i++) {
            for (int j = 0; j < ex; j++) {
                fw[i][j] = inf;
            }
        }

        int exv = 0;
        for (int i = 0; i < edges.size(); i++) {
            edge edge = edges.get(i);
            int u = edge.u;
            int v = edge.v;
            long w = edge.w;

            long dis = lca.maxdis(u, v);
            long mindis = Math.min(dis, w);

            if (ccex[u] == -1) {
                ccex[u] = exv;
                exv++;
            }

            if (ccex[v] == -1) {
                ccex[v] = exv;
                exv++;
            }

            int cu = ccex[u];
            int cv = ccex[v];

            fw[cu][cv] = mindis;
            fw[cv][cu] = mindis;
        }

        for (int key : exnodes) {
            int ckey = ccex[key];
            for (int exn : exnodes) {
                int cexn = ccex[exn];
                if (fw[ckey][cexn] == inf) {
                    fw[ckey][cexn] = lca.maxdis(key, exn);
                }
            }
        }

        for (int k : exnodes) {
            k = ccex[k];
            for (int key : exnodes) {
                key = ccex[key];
                for (int exn : exnodes) {
                    exn = ccex[exn];
                    fw[key][exn] = Math.min(fw[key][exn], fw[key][k] + fw[k][exn]);
                }
            }
        }

        long[][] disa = new long[ex][n];
        for (int exn : exnodes) {
            int cexn = ccex[exn];
            for (int i = 0; i < n; i++) {
                disa[cexn][i] = lca.maxdis(i, exn);
            }
        }

        int q = in.nextInt();

        StringBuilder ans = new StringBuilder();

        while (q-- > 0) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            long cdis = lca.maxdis(u, v);

            for (int exn1 : exnodes) {
                exn1 = ccex[exn1];
                for (int exn2 : exnodes) {
                    exn2 = ccex[exn2];
                    cdis = Math.min(cdis,
                            disa[exn1][u] + fw[exn1][exn2] +
                                    disa[exn2][v]);
                }
            }

            ans.append(cdis);
            ans.append("\n");
        }

        out.println(ans.toString());

    }

    long inf = (long) 1e16;
}
