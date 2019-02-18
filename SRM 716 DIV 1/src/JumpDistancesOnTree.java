import java.util.ArrayList;
import java.util.List;

public class JumpDistancesOnTree {

    List[] g;

    String impossible = "Impossible";
    String possible = "Possible";

    public String isPossible(int[] p, int[] s) {
        int n = p.length + 1;

        g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < p.length; i++) {
            int u = i + 1;
            int v = p[i];

            g[u].add(v);
            g[v].add(u);
        }

        DiameterTree[] dt = new DiameterTree[n];

        int[] max = new int[n];
        int[] h = null;

        for (int i = 0; i < n; i++) {
            dt[i] = new DiameterTree(g);
            dt[i].dfs(i, -1, 0);
            if (i == 0) {
                h = dt[i].h;
            }
            max[i] = dt[i].dis;
        }

        if (max[0] < s[0]) {
            return impossible;
        }

        int rg = s[0];

        boolean[] vis = new boolean[n];

        vis[0] = true;
        for (int i = 0; i < n; i++) {
            if (rg == 0)
                continue;
            if (h[i] % rg == 0) {
                vis[i] = true;
            }
        }

        for (int i = 1; i < s.length; i++) {
            boolean poss = false;
            for (int j = 0; j < n; j++) {
                if (vis[j]) {
                    if (max[j] >= s[i]) {
                        poss = true;
                        break;
                    }
                }
            }

            if (!poss) {
                return impossible;
            }

            rg = gcd(rg, s[i]);

            for (int j = 0; j < n; j++) {
                if (rg == 0)
                    continue;
                if (h[j] % rg == 0) {
                    vis[j] = true;
                }
            }
        }

        return possible;
    }

    int gcd(int a, int b) {
        if (b == 0)
            return a;
        else return gcd(b, a % b);
    }

    class DiameterTree {
        List[] g;

        int node1;
        int dis = -1;

        int center;

        int[] parent;

        public DiameterTree(List[] g) {
            this.g = g;
            this.parent = new int[g.length];
            this.h = new int[g.length];
        }

        int[] h;

        void dfs(int u, int p, int cmax) {
            if (cmax > dis) {
                node1 = u;
                dis = cmax;
            }

            h[u] = cmax;
            parent[u] = p;

            for (int edge : (List<Integer>) g[u]) {
                int v = edge;
                if (v != p)
                    dfs(v, u, cmax + 1);
            }
        }

        int findCenter(int u) {
            int current = node1;

            for (int i = 0; i < dis / 2; i++) {
                current = parent[current];
            }

            return current;
        }
    }
}
