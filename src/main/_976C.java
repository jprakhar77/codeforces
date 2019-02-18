package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class _976C {
    public class SegmentTree {
        int[] st;
        int[] a;
        int n;

        public SegmentTree(int n, int[] a) {
            this.a = a;
            st = new int[4 * n];
            this.n = n;
        }

        int build(int i, int r1, int r2) {
            if (r1 == r2) {
                st[i] = r1;
                return st[i];
            } else {
                int fi = build(i * 2 + 1, r1, (r1 + r2) / 2);
                int si = build(i * 2 + 2, (r1 + r2) / 2 + 1, r2);

                st[i] = (a[fi] <= a[si]) ? fi : si;

                return st[i];
            }
        }

        int query(int i, int ra, int rb, int r1, int r2) {
            if (ra > r2 || rb < r1) {
                return -1;
            }

            if (ra >= r1 && rb <= r2) {
                return st[i];
            }

            int p1 = query(i * 2 + 1, ra, (ra + rb) / 2, r1, r2);
            int p2 = query(i * 2 + 2, (ra + rb) / 2 + 1, rb, r1, r2);

            if (p1 == -1) {
                return p2;
            } else if (p2 == -1) {
                return p1;
            } else {
                return a[p1] <= a[p2] ? p1 : p2;
            }
        }

    }

    class seg {
        int s;
        int e;
        int i;

        public seg(int s, int e, int i) {
            this.s = s;
            this.e = e;
            this.i = i;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        seg[] segs = new seg[n];

        for (int i = 0; i < n; i++) {
            segs[i] = new seg(in.nextInt(), in.nextInt(), i + 1);
        }

        Arrays.sort(segs, (s1, s2) ->
        {
            if (s1.s == s2.s) {
                return s2.e - s1.e;
            } else {
                return s1.s - s2.s;
            }
        });

        int min = segs[n - 1].e;
        int mini = n - 1;

        for (int i = n - 2; i >= 0; i--) {
            if (min <= segs[i].e) {
                out.println(segs[mini].i + " " + segs[i].i);
                return;
            }
            if (segs[i].e < min) {
                min = segs[i].e;
                mini = i;
            }
        }

        out.println("-1 -1");

    }
}
