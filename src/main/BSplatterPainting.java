package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

public class BSplatterPainting {
    class query {
        int v;
        int d;
        int c;
        int i;

        public query(int v, int d, int c, int i) {
            this.v = v;
            this.d = d;
            this.c = c;
            this.i = i;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        g = new List[n];

        in.readUndirectedGraph(g, n, m, 1);

        int q = in.nextInt();

        query[] queries = new query[q];

        for (int i = 0; i < q; i++) {
            queries[i] = new query(in.nextInt() - 1, in.nextInt(), in.nextInt(), i);
        }

        Arrays.sort(queries, (q1, q2) -> q2.i - q1.i);

        color = new int[n];
        for (int i = 0; i < q; i++) {
            query cq = queries[i];
            dfs(cq.v, cq.c, cq.d, 0);
        }

        for (int i = 0; i < n; i++) {
            out.println(color[i]);
        }
    }

    List[] g;
    int[] dis;

    private void dfs(int u, int c, int d, int cd) {

        if (color[u] != 0)
            return;

        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.addLast(u);
        color[u] = c;
        dis[u] = 0;

        while (!q.isEmpty()) {
            int ce = q.removeFirst();

            if (dis[ce] == d)
                continue;

            for (int i = 0; i < g[ce].size(); i++) {
                int v = (int) g[ce].get(i);

                if (color[v] == 0) {
                    dis[v] = dis[ce] + 1;
                    color[v] = c;
                    q.addLast(v);
                }
            }
        }
    }

    int[] color;
}
