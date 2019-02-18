package library;

import java.util.ArrayDeque;
import java.util.List;

public class ShortestPathFasterAlgorithm {
    List[] g;

    long[] dis;
    int source;

    int n;

    public ShortestPathFasterAlgorithm(List[] g, int source) {
        this.g = g;
        this.n = n;
        this.source = source;
    }

    long inf = Long.MAX_VALUE;

    void spfa() {
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        boolean[] queueSet = new boolean[n];

        for (int i = 0; i < n; i++) {
            dis[i] = inf;
        }

        dis[source] = 0;
        queue.addLast(source);
        queueSet[source] = true;

        while (!queue.isEmpty()) {
            Integer vertex = queue.pollFirst();
            queueSet[vertex] = false;

            for (int i = 0; i < g[vertex].size(); i++) {
                edge edge = (edge) g[vertex].get(i);

                int adjacentVertex = edge.vertex;
                int edgeWeight = edge.weight;

                if (dis[vertex] + edgeWeight < dis[adjacentVertex]) {
                    dis[adjacentVertex] = dis[vertex] + edgeWeight;
                    if (!queueSet[adjacentVertex]) {
                        queue.addLast(adjacentVertex);
                        queueSet[adjacentVertex] = true;
                    }
                }
            }
        }
    }

    void addEdge(int u, int v, int w) {
        g[u].add(new edge(v, w));
    }

    class edge {
        int vertex;
        int weight;

        public edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
