package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _781B {
    class Sat {

        int n;
        Set[] g;

        public Sat(int n, Set[] g) {
            this.n = n;
            this.g = g;
            this.comp = new int[2 * n];
            this.rg = new List[2 * n];
            this.val = new int[n];
        }

        ArrayDeque<Integer> sccOrder = new ArrayDeque<>();

        int[] comp;

        List[] rg;

        int[] val;

        void generateSccOrder() {
            boolean[] vis = new boolean[2 * n];
            for (int i = 0; i < 2 * n; i++) {
                if (!vis[i])
                    dfs0(i, vis);
            }
        }

        void dfs0(int u, boolean[] vis) {
            vis[u] = true;
            for (Integer v : (Set<Integer>) g[u]) {
                if (!vis[v]) {
                    dfs0(v, vis);
                }
            }

            sccOrder.addFirst(u);
        }

        void reverseEdge() {
            for (int i = 0; i < 2 * n; i++) {
                rg[i] = new ArrayList<>();
            }

            for (int i = 0; i < 2 * n; i++) {
                for (Integer v : (Set<Integer>) g[i]) {

                    rg[v].add(i);
                }
            }
        }

        void scc() {
            boolean[] vis = new boolean[2 * n];
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
            for (Integer v : sccOrder) {
                if (!vis[v]) {
                    dfs2(v, rg, vis);
                }
            }
        }

        void dfs2(int u, List[] g, boolean[] vis) {
            if (u >= n) {
                if (val[u - n] == 0) {
                    val[u - n] = 1;
                }
            } else {
                if (val[u] == 0) {
                    val[u] = -1;
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

    class vertex {
        int i;
        String s;

        public vertex(int i, String s) {
            this.i = i;
            this.s = s;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            vertex vertex = (vertex) o;
            return i == vertex.i &&
                    Objects.equals(s, vertex.s);
        }

        @Override
        public int hashCode() {

            return Objects.hash(i, s);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        List<vertex> vl = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String s1 = in.next();
            String s2 = in.next();

            String ss1 = s1.substring(0, 3);
            String ss2 = s1.substring(0, 2) + s2.substring(0, 1);

            vertex v1 = new vertex(i, ss1);
            vertex v2 = new vertex(i, ss2);

            vl.add(v1);
            vl.add(v2);
        }

        Set[] g = new Set[4 * n];

        for (int i = 0; i < 4 * n; i++) {
            g[i] = new HashSet<Integer>();
        }

        for (int i = 0; i < n; i++) {
            //!x1 -> x2
            //!x2 -> x1
            //x2 -> !All x1s with same x1 as x2
            //x1 -> !All x1 and x2 with same val
            //x2 -> !''
            g[2 * n + 2 * i].add(2 * i + 1);
            g[2 * n + 2 * i + 1].add(2 * i);
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (vl.get(2 * i).s.equals(vl.get(2 * j).s)) {
                        g[2 * i + 1].add(2 * n + 2 * j);
                        g[2 * i].add(2 * n + 2 * j + 1);
                        g[2 * i].add(2 * n + 2 * j);
                    }
                    if (vl.get(2 * i + 1).s.equals(vl.get(2 * j + 1).s)) {
                        g[2 * i + 1].add(2 * n + 2 * j + 1);
                    }
                    if (vl.get(2 * i).s.equals((vl.get(2 * j + 1).s))) {
                        g[2 * i].add(2 * n + 2 * j + 1);
                    }
                    if (vl.get(2 * i + 1).s.equals((vl.get(2 * j).s))) {
                        g[2 * i + 1].add(2 * n + 2 * j);
                    }
                }
            }
        }

        Sat sat = new Sat(2 * n, g);

        sat.generateSccOrder();
        sat.reverseEdge();
        sat.scc();

        if (!sat.checkSatisfiability()) {
            out.println("NO");
            return;
        }

        out.println("YES");
        sat.assignVals();

        for (int i = 0; i < 2 * n; i++) {
            if (sat.val[i] == 1) {
                if (i % 2 == 1 && sat.val[i - 1] == 1)
                    continue;

                out.println(vl.get(i).s);
            }
        }

    }
}
