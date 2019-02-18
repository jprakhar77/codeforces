package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class AndrewAndTaxi {
    class edge {
        int u;
        int v;
        int w;
        int i;

        public edge(int u, int v, int w, int i) {
            this.u = u;
            this.v = v;
            this.w = w;
            this.i = i;
        }
    }

    class KosarajuScc {
        int n;
        List[] g;

        public KosarajuScc(int n, List[] g) {
            this.n = n;
            this.g = g;
            this.comp = new int[n];
            this.rg = new List[n];
            this.val = new int[n];
        }

        ArrayDeque<Integer> sccOrder = new ArrayDeque<>();

        int[] comp;

        List[] rg;

        int[] val;

        void generateSccOrder() {
            boolean[] vis = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!vis[i])
                    dfs0(i, vis);
            }
        }

        void dfs0(int u, boolean[] vis) {
            vis[u] = true;
            for (Integer v : (List<Integer>) g[u]) {
                if (!vis[v]) {
                    dfs0(v, vis);
                }
            }

            sccOrder.addFirst(u);
        }

        void reverseEdge() {
            for (int i = 0; i < n; i++) {
                rg[i] = new ArrayList<>();
            }

            for (int i = 0; i < n; i++) {
                for (Integer v : (List<Integer>) g[i]) {

                    rg[v].add(i);
                }
            }
        }

        void scc() {
            boolean[] vis = new boolean[n];
            int cn = 0;
            for (Integer v : sccOrder) {

                if (!vis[v]) {
                    dfs1(v, rg, cn, vis);
                    cn++;
                }
            }
        }

        void dfs1(int u, List[] g, int cn, boolean[] vis) {
            comp[u] = cn;
            vis[u] = true;

            for (int j = 0; j < g[u].size(); j++) {
                int v = (int) g[u].get(j);

                if (!vis[v]) {
                    dfs1(v, g, cn, vis);
                }
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        edge[] edges = new edge[m];

        int max = 0;
        for (int i = 0; i < m; i++) {
            edges[i] = new edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt(), i + 1);
            max = Math.max(max, edges[i].w);
        }

        int lo = 0;
        int hi = m;

        Arrays.sort(edges, (e1, e2) -> e1.w - e2.w);

        int ans = max;
        List<Integer> ansl = new ArrayList<>();
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            l.clear();
            if (isp(n, m, edges, mid)) {
                ans = ((mid > 0) ? edges[mid - 1].w : 0);
                hi = mid - 1;
                ansl = new ArrayList<>(l);
            } else {
                lo = mid + 1;
            }
        }

        ansl.sort(Comparator.naturalOrder());

        out.println(ans + " " + ansl.size());
        for (int val : ansl) {
            out.print(val + " ");
        }
    }

    private boolean isp(int n, int m, edge[] edges, int mid) {
        List[] g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        for (int i = mid; i < m; i++) {
            g[edges[i].u].add(edges[i].v);
        }

        KosarajuScc scc = new KosarajuScc(n, g);
        scc.generateSccOrder();
        scc.reverseEdge();
        scc.scc();

        int[] comp = scc.comp;

        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int cc = comp[i];

            if (s.contains(cc)) {
                return false;
            }

            s.add(cc);
        }

        for (int i = 0; i < mid; i++) {
            int c1 = comp[edges[i].u];
            int c2 = comp[edges[i].v];

            if (c1 > c2) {
                l.add(edges[i].i);
            }
        }

        return true;
    }

    List<Integer> l = new ArrayList<>();
}
