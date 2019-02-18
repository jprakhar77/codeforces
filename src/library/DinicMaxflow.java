package library;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

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

    void addEdge(int u, int v, int cap) {
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
                int fa = nv.cap - nv.flow;

                if (level[v] == -1 && fa > 0) {
                    q.add(v);
                    level[v] = pl + 1;
                }
            }
        }

        return level[t] != -1;
    }

    int sendFlow(int u, int[] start, int maxFlow) {
        if (u == t) {
            return maxFlow;
        }

        ArrayList<Node> ul = g.get(u);
        for (; start[u] < ul.size(); start[u]++) {
            int i = start[u];

            Node vn = ul.get(i);
            int v = vn.v;
            int f = vn.cap - vn.flow;

            if (level[v] == level[u] + 1 && f > 0) {
                int flow = sendFlow(v, start, Math.min(f, maxFlow));

                if (flow == 0) {
                    continue;
                }

                vn.flow += flow;
                g.get(v).get(vn.reverseEdgeIndex).flow -= flow;

                return flow;
            }
        }

        return 0;
    }

    int maxFlow() {
        int maxFlow = 0;

        while (bfs()) {
            int[] start = new int[n];

            int rf;
            while ((rf = sendFlow(s, start, Integer.MAX_VALUE)) > 0) {
                maxFlow += rf;
            }
        }

        return maxFlow;
    }

    class Node {
        int v;
        int flow;
        int cap;
        int reverseEdgeIndex;

        public Node(int v, int flow, int cap, int reverseEdgeIndex) {
            super();
            this.v = v;
            this.flow = flow;
            this.cap = cap;
            this.reverseEdgeIndex = reverseEdgeIndex;
        }
    }

}