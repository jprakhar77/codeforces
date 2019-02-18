package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _1000E {

    static class edge {
        int v;
        boolean isBridge;

        public edge(int v) {
            this.v = v;
        }

        public edge(int v, boolean isBridge) {
            this.v = v;
            this.isBridge = isBridge;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            edge edge = (edge) o;
            return v == edge.v &&
                    isBridge == edge.isBridge;
        }

        @Override
        public int hashCode() {

            return Objects.hash(v, isBridge);
        }
    }

    static class VertexPair {
        int u;
        int v;

        public VertexPair(int u, int v) {
            this.u = u;
            this.v = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VertexPair that = (VertexPair) o;
            return u == that.u &&
                    v == that.v;
        }

        @Override
        public int hashCode() {

            return Objects.hash(u, v);
        }
    }

    static class DSU<T> {

        Map<T, T> parent = new HashMap<>();
        Map<T, Integer> rank = new HashMap<>();

        T createSet(T x) {
            parent.put(x, x);
            rank.put(x, 0);
            return x;
        }

        T findSet(T x) {
            T par = parent.get(x);
            if (!x.equals(par)) {
                parent.put(x, findSet(par));
                return parent.get(x);
            }
            return par;
        }

        T mergeSets(T x, T y) {
            T rx = findSet(x);
            T ry = findSet(y);

            if (rx.equals(ry)) {
                return rx;
            }

            T fp = null;

            if (rank.get(rx) > rank.get(ry)) {
                parent.put(ry, rx);
                fp = rx;
            } else {
                parent.put(rx, ry);
                fp = ry;
            }

            if (rank.get(rx).equals(rank.get(ry))) {
                rank.put(ry, rank.get(ry) + 1);
            }

            return fp;
        }
    }

    static class Bridges {
        List[] g;
        int n;

        int[] lowlink;
        int[] discovery;

        int time;

        int[] vertexMergedTo;

        Set[] mergedg;

        public Bridges(List[] g) {
            this.g = g;
            this.n = g.length;
            lowlink = new int[n];
            discovery = new int[n];
            vertexMergedTo = new int[n];
            mergedg = new Set[n];
            for (int i = 0; i < n; i++) {
                mergedg[i] = new HashSet<edge>();
            }
        }

        void markBridges(int u) {
            dfs(0, -1);
            Set<VertexPair> vertexPairs = new HashSet<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < g[i].size(); j++) {
                    edge v = (edge) g[i].get(j);
                    if (v.isBridge) {
                        vertexPairs.add(new VertexPair(i, v.v));
                        vertexPairs.add(new VertexPair(v.v, i));
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < g[i].size(); j++) {
                    edge v = (edge) g[i].get(j);
                    if (vertexPairs.contains(new VertexPair(i, v.v))) {
                        v.isBridge = true;
                    }
                }
            }
        }

        private void dfs(int u, int p) {
            time++;
            discovery[u] = time;
            lowlink[u] = discovery[u];

            for (int i = 0; i < g[u].size(); i++) {
                edge v = (edge) g[u].get(i);

                if (discovery[v.v] == 0) {
                    dfs(v.v, u);
                    lowlink[u] = Math.min(lowlink[v.v], lowlink[u]);
                    if (lowlink[v.v] > discovery[u])
                        v.isBridge = true;
                } else if (v.v != p) {
                    lowlink[u] = Math.min(discovery[v.v], lowlink[u]);
                }
            }
        }

        Set[] merge() {

            DSU<Integer> dsu = new DSU<>();

            for (int i = 0; i < n; i++) {
                dsu.createSet(i);
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < g[i].size(); j++) {
                    edge v = (edge) g[i].get(j);

                    if (!v.isBridge) {
                        dsu.mergeSets(i, v.v);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < g[i].size(); j++) {
                    edge v = (edge) g[i].get(j);

                    int fsi = dsu.findSet(i);
                    int fsv = dsu.findSet(v.v);

                    if (fsi != fsv) {
                        mergedg[fsi].add(new edge(fsv));
                        mergedg[fsv].add(new edge(fsi));
                    }
                }
            }

            return mergedg;
        }
    }

    static class DiameterTree {
        Set[] g;

        int node1;
        int dis = -1;

        public DiameterTree(Set[] g) {
            this.g = g;
        }

        void dfs(int u, int p, int cmax) {
            if (cmax > dis) {
                node1 = u;
                dis = cmax;
            }

            for (edge edge : (HashSet<edge>) g[u]) {
                int v = edge.v;
                if (v != p)
                    dfs(v, u, cmax + 1);
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        List[] g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<edge>();
        }

        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();

            g[u - 1].add(new edge(v - 1));
            g[v - 1].add(new edge(u - 1));
        }

        Bridges bridges = new Bridges(g);

        bridges.markBridges(0);

        Set[] mergedg = bridges.merge();

        DiameterTree diameterTree = new DiameterTree(mergedg);

        for (int i = 0; i < n; i++)
            if (mergedg[i].size() > 0) {
                diameterTree.dfs(i, -1, 0);
                break;
            }

        diameterTree.dfs(diameterTree.node1, -1, 0);

        out.println(diameterTree.dis);
    }
}
