package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EMinimalDiameterForest {

    public class DiameterTree {
        List[] g;

        int node1;
        int dis = -1;

        int center;

        int[] parent;

        public DiameterTree(List[] g) {
            this.g = g;
            this.parent = new int[g.length];
        }

        void dfs(int u, int p, int cmax) {
            if (cmax >= dis) {
                node1 = u;
                dis = cmax;
            }

            parent[u] = p;

            for (int edge : (List<Integer>) g[u]) {
                int v = edge;
                if (v != p)
                    dfs(v, u, cmax + 1);
            }
        }

        int findCenter() {
            int current = node1;

            for (int i = 0; i < dis / 2; i++) {
                current = parent[current];
            }

            return current;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        g = new List[n];
        in.readUndirectedGraph(g, n, m, 1);

        vis = new boolean[n];

        List<Integer> centers = new ArrayList<>();

        int ans = 0;

        List<Integer> mxh = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                s.clear();
                dfs(i, -1);

                List[] g2 = new List[n];

                int root = -1;

                for (int j = 0; j < n; j++) {
                    g2[j] = new ArrayList();

                    if (!s.contains(j))
                        continue;

                    root = j;

                    for (int k = 0; k < g[j].size(); k++) {
                        int v = (int) g[j].get(k);

                        if (s.contains(v)) {
                            g2[j].add(v);
                        }
                    }
                }

                DiameterTree dt = new DiameterTree(g2);
                dt.dfs(root, -1, 0);
                dt.dfs(dt.node1, -1, 0);
                centers.add(dt.findCenter());

                // ans = Math.max(ans, dt.dis);

                mxh.add((dt.dis + 1) / 2);
            }
        }

//        mxh.sort(Comparator.reverseOrder());
//
//        if (mxh.size() >= 2) {
//            ans = Math.max(ans, mxh.get(0) + 1 + mxh.get(1));
//        }

        ans = n + 5;


        int fmp = -1;
        for (int j = 0; j < centers.size(); j++) {
            int mp = j;
            for (int i = 0; i < centers.size(); i++) {
                if (i != mp) {
                    g[centers.get(mp)].add(centers.get(i));
                    g[centers.get(i)].add(centers.get(mp));
                }
            }

            DiameterTree dt = new DiameterTree(g);
            dt.dfs(0, -1, 0);
            dt.dfs(dt.node1, -1, 0);

            if (dt.dis < ans) {
                fmp = mp;
                ans = dt.dis;
            }

            for (int i = 0; i < centers.size(); i++) {
                if (i != mp) {
                    g[centers.get(mp)].remove(centers.get(i));
                    g[centers.get(i)].remove(centers.get(mp));
                }
            }
        }

        out.println(ans);

        for (int i = 0; i < centers.size(); i++) {
            if (i != fmp)
                out.println(centers.get(fmp) + 1 + " " + (centers.get(i) + 1));
        }
    }

    boolean[] vis;
    Set<Integer> s = new HashSet<>();

    List[] g;

    void dfs(int u, int p) {
        vis[u] = true;
        s.add(u);
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs(v, u);
            }
        }
    }
}
