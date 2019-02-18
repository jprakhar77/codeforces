package library;

import java.util.*;

public class MinCostMaxFlowSpfa {
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


    //Dummy use
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = scanner.nextInt();
            }
        }

        MinCostMaxFlowSpfa mcmf = new MinCostMaxFlowSpfa(2 * n + 2, 0, 2 * n + 1);

        for (int i = 0; i < n; i++) {
            mcmf.addEdge(0, i + 1, 1, 0);
        }

        for (int i = n + 1; i <= 2 * n; i++) {
            mcmf.addEdge(i, 2 * n + 1, 1, 0);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mcmf.addEdge(i + 1, n + 1 + j, 1, a[i][j]);
            }
        }

        FlowAndCost fac = mcmf.minCostMaxFlow();

        System.out.println(fac.cost);
    }
}
