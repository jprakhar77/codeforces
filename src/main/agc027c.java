package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class agc027c {
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

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        String s = in.next();

        DsuInteger dsu = new DsuInteger(n);

        for (int i = 0; i < n; i++) {
            dsu.createSet(i);
        }

        int[] isa = new int[n];
        int[] isb = new int[n];

        List[] g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        boolean[] iss = new boolean[n];

        int ca = 0, cb = 0;
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            if (u == v) {
                iss[u] = true;
                if (s.charAt(u) == 'A') {
                    isa[u]++;
                } else {
                    isb[u]++;
                }
                continue;
            }

            if (s.charAt(u) == 'A') {
                isa[v]++;
            } else {
                isb[v]++;
            }

            if (s.charAt(v) == 'A') {
                isa[u]++;
            } else {
                isb[u]++;
            }

            g[u].add(v);
            g[v].add(u);
        }

        ArrayDeque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (isa[i] == 0 || isb[i] == 0) {
                dq.addLast(i);
            }
        }

        boolean[] isd = new boolean[n];
        while (dq.size() > 0) {
            int ele = dq.removeFirst();

            isd[ele] = true;

            char ch = s.charAt(ele);

            for (int v : (List<Integer>) g[ele]) {
                if (!isd[v]) {
                    if (ch == 'A') {
                        isa[v]--;
                        if (isa[v] <= 0) {
                            dq.addLast(v);
                        }
                    } else {
                        isb[v]--;
                        if (isb[v] <= 0) {
                            dq.addLast(v);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!isd[i]) {
                if (s.charAt(i) == 'A') {
                    ca++;
                    if (iss[i])
                        ca++;
                } else {
                    cb++;
                    if (iss[i])
                        cb++;
                }
            }
        }

        if (ca >= 2 && cb >= 2) {
            out.println("Yes");
        } else {
            out.println("No");
        }

    }
}
