package library;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class edge {
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

class VertexPair {
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

class Bridges {
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