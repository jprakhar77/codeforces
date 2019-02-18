package library;

import java.util.*;

public class IsBipartite {
    List[] g;
    int n;

    public IsBipartite(List[] g) {
        this.g = g;
        this.n = g.length;
        this.vis = new boolean[n];
        this.level = new int[n];
        this.color = new int[n];
        this.colorVis = new boolean[n];
        this.parent = new int[n];
    }

    boolean[] vis;
    boolean[] colorVis;
    int[] level;
    int[] color;
    int[] parent;

    int zeroColorNodes = 0;
    int oneColorNodes = 0;

    List<Integer> nonBipartiteCycle = new ArrayList<>();

    //undirected graph, obviously
    boolean isBipartite(int u) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        queue.addLast(u);
        vis[u] = true;
        level[u] = 0;
        parent[u] = -1;

        while (queue.size() > 0) {
            int front = queue.removeFirst();

            for (int v : (List<Integer>) g[front]) {
                if (vis[v]) {
                    if (level[v] == level[front]) {
                        findCycle(v, front);
                        return false;
                    }
                } else {
                    queue.addLast(v);
                    vis[v] = true;
                    level[v] = level[front] + 1;
                    parent[v] = front;
                }
            }
        }

        return true;
    }

    void findCycle(int u, int v) {
        List<Integer> secondPart = new ArrayList<>();

        while (u != v) {
            nonBipartiteCycle.add(u);
            secondPart.add(v);

            u = parent[u];
            v = parent[v];
        }

        nonBipartiteCycle.add(u);

        Collections.reverse(secondPart);

        nonBipartiteCycle.addAll(secondPart);
    }

    void assign2Colors(int u) {
        dfs(u, -1);
    }

    HashSet<Integer> zeroNodes = new HashSet<>();
    HashSet<Integer> oneNodes = new HashSet<>();

    void dfs(int u, int prevColor) {
        if (prevColor == -1) {
            color[u] = 0;
        } else {
            color[u] = 1 - prevColor;
        }

        if (color[u] == 0) {
            zeroColorNodes++;
            zeroNodes.add(u);
        } else {
            oneColorNodes++;
            oneNodes.add(u);
        }

        colorVis[u] = true;

        for (int v : (List<Integer>) g[u]) {
            if (!colorVis[v])
                dfs(v, color[u]);
        }
    }
}