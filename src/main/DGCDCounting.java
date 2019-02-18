package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DGCDCounting {
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

        int[] sieve;

        int[] a;

        public CentroidDecomposition(List[] g, int n) {
            this.g = g;
            this.n = n;
            this.cdg = new List[n];
            for (int i = 0; i < n; i++) {
                this.cdg[i] = new ArrayList<vertex>();
            }
            this.isCentroid = new boolean[n];
        }

        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> cmap = new HashMap<>();

        int cans = 0;

        int solve(int u) {
            map.clear();
            cmap.clear();
            cans = 0;

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (!isCentroid[v]) {
                    dfs(v, u, gcd(a[u], a[v]), 1);
                    for (int key : cmap.keySet()) {
                        map.merge(key, cmap.get(key), (a, b) -> Math.max(a, b));
                    }
                    cmap.clear();
                }
            }

            int num = a[u];

            while (num > 1) {
                int cf = sieve[num];

                if (map.containsKey(cf)) {
                    cans = Math.max(cans, map.get(cf));
                }

                //map.merge(cf, dis, (a, b) -> Math.max(a, b));

                num /= cf;
            }

            return cans + 1;
        }

        int gcd(int a, int b) {
            if (b == 0)
                return a;
            else
                return gcd(b, a % b);
        }

        void dfs(int u, int p, int gcd, int dis) {

            int num = gcd;

            while (num > 1) {
                int cf = sieve[num];

                if (map.containsKey(cf)) {
                    cans = Math.max(cans, dis + map.get(cf));
                }

                cmap.merge(cf, dis, (a, b) -> Math.max(a, b));

                while (num % cf == 0) {
                    num /= cf;
                }
            }

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (v != p && !isCentroid[v]) {
                    dfs(v, u, gcd(gcd, a[v]), dis + 1);
                }
            }
        }

        int ans = 0;

        vertex decompose(int src) {
            vertex centroid = findCentroid(src);
            isCentroid[centroid.v] = true;

            int cans = solve(centroid.v);

            ans = Math.max(ans, cans);

            if (src == 0)
                cdroot = centroid;

            int u = centroid.v;

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (!isCentroid[v]) {
                    vertex child = decompose(v);
                    cdg[u].add(child);
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
    }

    int max = 200000;

    int[] sieve;

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        int o = 0;
        for (int val : a) {
            if (val == 1) {
                o++;
            }
        }

        if (o == n) {
            out.println(0);
            return;
        }

        List[] g = new List[n];

        in.readUndirectedGraph(g, n, n - 1, 1);

        sieve = sieve(max);

        CentroidDecomposition cd = new CentroidDecomposition(g, n);

        cd.sieve = sieve;
        cd.a = a;
        cd.decompose(0);

        out.println(cd.ans);
    }

    int[] sieve(int n) {
        int[] sieve = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            sieve[i] = i;
        }

        for (int i = 2; i * i <= n; i++) {
            if (sieve[i] == i) {
                for (int j = i; j * i <= n; j++) {
                    if (sieve[j * i] == j * i)
                        sieve[j * i] = i;
                }
            }
        }

        return sieve;
    }
}
