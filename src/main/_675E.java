package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _675E {
    class SegmentTree {
        node[] st;
        int n;

        class node {
            long max;
            int i;

            public node(long max, int i) {
                this.max = max;
                this.i = i;
            }


            public node(node node) {
                this.max = node.max;
                this.i = node.i;
            }
        }

        public SegmentTree(int n) {
            st = new node[4 * n];
            this.n = n;
        }

        node build(int i, int[] a, int r1, int r2) {
            if (r1 == r2) {
                return st[i] = new node(a[r1], r1);
            } else {
                node p1 = build(i * 2 + 1, a, r1, (r1 + r2) / 2);
                node p2 = build(i * 2 + 2, a, (r1 + r2) / 2 + 1, r2);

                if (p1.max >= p2.max) {
                    return st[i] = new node(p1.max, p1.i);
                } else {
                    return st[i] = new node(p2.max, p2.i);
                }
            }
        }

        node query(int i, int ra, int rb, int r1, int r2) {
            if (ra > r2 || rb < r1) {
                return null;
            }

            if (ra >= r1 && rb <= r2) {
                return st[i];
            }

            node p1 = query(i * 2 + 1, ra, (ra + rb) / 2, r1, r2);
            node p2 = query(i * 2 + 2, (ra + rb) / 2 + 1, rb, r1, r2);

            if (p1 == null) {
                return p2;
            } else if (p2 == null) {
                return p1;
            } else {
                if (p1.max >= p2.max) {
                    return new node(p1.max, p1.i);
                } else {
                    return new node(p2.max, p2.i);
                }
            }
        }

        node update(int i, int ra, int rb, int ind, long val) {
            if (ra == rb && rb == ind) {
                st[i].max = val;
                return st[i];
            }

            if (ra > ind || rb < ind) {
                return st[i];
            }

            node p1 = update(i * 2 + 1, ra, (ra + rb) / 2, ind, val);
            node p2 = update(i * 2 + 2, (ra + rb) / 2 + 1, rb, ind, val);

            if (p1 == null) {
                return st[i] = new node(p2);
            } else if (p2 == null) {
                return st[i] = new node(p1);
            } else {
                if (p1.max >= p2.max) {
                    return st[i] = new node(p1.max, p1.i);
                } else {
                    return st[i] = new node(p2.max, p2.i);
                }
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n - 1; i++) {
            a[i] = in.nextInt();
        }

        a[n - 1] = n;

        long[] ans = new long[n];

        ans[n - 2] = 1;

        SegmentTree st = new SegmentTree(n);
        st.build(0, a, 0, n - 1);

        long fans = 1;
        for (int i = n - 3; i >= 0; i--) {
            SegmentTree.node node = st.query(0, 0, n - 1, i + 1, a[i] - 1);

            long ca = ans[node.i];
            ca -= a[i] - 1 - node.i;
            ca += n - a[i];
            ca += a[i] - 1 - i;

            ans[i] = ca;

            fans += ans[i];
        }

        out.print(fans);
    }
}
