package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _914E {
    class CentroidDecomposition {
        class vertex {
            int v;

            public vertex(int v) {
                this.v = v;
            }
        }

        List[] g;
        int n;

        vertex cdroot = null;
        List[] cdg;

        boolean[] isCentroid;

        String color;

        public CentroidDecomposition(List[] g, int n, String color) {
            this.g = g;
            this.n = n;
//            this.cdg = new List[n];
//            for (int i = 0; i < n; i++) {
//                this.cdg[i] = new ArrayList<vertex>();
//            }
            this.isCentroid = new boolean[n];
            this.color = color;
            this.ans = new long[n];
            this.temp = new long[n];
        }

        long[] ans;
        long[] temp;
        long cans;

        Map<Integer, Integer> ansMap;

        void solve(int u) {

            int cc = color.charAt(u) - 'a';
            ansMap = new HashMap<>();

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);
                if (!isCentroid[v]) {
                    Map<Integer, Integer> curMap = new HashMap<>();
                    dfs1(v, u, 0, curMap, cc, true);
                    for (Integer num : curMap.keySet()) {
                        //ansMap.merge(num, curMap.get(num), (x, y) -> x + y);
                        int cenNum = (num ^ (1 << cc));
                        long val = curMap.get(num);
                        cans += val * ansMap.getOrDefault(cenNum, 0);
                        for (int j = 0; j < 20; j++) {
                            cans += val * ansMap.getOrDefault((cenNum ^ (1 << j)), 0);
                        }
                    }
                    for (Integer num : curMap.keySet()) {
                        ansMap.merge(num, curMap.get(num), (x, y) -> x + y);
                    }
                }
            }

            ansMap = new HashMap<>();

            for (int i = g[u].size() - 1; i >= 0; i--) {
                int v = (int) g[u].get(i);
                if (!isCentroid[v]) {
                    Map<Integer, Integer> curMap = new HashMap<>();
                    dfs1(v, u, 0, curMap, cc, false);
                    for (Integer num : curMap.keySet()) {
                        ansMap.merge(num, curMap.get(num), (x, y) -> x + y);
                    }
                }
            }

            cans += ansMap.getOrDefault((1 << cc), 0);

            for (int i = 0; i < 20; i++) {
                cans += ansMap.getOrDefault((1 << i) ^ (1 << cc), 0);
            }

            cans++;

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);
                if (!isCentroid[v]) {
                    dfs2(v, u);
                }
            }

//            for (int i = 0; i < g[u].size(); i++) {
//                int v = (int) g[u].get(i);
//                if (!isCentroid[v]) {
//                    dfs3(v, u);
//                }
//            }
        }

        long dfs2(int u, int p) {
            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (v != p && !isCentroid[v]) {
                    //dfs1(v, u, cnum, curMap, cc, f);
                    temp[u] += dfs2(v, u);
                }
            }
            ans[u] += temp[u];
            return temp[u];
        }

        void dfs3(int u, int p) {
            ans[u] += temp[u];
            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (v != p && !isCentroid[v]) {
                    //dfs1(v, u, cnum, curMap, cc, f);
                    dfs3(v, u);
                }
            }
        }

        void dfs1(int u, int p, int num, Map<Integer, Integer> curMap, int cc, boolean f) {
            int cnum = num ^ (1 << (color.charAt(u) - 'a'));
            curMap.merge(cnum, 1, (x, y) -> x + y);

            if (f)
                temp[u] = 0;
            long cans = 0;
            int cenNum = (cnum ^ (1 << cc));
            cans += ansMap.getOrDefault(cenNum, 0);
            for (int j = 0; j < 20; j++) {
                cans += ansMap.getOrDefault((cenNum ^ (1 << j)), 0);
            }

            if (cenNum == 0 || (cenNum & (cenNum - 1)) == 0) {
                if (f)
                    cans++;
            }

            temp[u] += cans;

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (v != p && !isCentroid[v]) {
                    dfs1(v, u, cnum, curMap, cc, f);
                }
            }
        }

        int decompose(int src, int p) {
            int centroid = findCentroid(src, p);
            isCentroid[centroid] = true;

            cans = 0;
            solve(centroid);

            ans[centroid] += cans;

//            if (p == -1)
//                cdroot = centroid;

            int u = centroid;

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (!isCentroid[v]) {
                    int child = decompose(v, u);
                    //cdg[u].add(child);
                }
            }

            return centroid;
        }

        int findCentroid(int u, int p) {
            Map<Integer, Integer> subSize = new HashMap<>();
            dfs0(u, p, subSize);

            int totSize = subSize.get(u);

            int curVertex = u;
            int curParent = p;

            int heavyVertex = -1;

            while (true) {
                boolean isCurCentroid = true;
                for (int i = 0; i < g[curVertex].size(); i++) {
                    int v = (int) g[curVertex].get(i);

                    if (v != curParent && !isCentroid[v]) {
                        if (subSize.get(v) > totSize / 2) {
                            heavyVertex = v;
                            isCurCentroid = false;
                            break;
                        }
                    }
                }
                if (isCurCentroid) {
                    return curVertex;
                }
                curParent = curVertex;
                curVertex = heavyVertex;
            }
        }

        int dfs0(int u, int p, Map<Integer, Integer> subSize) {
            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (v != p && !isCentroid[v]) {
                    int size = dfs0(v, u, subSize);
                    subSize.merge(u, size, (x, y) -> x + y);
                }
            }

            subSize.merge(u, 1, (x, y) -> x + y);

            return subSize.get(u);
        }

    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        List[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            g[u].add(v);
            g[v].add(u);
        }

        String s = in.next();

        CentroidDecomposition cd = new CentroidDecomposition(g, n, s);

        cd.decompose(0, -1);

        //out.println(cd.ans);

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            ans.append(cd.ans[i] + " ");
        }

        out.print(ans.toString());
    }
}
