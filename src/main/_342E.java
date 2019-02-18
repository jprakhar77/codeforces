package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _342E {
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

        Map[] dis;

        int[] closedRed;

        int[] cdpar;

        int inf = (int) 1e7;

        public CentroidDecomposition(List[] g, int n) {
            this.g = g;
            this.n = n;
            this.cdg = new List[n];
            for (int i = 0; i < n; i++) {
                this.cdg[i] = new ArrayList<vertex>();
            }
            this.isCentroid = new boolean[n];
            this.dis = new Map[n];
            for (int i = 0; i < n; i++) {
                this.dis[i] = new HashMap();
            }
            this.closedRed = new int[n];
            for (int i = 0; i < n; i++) {
                this.closedRed[i] = inf;
            }
            this.cdpar = new int[n];
        }

        void caldis(int u) {
            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (!isCentroid[v])
                    dfs1(v, u, 1, u);
            }
        }

        void dfs1(int u, int p, int cd, int mn) {
            dis[mn].put(u, cd);
            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (v != p && !isCentroid[v]) {
                    dfs1(v, u, cd + 1, mn);
                }
            }
        }

        vertex decompose(int src) {
            vertex centroid = findCentroid(src);
            isCentroid[centroid.v] = true;

            caldis(centroid.v);

            if (src == 0) {
                cdroot = centroid;
                cdpar[centroid.v] = -1;
            }

            int u = centroid.v;

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (!isCentroid[v]) {
                    vertex child = decompose(v);
                    cdg[u].add(child);
                    cdpar[child.v] = u;
                }
            }

            return centroid;
        }

        vertex findCentroid(int u) {
            Map<Integer, Integer> subSize = new HashMap<>();
            dfs0(u, -1, subSize);

            int totSize = subSize.get(u);

            int curVertex = u;
            int curParent = -1;

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
                    return new vertex(curVertex);
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

        void markAsRed(int u) {
            closedRed[u] = 0;

            int cn = cdpar[u];

            while (cn != -1) {
                closedRed[cn] = Math.min(closedRed[cn], (int) dis[cn].get(u));
                cn = cdpar[cn];
            }
        }

        int calMinRed(int u) {
            int minred = closedRed[u];

            int cn = cdpar[u];

            while (cn != -1) {
                minred = Math.min(minred, closedRed[cn] + (int) dis[cn].get(u));
                cn = cdpar[cn];
            }

            return minred;
        }
    }


    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

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

        CentroidDecomposition cd = new CentroidDecomposition(g, n);

        cd.decompose(0);

        cd.markAsRed(0);

        while (m-- > 0) {
            int t = in.nextInt();
            int v = in.nextInt() - 1;

            if (t == 1) {
                cd.markAsRed(v);
            } else {
                out.println(cd.calMinRed(v));
            }
        }
    }
}
