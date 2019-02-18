package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class GPetyaAndGraph {
    class DinicMaxflow {
        int n;
        List<ArrayList<Node>> g;
        int s;
        int t;
        int[] level;

        public DinicMaxflow(int n, int s, int t) {
            this.n = n;
            this.g = new ArrayList<ArrayList<Node>>();
            for (int i = 0; i < n; i++)
                g.add(new ArrayList<Node>());
            this.level = new int[n];
            this.s = s;
            this.t = t;
        }

        void addEdge(int u, int v, long cap) {
            g.get(u).add(new Node(v, 0, cap, g.get(v).size()));
            g.get(v).add(new Node(u, 0, 0, g.get(u).size() - 1));
        }

        boolean bfs() {
            ArrayDeque<Integer> q = new ArrayDeque<Integer>();

            for (int i = 0; i < n; i++) {
                level[i] = -1;
            }

            q.add(s);
            level[s] = 0;

            while (!q.isEmpty()) {
                int u = q.poll();

                ArrayList<Node> ul = g.get(u);
                int pl = level[u];

                for (int i = 0; i < ul.size(); i++) {
                    Node nv = ul.get(i);

                    int v = nv.v;
                    long fa = nv.cap - nv.flow;

                    if (level[v] == -1 && fa > 0) {
                        q.add(v);
                        level[v] = pl + 1;
                    }
                }
            }

            return level[t] != -1;
        }

        long sendFlow(int u, int[] start, long maxFlow) {
            if (u == t) {
                return maxFlow;
            }

            ArrayList<Node> ul = g.get(u);
            for (; start[u] < ul.size(); start[u]++) {
                int i = start[u];

                Node vn = ul.get(i);
                int v = vn.v;
                long f = vn.cap - vn.flow;

                if (level[v] == level[u] + 1 && f > 0) {
                    long flow = sendFlow(v, start, Math.min(f, maxFlow));

                    if (flow == 0) {
                        continue;
                    }

                    vn.flow += flow;
                    g.get(v).get(vn.rei).flow -= flow;

                    return flow;
                }
            }

            return 0;
        }

        long maxFlow() {
            long maxFlow = 0;

            while (bfs()) {
                int[] start = new int[n];

                long rf;
                while ((rf = sendFlow(s, start, Long.MAX_VALUE)) > 0) {
                    maxFlow += rf;
                }
            }

            return maxFlow;
        }

        class Node {
            int v;
            int flow;
            long cap;
            int rei;

            public Node(int v, int flow, long cap, int rei) {
                super();
                this.v = v;
                this.flow = flow;
                this.cap = cap;
                this.rei = rei;
            }
        }

    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = in.nextIntArray(n);

        DinicMaxflow maxflow = new DinicMaxflow(m + n + 2, 0, m + n + 1);

        long sum = 0;
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();

            sum += w;

            maxflow.addEdge(i + 1, m + 1 + u, inf);
            maxflow.addEdge(i + 1, m + 1 + v, inf);

            maxflow.addEdge(0, i + 1, w);
        }

        for (int i = 0; i < n; i++) {
            maxflow.addEdge(m + 1 + i, m + n + 1, a[i]);
        }

        long ans = maxflow.maxFlow();

        out.println(sum - ans);
    }

    long inf = Long.MAX_VALUE;
}
