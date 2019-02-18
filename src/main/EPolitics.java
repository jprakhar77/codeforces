package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class EPolitics {
    class MinCostMaxFlowSpfa {
        class ShortestPathFasterAlgorithm {
            List[] g;

            long[] dis;
            int[] path;
            int source;

            int n;

            public ShortestPathFasterAlgorithm(List[] g, int source) {
                this.g = g;
                this.n = g.length;
                this.source = source;
                this.path = new int[n];
                this.dis = new long[n];
            }

            long inf = Long.MAX_VALUE;

            void spfa() {
                ArrayDeque<Integer> queue = new ArrayDeque<>();

                boolean[] queueSet = new boolean[n];

                Arrays.fill(dis, inf);

                Arrays.fill(path, -1);

                dis[source] = 0;
                queue.addLast(source);
                queueSet[source] = true;

                while (!queue.isEmpty()) {
                    Integer vertex = queue.pollFirst();
                    queueSet[vertex] = false;

                    for (int i = 0; i < g[vertex].size(); i++) {
                        Edge edge = (Edge) g[vertex].get(i);

                        int adjacentVertex = edge.vertex;
                        long edgeWeight = edge.cost;

                        if (dis[vertex] + edgeWeight < dis[adjacentVertex] && edge.capacity - edge.flow > 0) {
                            dis[adjacentVertex] = dis[vertex] + edgeWeight;
                            path[adjacentVertex] = vertex;
                            if (!queueSet[adjacentVertex]) {
                                queue.addLast(adjacentVertex);
                                queueSet[adjacentVertex] = true;
                            }
                        }
                    }
                }
            }
        }

        List[] g;
        int source;
        int target;
        int n;

        int[][] indexToEdge;

        ShortestPathFasterAlgorithm spfa;

        public MinCostMaxFlowSpfa(int n, int source, int target) {
            this.n = n;
            this.g = new List[n];
            for (int i = 0; i < n; i++)
                g[i] = new ArrayList();
            this.source = source;
            this.target = target;
            indexToEdge = new int[n][n];
        }

        class Edge {
            int vertex;
            int flow;
            int capacity;
            long cost;
            int reverseEdgeIndex;

            public Edge(int vertex, int flow, int capacity, long cost, int reverseEdgeIndex) {
                this.vertex = vertex;
                this.flow = flow;
                this.capacity = capacity;
                this.cost = cost;
                this.reverseEdgeIndex = reverseEdgeIndex;
            }
        }

        void addEdge(int u, int v, int capacity, long cost) {
            g[u].add(new Edge(v, 0, capacity, cost, g[v].size()));
            indexToEdge[u][v] = g[u].size() - 1;
            g[v].add(new Edge(u, 0, 0, -cost, g[u].size() - 1));
            indexToEdge[v][u] = g[v].size() - 1;
        }

        boolean checkForPathFromStoT() {
            boolean[] vis = new boolean[n];

            dfs(source, vis);

            return vis[target];
        }

        void dfs(int u, boolean[] vis) {
            vis[u] = true;
            for (int i = 0; i < g[u].size(); i++) {
                Edge edge = (Edge) g[u].get(i);

                if (edge.capacity - edge.flow > 0 && !vis[edge.vertex]) {
                    dfs(edge.vertex, vis);
                }
            }
        }

        FlowAndCost sendFlow(int[] path) {
            long flow = Long.MAX_VALUE;

            long cost = 0;

            int curVertex = target;
            while (path[curVertex] != -1) {
                int prevVertex = path[curVertex];

                Edge edge = (Edge) g[prevVertex].get(indexToEdge[prevVertex][curVertex]);

                flow = Math.min(flow, edge.capacity - edge.flow);

                curVertex = prevVertex;
            }

            curVertex = target;

            while (path[curVertex] != -1) {
                int prevVertex = path[curVertex];

                Edge edge = (Edge) g[prevVertex].get(indexToEdge[prevVertex][curVertex]);

                cost += edge.cost * flow;
                edge.flow += flow;
                ((Edge) g[curVertex].get(edge.reverseEdgeIndex)).flow -= flow;

                curVertex = prevVertex;
            }

            return new FlowAndCost(flow, cost);
        }

        FlowAndCost minCostMaxFlow() {
            long flow = 0;
            long cost = 0;

            while (checkForPathFromStoT()) {
                spfa = new ShortestPathFasterAlgorithm(g, source);

                spfa.spfa();

                int[] path = spfa.path;

                FlowAndCost flowAndCost = sendFlow(path);

                flow += flowAndCost.flow;
                cost += flowAndCost.cost;
            }

            return new FlowAndCost(flow, cost);
        }

        class FlowAndCost {
            long cost;
            long flow;

            public FlowAndCost(long cost, long flow) {
                this.cost = cost;
                this.flow = flow;
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();
        int x = in.nextInt() - 1;
        int y = in.nextInt() - 1;

        int[] a = in.nextIntArray(n);

        List[] g1 = new List[n];

        in.readUndirectedGraph(g1, n, n - 1, 1);

        List[] g2 = new List[n];

        in.readUndirectedGraph(g2, n, n - 1, 1);

        q1 = in.nextInt();

        d1 = new int[n];
        d1num = new int[n];

        Arrays.fill(d1num, -1);

        for (int i = 0; i < q1; i++) {
            int k = in.nextInt();
            int d = in.nextInt();

            d1[k - 1] = d;
            d1num[k - 1] = i;
        }

        q2 = in.nextInt();

        d2 = new int[n];
        d2num = new int[n];

        Arrays.fill(d2num, -1);

        for (int i = 0; i < q2; i++) {
            int k = in.nextInt();
            int d = in.nextInt();

            d2[k - 1] = d;
            d2num[k - 1] = i;
        }

        if (d1[x] != d2[y]) {
            out.println(-1);
            return;
        }

        int tot = d1[x];

        nodes = 2 * n + q1 + q2 + 2;

        mcmf = new MinCostMaxFlowSpfa(nodes, 0, nodes - 1);

        dfs2(x, -1, g1, d1, d1num);
        dfs2(y, -1, g2, d2, d2num);

        for (int i = 0; i < n; i++) {
            if (d1[i] < 0 || d2[i] < 0) {
                out.println(-1);
                return;
            }
        }

        dfs0(x, -1, g1);
        dfs1(y, -1, g2);

        if (!poss) {
            out.println(-1);
            return;
        }

        for (int i = 0; i < n; i++) {
            mcmf.addEdge(1 + q1 + i, 1 + q1 + n + i, 1, -a[i]);
        }

        MinCostMaxFlowSpfa.FlowAndCost fac = mcmf.minCostMaxFlow();

        if (fac.flow != tot) {
            out.println(-1);
            return;
        }

        long ans = -fac.cost;

        out.println(ans);
    }

    MinCostMaxFlowSpfa mcmf;

    int[] d1;
    int[] d1num;

    int[] d2;
    int[] d2num;

    int q1;
    int q2;

    int nodes;

    int n;

    boolean poss = true;

    List<Integer> EMPTY_LIST = Collections.EMPTY_LIST;

    List<Integer> dfs0(int u, int p, List[] g) {

        List<Integer> sl = new ArrayList<>();
        sl.add(u);

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                List<Integer> cl = dfs0(v, u, g);

                sl.addAll(cl);
            }
        }

        if (d1num[u] == -1) {
            return sl;
        } else {
            if (sl.size() < d1[u]) {
                poss = false;
            }
            mcmf.addEdge(0, d1num[u] + 1, d1[u], 0);

            for (int i = 0; i < sl.size(); i++) {
                mcmf.addEdge(d1num[u] + 1, sl.get(i) + q1 + 1, 1, 0);
            }
            return EMPTY_LIST;
        }
    }

    List<Integer> dfs1(int u, int p, List[] g) {

        List<Integer> sl = new ArrayList<>();
        sl.add(u);

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                List<Integer> cl = dfs1(v, u, g);

                sl.addAll(cl);
            }
        }

        if (d2num[u] == -1) {
            return sl;
        } else {
            if (sl.size() < d2[u]) {
                poss = false;
            }
            int dv = 1 + q1 + 2 * n + d2num[u];
            mcmf.addEdge(dv, nodes - 1, d2[u], 0);

            for (int i = 0; i < sl.size(); i++) {
                mcmf.addEdge(sl.get(i) + q1 + 1 + n, dv, 1, 0);
            }
            return EMPTY_LIST;
        }
    }

    int dfs2(int u, int p, List[] g, int[] d, int[] dnum) {

        int cval = 0;
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                int val = dfs2(v, u, g, d, dnum);
                cval += val;
            }
        }

        if (dnum[u] == -1) {
            return cval;
        } else {
            int pval = d[u];
            d[u] += cval;
            return -(pval);
        }
    }
}
