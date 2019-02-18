import java.util.HashSet;
import java.util.Set;

public class SmilesTheFriendshipUnicorn {

    class DiameterTree {
        Set[] g;

        int node1;
        int dis = -1;

        public DiameterTree(Set[] g) {
            this.g = g;
        }

        void dfs(int u, int p, int cmax, boolean[] vis) {
            vis[u] = true;

            if (cmax > dis) {
                node1 = u;
                dis = cmax;
            }

            for (Integer edge : (HashSet<Integer>) g[u]) {
                int v = edge;
                if (!vis[v])
                    dfs(v, u, cmax + 1, vis);
            }
        }
    }

    public String hasFriendshipChain(int n, int[] a, int[] b) {

        int ans = 0;

        Set[] mg = new Set[n];
        for (int j = 0; j < n; j++)
            mg[j] = new HashSet<Integer>();

        for (int i = 0; i < a.length; i++) {
            mg[a[i]].add(b[i]);
            mg[b[i]].add(a[i]);
        }

        for (int i = 0; i < n; i++) {
            boolean[] vis = new boolean[n];
            Set[] g = new Set[n];
            for (int j = 0; j < n; j++)
                g[j] = new HashSet<Integer>();
            dfs(i, g, vis, mg);
            DiameterTree dt = new DiameterTree(g);
            vis = new boolean[n];
            dt.dfs(i, -1, 0, vis);
            vis = new boolean[n];
            dt.dfs(dt.node1, -1, 0, vis);
            ans = Math.max(ans, dt.dis);
        }

        if (ans >= 4)
            return "Yay!";
        else
            return ":(";
    }

    void dfs(int u, Set[] g, boolean[] vis, Set[] mg) {

        vis[u] = true;

        for (Integer edge : (HashSet<Integer>) mg[u]) {
            int v = edge;

            if (!vis[v]) {
                g[u].add(v);
                g[v].add(u);
                dfs(v, g, vis, mg);
            }
        }
    }
}
