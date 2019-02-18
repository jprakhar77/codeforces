package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class PumpingWater {
    class MinSegmentTree {

        int[] st;
        int[] a;
        int n;

        public MinSegmentTree(int n, int[] a) {
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

                st[i] = (a[fi] >= a[si]) ? fi : si;

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
                return a[p1] >= a[p2] ? p1 : p2;
            }
        }

    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] h = new int[n];

        in.readArray(h, n, 0);

        MinSegmentTree st = new MinSegmentTree(n, h);

        st.build(0, 0, n - 1);

        int max = 0;

        while ((1 << max) < n) {
            max++;
        }

        ans = max;

        solve(0, n - 1, 0, h, st, max);

        out.println(ans);
    }

    int ans;

    void solve(int st, int en, int ch, int[] h, MinSegmentTree mst, int maxh) {
        if (ch >= maxh)
            return;

        if (st > en) {
            ans = Math.min(ans, ch);
            return;
        }

        int n = h.length;

        int ind = mst.query(0, 0, n - 1, st, en);

        solve(ind + 1, en, ch + 1, h, mst, maxh);
        solve(st, ind - 1, ch + 1, h, mst, maxh);
    }
}
