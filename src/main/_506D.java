package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class _506D {
    class edge {
        int a;
        int b;
        int c;

        public edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    class DsuInteger {

        public DsuInteger(int n) {
            this.n = n;
            this.parent = new int[n];
            this.rank = new int[n];
        }

        int[] parent;
        int[] rank;
        int n;


        int createSet(int x) {
            parent[x] = x;
            rank[x] = 0;
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

            if (rank[rx] == rank[ry]) {
                rank[ry] = rank[ry] + 1;
            }

            return fp;
        }
    }

    class DSU<T> {

        Map<T, T> parent = new HashMap<>();
        Map<T, Integer> rank = new HashMap<>();

        T createSet(T x) {
            parent.put(x, x);
            rank.put(x, 0);
            return x;
        }

        T findSet(T x) {
            T par = parent.get(x);
            if (!x.equals(par)) {
                parent.put(x, findSet(par));
                return parent.get(x);
            }
            return par;
        }

        T mergeSets(T x, T y) {
            T rx = findSet(x);
            T ry = findSet(y);

            if (rx.equals(ry)) {
                return rx;
            }

            T fp = null;

            if (rank.get(rx) > rank.get(ry)) {
                parent.put(ry, rx);
                fp = rx;
            } else {
                parent.put(rx, ry);
                fp = ry;
            }

            if (rank.get(rx).equals(rank.get(ry))) {
                rank.put(ry, rank.get(ry) + 1);
            }

            return fp;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        edge[] edges = new edge[m];

        List<Integer> majorColors = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            edges[i] = new edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt() - 1);
        }

        int[] cc = new int[m];

        for (int i = 0; i < m; i++) {
            cc[edges[i].c]++;
        }

        int sqrt = 316;

        Map<Integer, DsuInteger> dsuMaj = new HashMap<>();
        Map<Integer, DSU> dsuMin = new HashMap<>();

        Set[] va = new Set[m];

        for (int i = 0; i < m; i++) {
            if (cc[i] > sqrt) {
                majorColors.add(i);
                DsuInteger dsu = new DsuInteger(n);
                for (int j = 0; j < n; j++) {
                    dsu.createSet(j);
                }
                dsuMaj.put(i, dsu);
            } else {
                DSU<Integer> dsu = new DSU<>();
                dsuMin.put(i, dsu);
                va[i] = new HashSet();
            }
        }

        for (int i = 0; i < edges.length; i++) {
            edge edge = edges[i];
            if (cc[edge.c] > sqrt) {
                DsuInteger dsu = dsuMaj.get(edge.c);
                dsu.mergeSets(edge.a, edge.b);
            } else {
                va[edge.c].add(edge.a);
                va[edge.c].add(edge.b);
            }
        }

        Map[] ma = new Map[n];

        for (int i = 0; i < n; i++) {
            ma[i] = new HashMap<Integer, Integer>();
        }

        for (int i = 0; i < m; i++) {
            if (va[i] != null) {
                DSU<Integer> dsu = dsuMin.get(i);

                for (int num : (Set<Integer>) va[i]) {
                    dsu.createSet(num);
                }
            }
        }

        for (int i = 0; i < edges.length; i++) {
            edge edge = edges[i];
            if (va[edge.c] != null) {
                DSU<Integer> dsu = dsuMin.get(edge.c);
                dsu.mergeSets(edge.a, edge.b);
            }
        }

        for (int i = 0; i < m; i++) {
            if (va[i] != null) {
                DSU<Integer> dsu = dsuMin.get(i);

                for (Integer ca : (Set<Integer>) va[i]) {
                    for (Integer cb : (Set<Integer>) va[i]) {
                        if (ca < cb) {
                            int fs1 = dsu.findSet(ca);
                            int fs2 = dsu.findSet(cb);

                            if (fs1 == fs2) {
                                int min = ca;
                                int max = cb;

                                ma[min].merge(max, 1, (x, y) -> (int) x + (int) y);
                            }
                        }
                    }
                }
            }
        }

        int q = in.nextInt();

        while (q-- > 0) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            int min = min(u, v);
            int max = max(u, v);

            long ans = (int) ma[min].getOrDefault(max, 0);

            for (int i = 0; i < majorColors.size(); i++) {
                int c = majorColors.get(i);

                DsuInteger dsu = dsuMaj.get(c);

                int fs1 = dsu.findSet(u);
                int fs2 = dsu.findSet(v);

                if (fs1 == fs2) {
                    ans++;
                }
            }

            out.println(ans);
        }
    }
}
