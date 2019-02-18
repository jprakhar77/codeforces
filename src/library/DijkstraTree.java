package library;

import java.util.*;

public class DijkstraTree {
    private List[] g;
    int n;

    //incomingEdge stores the edge Id of the Shortest Path tree
    // for each of the vertex except for the source vertex.
    public DijkstraTree(List[] g) {
        this.g = g;
        this.n = g.length;
        this.incomingEdge = new int[n];
    }

    class node {
        int i;
        int node;
        long dis;

        public node(int i, int node, long dis) {
            this.i = i;
            this.node = node;
            this.dis = dis;
        }

        public node(int node, long dis) {
            this.node = node;
            this.dis = dis;
        }
    }

    int[] incomingEdge;

    Map<Integer, Long> shortestDist(int src) {
        PriorityQueue<node> pq = new PriorityQueue<>((n1, n2) -> (int) Math.signum(n1.dis - n2.dis));
        Map<Integer, Long> sd = new HashMap<>();
        Set<Integer> vis = new HashSet<>();

        pq.add(new node(src, 0));
        sd.put(src, 0l);

        while (!pq.isEmpty()) {
            node u = pq.poll();

            if (sd.get(u.node) != u.dis)
                continue;

            vis.add(u.node);

            for (node node : (List<node>) g[u.node]) {
                int v = node.node;

                if (!vis.contains(v)) {
                    if (!sd.containsKey(v) || sd.get(v) > u.dis + node.dis) {
                        incomingEdge[v] = node.i;
                        long newdis = u.dis + node.dis;
                        sd.put(v, newdis);
                        pq.add(new node(v, newdis));
                    }
                }
            }
        }

        return sd;
    }
}
