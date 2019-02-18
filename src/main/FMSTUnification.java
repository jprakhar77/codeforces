package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FMSTUnification {
    static class KruskalMst {
        class DsuInteger {

            public DsuInteger(int n) {
                this.n = n;
                this.parent = new int[n];
                this.rank = new int[n];
                this.size = new int[n];
            }

            int[] parent;
            int[] rank;
            int[] size;
            int n;


            int createSet(int x) {
                parent[x] = x;
                rank[x] = 0;
                size[x] = 1;
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

                size[fp] = size[rx] + size[ry];

                if (rank[rx] == rank[ry]) {
                    rank[ry] = rank[ry] + 1;
                }

                return fp;
            }
        }

        static class Edge {
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

        Map<Integer, Integer> cm = new HashMap<>();
        Map<Integer, Integer> tm = new HashMap<>();

        List<Edge> kruskal(List<Edge> edges, int n) {

            DsuInteger dsu = new DsuInteger(n);

            for (int i = 0; i < n; i++) {
                dsu.createSet(i);
            }
            edges.sort((e1, e2) -> e1.w - e2.w);

            List<Edge> mst = new ArrayList<>();

            for (int i = 0; i < edges.size(); i++) {
                Edge ce = edges.get(i);

                if (i == 0 || edges.get(i - 1).w < ce.w) {
                    for (int j = i; j < edges.size(); j++) {
                        Edge cce = edges.get(j);
                        if (cce.w > ce.w)
                            break;

                        int u = cce.u;
                        int v = cce.v;

                        if (dsu.findSet(u) != dsu.findSet(v)) {
                            tm.merge(ce.w, 1, (x, y) -> x + y);
                        }
                    }
                }

                int u = ce.u;
                int v = ce.v;

                if (dsu.findSet(u) != dsu.findSet(v)) {
                    cm.merge(ce.w, 1, (x, y) -> x + y);
                    mst.add(ce);
                    dsu.mergeSets(u, v);
                }
            }

            return mst;
        }

    }

    class seg {
        int w;
        int e;

        public seg(int w, int e) {
            this.w = w;
            this.e = e;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();
        int m = in.nextInt();

        List<KruskalMst.Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();

            u--;
            v--;

            edges.add(new KruskalMst.Edge(i, u, v, w));

        }

        KruskalMst mst = new KruskalMst();

        mst.kruskal(edges, n);

        Map<Integer, Integer> tm = mst.tm;

        Map<Integer, Integer> cm = mst.cm;

        long ans = 0;
        //List<seg> segs = new ArrayList<>();
        for (int key : tm.keySet()) {

            if (cm.containsKey(key)) {
                int val = tm.get(key);
                ans += val - cm.get(key);
                //segs.add(new seg(key, ));
            }
        }

//        long ans = 0;
//        long lv = 1000000001;
//
//        for (int i = segs.size() - 1; i >= 0; i--) {
//            seg cs = segs.get(i);
//            if (i == segs.size() - 1 || segs.get(i + 1).w > cs.w + 1) {
//                lv = cs.w + 1;
//            }
//
//            ans += cs.e * (lv - cs.w);
//        }

        out.print(ans);
    }
}
