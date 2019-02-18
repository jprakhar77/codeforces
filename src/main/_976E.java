package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class _976E {
    public class SegmentTree {
        long[] st;
        int n;

        public SegmentTree(int n) {
            st = new long[4 * n];
            this.n = n;
        }

        void build(int i, long[] a, int r1, int r2) {
            if (r1 == r2) {
                st[i] = a[r1];
            } else {
                build(i * 2 + 1, a, r1, (r1 + r2) / 2);
                build(i * 2 + 2, a, (r1 + r2) / 2 + 1, r2);

                st[i] = st[i * 2 + 1] + st[i * 2 + 2];
            }
        }

        long query(int i, int ra, int rb, int r1, int r2) {
            if (ra > r2 || rb < r1) {
                return 0;
            }

            if (ra >= r1 && rb <= r2) {
                return st[i];
            }

            long p1 = query(i * 2 + 1, ra, (ra + rb) / 2, r1, r2);
            long p2 = query(i * 2 + 2, (ra + rb) / 2 + 1, rb, r1, r2);

            return p1 + p2;
        }

        long update(int i, int ra, int rb, int ind, long val) {
            if (ra == rb && rb == ind) {
                st[i] = val;
                return st[i];
            }

            if (ra > ind || rb < ind) {
                return st[i];
            }

            long p1 = update(i * 2 + 1, ra, (ra + rb) / 2, ind, val);
            long p2 = update(i * 2 + 2, (ra + rb) / 2 + 1, rb, ind, val);

            return st[i] = p1 + p2;
        }
    }

    class cre {
        long h;
        long d;
        long diff;

        public cre(long h, long d) {
            this.h = h;
            this.d = d;
            this.diff = Math.max(0, h - d);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int a = in.nextInt();

        int b = in.nextInt();

        cre[] cs = new cre[n];

        for (int i = 0; i < n; i++) {
            cs[i] = new cre(in.nextInt(), in.nextInt());
        }

        Arrays.sort(cs, (c1, c2) -> (int) Math.signum(c2.diff - c1.diff));

        long[] d = new long[n];

        for (int i = 0; i < n; i++) {
            d[i] = Math.max(0, cs[i].diff);
        }

        SegmentTree st = new SegmentTree(n);
        st.build(0, d, 0, n - 1);

        long ans = 0;
        long pre = 0;

        for (int i = 0; i < n; i++) {
            pre += cs[i].d;
        }

        if (b == 0) {
            out.println(pre);
            return;
        }

        for (int i = 0; i < n; i++) {
            long ch = cs[i].h;

            for (int j = 0; j < a; j++) {
                ch *= 2;
            }

            long ca = pre;
            ca += Math.max(0, ch - cs[i].d);

            if (i >= b - 1) {
                ca += st.query(0, 0, n - 1, 0, b - 2);
            } else {
                ca += st.query(0, 0, n - 1, 0, i - 1);
                ca += st.query(0, 0, n - 1, i + 1, Math.min(b - 1, n - 1));
            }

            ans = Math.max(ans, ca);
        }

        out.println(ans);

    }
}
