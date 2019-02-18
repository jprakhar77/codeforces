package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _605A {
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
                st[i] = a[r1];
                return st[i];
            } else {
                int fi = build(i * 2 + 1, r1, (r1 + r2) / 2);
                int si = build(i * 2 + 2, (r1 + r2) / 2 + 1, r2);

                st[i] = Math.max(fi, si);

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
                return Math.max(p1, p2);
            }
        }

        int update(int i, int ra, int rb, int ind, int val) {
            if (ra == rb && rb == ind) {
                st[i] = val;
                return st[i];
            }

            if (ra > ind || rb < ind) {
                return st[i];
            }

            int p1 = update(i * 2 + 1, ra, (ra + rb) / 2, ind, val);
            int p2 = update(i * 2 + 2, (ra + rb) / 2 + 1, rb, ind, val);

            return st[i] = Math.max(p1, p2);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] ans = new int[n + 1];

        MinSegmentTree st = new MinSegmentTree(n + 1, ans);
        st.build(0, 0, n);

        for (int i = 0; i < n; i++) {
            int num = in.nextInt();

            //int pmax = st.query(0, 0, n, 0, num - 1);

            ans[num] = ans[num - 1] + 1;

            st.update(0, 0, n, num, ans[num]);
        }

        int fa = n - 1;
        for (int i = 1; i <= n; i++) {
            fa = Math.min(fa, n - ans[i]);
        }

        out.println(fa);
    }
}
