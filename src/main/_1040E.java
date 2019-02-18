package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1040E {
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
        int n;
        int[] size;


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

        int setSize(int x) {
            return size[findSet(x)];
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        long[] c = new long[n];

        for (int i = 0; i < n; i++) {
            c[i] = in.nextLong();
        }

        long xormax = (1l << k) - 1;

        edge[] edges = new edge[m];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            edges[i] = new edge(u, v);
        }

        long xortot = pow(2, k, mod);

        long tot = pow(2, n, mod) * xortot;
        tot %= mod;

        long ans = tot;

        DsuInteger dsu = new DsuInteger(n);

        for (int i = 0; i < n; i++) {
            dsu.createSet(i);
        }

        int cn = n;
        for (int i = 0; i < m && cn >= 2; i++) {
            edge edge = edges[i];

            int u = edge.u;
            int v = edge.v;

            long xor = c[u] ^ c[v];

            long s1 = dsu.setSize(u);
            long s2 = dsu.setSize(v);

            ans -= ((s1 * s2) % mod) * pow(2, cn - 1, mod);

            ans %= mod;

            dsu.mergeSets(u, v);

            cn--;
        }

        ans += mod;

        out.println(ans % mod);
    }

    int mod = (int) 1e9 + 7;

    long pow(long a, long p, int mod) {
        if (p == 0) {
            return 1;
        }

        long t = pow(a, p / 2, mod);

        if (p % 2 != 0) {
            return (((t * t) % mod) * a) % mod;
        } else {
            return (t * t) % mod;
        }
    }
}
