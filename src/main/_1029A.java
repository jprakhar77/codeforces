package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _1029A {
    int[] lev;
    List[] g;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            g[u].add(v);
            g[v].add(u);
        }

        lev = new int[n];

        bfs0();

        deg = new int[n];

        vertices = new PriorityQueue<>((v1, v2) -> v2.d - v1.d);

        dfs0(0, -1);

        int ans = 0;
        while (vertices.size() > 0) {
            vertex v = vertices.poll();

            if (deg[v.u] != v.d || deg[v.u] == 0) {
                continue;
            }

            ans++;

            Set<Integer> s = new HashSet<>();

            s.add(v.u);
            deg[v.u] = 0;

            for (int u : (List<Integer>) g[v.u]) {
                if (deg[u] != 0) {
                    s.add(u);
                    deg[u] = 0;
                }
            }

            for (Integer num : s) {
                for (int u : (List<Integer>) g[num]) {
                    if (deg[u] != 0 && !s.contains(u)) {
                        deg[u]--;
                        vertices.add(new vertex(u, deg[u]));
                    }
                }
            }
        }

        out.println(ans);
    }

    int[] deg;

    void dfs0(int u, int p) {

        if (lev[u] > 3) {
            deg[u] = 1;
        }

        for (int v : (List<Integer>) g[u]) {
            if (lev[v] > 3) {
                deg[u]++;
            }

            if (v != p) {
                dfs0(v, u);
            }
        }

        if (lev[u] > 3) {
            vertices.add(new vertex(u, deg[u]));
        } else if (lev[u] == 3) {
            vertices.add(new vertex(u, deg[u]));
        } else {
            deg[u] = 0;
        }

    }

    class vertex {
        int u;
        int d;

        public vertex(int u, int d) {
            this.u = u;
            this.d = d;
        }
    }

    PriorityQueue<vertex> vertices;


    void bfs0() {
        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.addLast(0);

        lev[0] = 1;

        while (q.size() > 0) {
            int e = q.removeFirst();

            for (int v : (List<Integer>) g[e]) {
                if (lev[v] == 0) {
                    lev[v] = lev[e] + 1;
                    q.addLast(v);
                }
            }
        }
    }
}
