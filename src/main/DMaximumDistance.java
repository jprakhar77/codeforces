package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class DMaximumDistance {
    class KruskalMst {
        class DsuInteger {

            public DsuInteger(int n) {
                this.n = n;
                this.parent = new int[n];
                this.rank = new int[n];
                this.size = new int[n];
                this.val = new int[n];
            }

            int[] parent;
            int[] rank;
            int[] size;
            int[] val;
            int n;


            int createSet(int x, int v) {
                parent[x] = x;
                rank[x] = 0;
                size[x] = 1;
                val[x] = v;
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

            boolean vi = false;

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


                size[fp] = size[rx] + size[ry];

                if (Math.min(val[rx], val[ry]) != 0) {
                    vi = true;
                }

                val[fp] = val[rx] + val[ry];

                if (rank[rx] == rank[ry]) {
                    rank[ry] = rank[ry] + 1;
                }

                return fp;
            }
        }

        class Edge {
            int i;
            int u;
            int v;
            int w;

            public Edge(int i, int u, int v, int w) {
                this.i = i;
                this.u = u;
                this.v = v;
                this.w = w;
            }
        }

        Integer kruskal(List<Edge> edges, int n, int[] spn) {

            DsuInteger dsu = new DsuInteger(n);

            for (int i = 0; i < n; i++) {
                dsu.createSet(i, spn[i]);
            }
            edges.sort((e1, e2) -> e1.w - e2.w);

            List<Edge> mst = new ArrayList<>();

            int ans = 0;
            for (int i = 0; i < edges.size(); i++) {
                Edge ce = edges.get(i);

                int u = ce.u;
                int v = ce.v;

                if (dsu.findSet(u) != dsu.findSet(v)) {
                    mst.add(ce);
                    dsu.vi = false;
                    dsu.mergeSets(u, v);

                    if (dsu.vi) {
                        ans = ce.w;
                    }
                }
            }

            return ans;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        int k = in.nextInt();

        int[] spn = new int[n];
        for (int i = 0; i < k; i++) {
            spn[in.nextInt() - 1] = 1;
        }

        KruskalMst mst = new KruskalMst();

        List<KruskalMst.Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();

            u--;
            v--;

            if (u == v)
                continue;

            edges.add(mst.new Edge(i, u, v, w));
        }

        int ans = mst.kruskal(edges, n, spn);

        for (int i = 0; i < k; i++) {
            out.print(ans + " ");
        }
    }
}
