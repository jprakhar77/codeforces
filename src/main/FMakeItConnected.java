package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;

public class FMakeItConnected {
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

    class ver {
        int i;
        long vc;

        public ver(int i, long vc) {
            this.i = i;
            this.vc = vc;
        }
    }

    class edge {
        int u;
        int v;

        public edge(int u, int v) {
            this.u = Math.min(u, v);
            this.v = Math.max(u, v);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            edge edge = (edge) o;
            return u == edge.u &&
                    v == edge.v;
        }

        @Override
        public int hashCode() {
            return Objects.hash(u, v);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        ver[] a = new ver[n];

        for (int i = 0; i < n; i++) {
            a[i] = new ver(i, in.nextLong());
        }

        Arrays.sort(a, (a1, a2) -> (int) Math.signum(a1.vc - a2.vc));

        PriorityQueue<sedge> pq = new PriorityQueue<>((s1, s2) -> (int) Math.signum(s1.w - s2.w));

        for (int i = 0; i < m; i++) {
            pq.add(new sedge(in.nextInt() - 1, in.nextInt() - 1, in.nextLong()));
        }

        DsuInteger dsu = new DsuInteger(n);

        for (int i = 0; i < n; i++) {
            dsu.createSet(i);
        }

        int ce = 0;

        long ans = 0;

        for (int i = 1; i < n; i++) {
            long ec = a[0].vc + a[i].vc;

            int s1 = dsu.findSet(a[0].i);
            int s2 = dsu.findSet(a[i].i);

            if (s1 != s2) {
                while (!pq.isEmpty()) {
                    sedge pe = pq.peek();

                    int s3 = dsu.findSet(pe.u);
                    int s4 = dsu.findSet(pe.v);

                    if (s3 == s4) {
                        pq.poll();
                        continue;
                    } else {
                        if (pe.w < ec) {
                            ans += pe.w;
                            dsu.mergeSets(pe.u, pe.v);
                        } else {
                            break;
                        }
                    }
                }
            }

            s1 = dsu.findSet(a[0].i);
            s2 = dsu.findSet(a[i].i);

            if (s1 != s2) {
                ans += ec;
                dsu.mergeSets(s1, s2);
            }
        }

        while (!pq.isEmpty()) {
            sedge pe = pq.peek();

            int s3 = dsu.findSet(pe.u);
            int s4 = dsu.findSet(pe.v);

            if (s3 == s4) {
                pq.poll();
                continue;
            } else {
                ans += pe.w;
                dsu.mergeSets(pe.u, pe.v);
            }
        }

        out.print(ans);
    }

    class sedge {
        int u;
        int v;
        long w;

        public sedge(int u, int v, long w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
