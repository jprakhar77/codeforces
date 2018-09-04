package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CentroidDecomposition {
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

    public CentroidDecomposition(List[] g, int n) {
        this.g = g;
        this.n = n;
        this.cdg = new List[n];
        for (int i = 0; i < n; i++) {
            this.cdg[i] = new ArrayList<vertex>();
        }
        this.isCentroid = new boolean[n];
    }

    vertex decompose(int src) {
        vertex centroid = findCentroid(src);
        isCentroid[centroid.v] = true;

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
