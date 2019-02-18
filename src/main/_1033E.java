package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;


public class _1033E {
    List[] g;

    class edge {
        int u;
        int v;

        public edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    class DsuInteger {

        public DsuInteger(int n) {
            this.n = n;
            this.parent = new int[n];
            this.rank = new int[n];
            this.size = new int[n];
        }

        int[] parent;
        int[] rank;
        int[] size;
        int n;


        int createSet(int x) {
            parent[x] = x;
            rank[x] = 0;
            size[x] = 1;
            return x;
        }

        int findSet(int x) {
            int par = parent[x];
            if (x != par) {
                parent[x] = findSet(par);
                return parent[x];
            }
            return par;
        }

        int mergeSets(int x, int y) {
            int rx = findSet(x);
            int ry = findSet(y);

            if (rx == ry) {
                return rx;
            }

            int fp = -1;

            if (rank[rx] > rank[ry]) {
                parent[ry] = rx;
                fp = rx;
            } else {
                parent[rx] = ry;
                fp = ry;
            }

            size[fp] = size[rx] + size[ry];

            if (rank[rx] == rank[ry]) {
                rank[ry] = rank[ry] + 1;
            }

            return fp;
        }
    }

    class IsBipartite {
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

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        g = new List[n];

        DsuInteger dsu = new DsuInteger(n);

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
            dsu.createSet(i);
        }

        for (int i = 0; i < n - 1; i++) {
            int minSize = n;
            int vertex = -1;
            for (int j = 0; j < n; j++) {
                int fs = dsu.findSet(j);
                if (dsu.size[fs] < minSize) {
                    minSize = dsu.size[fs];
                    vertex = j;
                }
            }

            int vertexFs = dsu.findSet(vertex);

            Set<Integer> s1 = new HashSet<>();
            Set<Integer> s2 = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if (dsu.findSet(j) == vertexFs) {
                    s2.add(j);
                } else {
                    s1.add(j);
                }
            }

            edge edge = searchEdge(s1, s2, in, out);

            g[edge.u].add(edge.v);
            g[edge.v].add(edge.u);

            dsu.mergeSets(edge.u, edge.v);
        }

        IsBipartite isb = new IsBipartite(g);

        isb.assign2Colors(0);

        HashSet<Integer> partition1 = isb.zeroNodes;
        HashSet<Integer> partition2 = isb.oneNodes;

//        if (isb.zeroColorNodes > isb.oneColorNodes) {
//            partition = isb.oneNodes;
//        } else {
//            partition = isb.zeroNodes;
//        }

        int part1Edges = numOfEdges(partition1, in, out);
        int part2Edges = numOfEdges(partition2, in, out);

        if (part1Edges == 0 && part2Edges == 0) {
            System.out.println("Y " + partition1.size());
            for (int v : partition1) {
                System.out.print((v + 1) + " ");
            }
            System.out.println();
            System.out.flush();
        } else if (part1Edges == 0) {
            TreeSet<Integer> ts1 = new TreeSet<>(partition2);
            TreeSet<Integer> ts2 = new TreeSet<>();

            while (true) {
                int last = ts1.last();
                ts1.remove(last);
                ts2.add(last);
                edge edge = searchEdge(ts1, ts2, in, out);

                if (edge != null) {
                    g[edge.u].add(edge.v);
                    g[edge.v].add(edge.u);

                    IsBipartite isb2 = new IsBipartite(g);
                    isb2.isBipartite(0);

                    System.out.println("N " + isb2.nonBipartiteCycle.size());
                    for (int v : isb2.nonBipartiteCycle) {
                        System.out.print((v + 1) + " ");
                    }
                    System.out.println();
                    System.out.flush();
                    break;
                }
            }
        } else if (part2Edges == 0) {
            TreeSet<Integer> ts1 = new TreeSet<>(partition1);
            TreeSet<Integer> ts2 = new TreeSet<>();

            while (true) {
                int last = ts1.last();
                ts1.remove(last);
                ts2.add(last);
                edge edge = searchEdge(ts1, ts2, in, out);

                if (edge != null) {
                    g[edge.u].add(edge.v);
                    g[edge.v].add(edge.u);

                    IsBipartite isb2 = new IsBipartite(g);
                    isb2.isBipartite(0);

                    System.out.println("N " + isb2.nonBipartiteCycle.size());
                    for (int v : isb2.nonBipartiteCycle) {
                        System.out.print((v + 1) + " ");
                    }
                    System.out.println();
                    System.out.flush();
                    break;
                }
            }
        }

    }

    edge searchEdge(Set<Integer> s1, Set<Integer> s2, InputReader in, OutputWriter out) {
        if (s1.size() < s2.size()) {
            Set<Integer> tem = s1;
            s1 = s2;
            s2 = tem;
        }

//        Set<Integer> s = new HashSet<>(s1);
//        s.addAll(s2);
//
//        int total = numOfEdges(s, in, out);

        while (true) {
            if (s1.size() == 1 && s2.size() == 1) {
                HashSet<Integer> s1s2 = new HashSet<>(s1);
                s1s2.addAll(s2);
                int s1s2Edges = numOfEdges(s1s2, in, out);
                if (s1s2Edges > 0) {
                    return new edge(s1.iterator().next(), s2.iterator().next());
                } else {
                    break;
                }
            }

            if (s1.size() < s2.size()) {
                Set<Integer> tem = s1;
                s1 = s2;
                s2 = tem;
            }

            int s1Size = s1.size();
            Set<Integer> s3 = new HashSet<>();
            Set<Integer> s4 = new HashSet<>();
            int s3Size = s1Size / 2;
            int i = 0;
            for (int v : s1) {
                if (i < s3Size) {
                    s3.add(v);
                } else {
                    s4.add(v);
                }
                i++;
            }

            int s3Edges = numOfEdges(s3, in, out);
            int s2Edges = numOfEdges(s2, in, out);

            HashSet<Integer> s3s2 = new HashSet<>(s3);
            s3s2.addAll(s2);

            int s3s2Edges = numOfEdges(s3s2, in, out);

            if (s3s2Edges > s3Edges + s2Edges) {
                s1 = s3;
            } else {
                s1 = s4;
            }
        }

        return null;
    }

    Map<Set<Integer>, Integer> cache = new HashMap<>();

    int numOfEdges(Set<Integer> s, InputReader in, OutputWriter out) {
        if (s.size() <= 1) {
            return 0;
        }

//        if (s.size() <= 10) {
//            if (cache.containsKey(s)) {
//                return cache.get(s);
//            }
//        }
        System.out.println("? " + s.size());
        for (int num : s) {
            System.out.print((num + 1) + " ");
        }
        System.out.println();
        System.out.flush();

        int edges = in.nextInt();

//        if (s.size() <= 10) {
//            Set<Integer> newSet = Collections.unmodifiableSet(new HashSet<>(s));
//            cache.put(newSet, edges);
//        }

        return edges;
    }
}