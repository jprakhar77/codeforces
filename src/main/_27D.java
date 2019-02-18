package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _27D {
    public class Sat {

        int n;
        List[] g;

        int[] comp;
        List[] rg;
        int[] val;

        public Sat(int n, List[] g) {
            this.n = n;
            this.g = g;
            comp = new int[2 * n];

            rg = new List[2 * n];
            val = new int[n];
        }

        List<Integer> topoOrder = new ArrayList<>();

        void topoSort() {

            int[] in = new int[2 * n];

            ArrayDeque<Integer> q = new ArrayDeque<>();

            for (int i = 0; i < 2 * n; i++) {
                for (int j = 0; j < g[i].size(); j++) {
                    int v = (int) g[i].get(j);

                    in[v]++;
                }
            }

            Set<Integer> unvis = new HashSet<>();

            for (int i = 0; i < 2 * n; i++) {
                unvis.add(i);
                if (in[i] == 0) {
                    q.addLast(i);
                    topoOrder.add(i);
                }
            }

            while (!q.isEmpty() || unvis.size() > 0) {

                int ce = -1;

                if (!q.isEmpty())
                    ce = q.removeFirst();
                else
                    ce = unvis.iterator().next();

                unvis.remove(ce);

                topoOrder.add(ce);
                for (int i = 0; i < g[ce].size(); i++) {
                    int v = (int) g[ce].get(i);

                    in[v]--;

                    if (in[v] == 0) {
                        q.addLast(v);
                    }
                }
            }
        }

        void reverseEdge() {
            for (int i = 0; i < 2 * n; i++) {
                rg[i] = new ArrayList<Integer>();
            }

            for (int i = 0; i < 2 * n; i++) {
                for (int j = 0; j < g[i].size(); j++) {
                    int v = (int) g[i].get(j);

                    rg[v].add(i);
                }
            }
        }

        void scc() {
            boolean[] vis = new boolean[2 * n];
            int cn = 0;
            for (int i = 0; i < 2 * n; i++) {

                int v = i;

                if (!vis[v]) {
                    dfs(v, rg, cn, vis);
                    cn++;
                }
            }
        }

        void dfs(int u, List[] g, int cn, boolean[] vis) {
            comp[u] = cn;
            vis[u] = true;

            for (int j = 0; j < g[u].size(); j++) {
                int v = (int) g[u].get(j);

                if (!vis[v]) {
                    dfs(v, g, cn, vis);
                }
            }
        }

        boolean checkSatisfiability() {
            for (int i = 0; i < n; i++) {
                if (comp[i] == comp[n + i]) {
                    return false;
                }
            }

            return true;
        }

        void assignVals() {
            boolean[] vis = new boolean[2 * n];
            for (int i = 0; i < topoOrder.size(); i++) {
                int v = (int) topoOrder.get(i);

                if (!vis[v]) {
                    dfs2(v, rg, vis);
                }
            }
        }

        void dfs2(int u, List[] g, boolean[] vis) {
            if (u >= n) {
                if (val[u - n] == 0) {
                    val[u - n] = -1;
                }
            } else {
                if (val[u] == 0) {
                    val[u] = 1;
                }
            }

            vis[u] = true;

            for (int j = 0; j < g[u].size(); j++) {
                int v = (int) g[u].get(j);

                if (!vis[v]) {
                    dfs2(v, g, vis);
                }
            }
        }
    }

    class path {
        int a;
        int b;

        public path(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();


        path[] path = new path[m];

        for (int i = 0; i < m; i++) {
            path[i] = new path(in.nextInt() - 1, in.nextInt() - 1);
        }

        //inside : x, outside : x'
        List[] g = new List[2 * m];

        for (int i = 0; i < 2 * m; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (isoverlap(path[i], path[j])) {
                    g[i].add(m + j);
                    g[m + i].add(j);
                    g[j].add(m + i);
                    g[m + j].add(i);
                }
            }
        }

        Sat sat = new Sat(m, g);

        sat.topoSort();
        sat.reverseEdge();
        sat.scc();

        if (!sat.checkSatisfiability()) {
            out.println("Impossible");
            return;
        }

        sat.assignVals();

        int[] val = sat.val;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            if (val[i] == -1) {
                sb.append('o');
            } else {
                sb.append('i');
            }
        }

        out.println(sb.toString());
    }

    boolean isoverlap(path a, path b) {

        if (isBetween(a, b.a) && isNotBetween(a, b.b))
            return true;

        if (isBetween(a, b.b) && isNotBetween(a, b.a))
            return true;

        return false;
    }

    boolean isBetween(path p, int a) {
        int min = Math.min(p.a, p.b);
        int max = Math.max(p.a, p.b);

        if (a > min && a < max)
            return true;

        return false;
    }

    boolean isNotBetween(path p, int a) {
        int min = Math.min(p.a, p.b);
        int max = Math.max(p.a, p.b);

        if (a >= min && a <= max)
            return false;

        return true;
    }
}
