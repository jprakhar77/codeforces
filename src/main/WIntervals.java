package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.PriorityQueue;

public class WIntervals {


    class SimpleLazySegmentTree {
        long[] tree;
        int n;
        long[] lazy;
        boolean[] lu;
        long[] a;

        public SimpleLazySegmentTree(long[] a, int n) {
            super();
            this.n = n;
            this.a = a;
            this.tree = new long[4 * n];
            this.lazy = new long[4 * n];
            this.lu = new boolean[4 * n];
        }

        public long build(int i, int rs, int re) {
            if (rs == re) {
                tree[i] = a[rs];
                return tree[i];
            } else {
                int mid = (rs + re) / 2;

                long left = build(i * 2 + 1, rs, mid);
                long right = build(i * 2 + 2, mid + 1, re);

                tree[i] = Math.max(left, right);

                return tree[i];
            }
        }

        long minf = Long.MIN_VALUE;

        public long query(int i, int rs, int re, int qs, int qe) {
            lazyAdjustment(i, rs, re);

            if (re < qs || qe < rs) {
                return minf;
            }

            if (qs <= rs && qe >= re) {
                return tree[i];
            }

            int mid = (rs + re) / 2;

            long p1 = query(i * 2 + 1, rs, mid, qs, qe);
            long p2 = query(i * 2 + 2, mid + 1, re, qs, qe);

            return Math.max(p1, p2);
        }

        private void lazyAdjustment(int i, int rs, int re) {
            if (!lu[i])
                return;

            //Do something with the lazy value
            //tree[i] = pst.query(pst.roots[rs], pst.roots[re + 1], 0, pst.mv, lazy[i]);
            tree[i] += lazy[i];

            if (rs < re) {
                lazy[i * 2 + 1] += lazy[i];
                lu[i * 2 + 1] = true;

                lazy[i * 2 + 2] += lazy[i];
                lu[i * 2 + 2] = true;
            }

            lu[i] = false;
            lazy[i] = 0;
        }

        public long update(int i, int rs, int re, int us, int ue, long add) {
            if (re < us || ue < rs) {
                lazyAdjustment(i, rs, re);
                return tree[i];
            }

            if (us <= rs && ue >= re) {
                lazy[i] += add;
                lu[i] = true;
                lazyAdjustment(i, rs, re);
                return tree[i];
            }

            lazyAdjustment(i, rs, re);

            int mid = (rs + re) / 2;

            long p1 = update(i * 2 + 1, rs, mid, us, ue, add);
            long p2 = update(i * 2 + 2, mid + 1, re, us, ue, add);

            tree[i] = Math.max(p1, p2);

            return tree[i];
        }
    }

    class up {
        int l;
        int r;

        long a;

        public up(int l, int r, long a) {
            this.l = l;
            this.r = r;
            this.a = a;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        PriorityQueue<up> pq = new PriorityQueue<>((u1, u2) ->
        {
            return u1.r - u2.r;
        });

        int n = in.nextInt();
        int m = in.nextInt();

        long[] dp = new long[n];

        Arrays.fill(dp, Long.MIN_VALUE);

        SimpleLazySegmentTree st = new SimpleLazySegmentTree(dp, n);
        st.build(0, 0, n - 1);

        up[] ups = new up[m];
        for (int i = 0; i < m; i++) {
            ups[i] = new up(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
        }

        Arrays.sort(ups, (u1, u2) -> u1.l - u2.l);

        int j = 0;


        long ans = 0;
        long cur = 0;
        for (int i = 0; i < n; i++) {
            while (j < m && ups[j].l == i) {
                cur += ups[j].a;
                pq.add(ups[j]);
                if (ups[j].l > 0) {
                    st.update(0, 0, n - 1, 0, ups[j].l - 1, ups[j].a);
                }
                j++;
            }

            while (!pq.isEmpty() && pq.peek().r + 1 == i) {
                up cu = pq.poll();

                if (cu.l > 0) {
                    st.update(0, 0, n - 1, 0, cu.l - 1, -cu.a);
                }

                cur -= cu.a;
            }

            dp[i] = st.query(0, 0, n - 1, 0, i - 1);

            dp[i] = Math.max(dp[i], cur);

            st.update(0, 0, n - 1, i, i, -Long.MIN_VALUE + dp[i]);

            ans = Math.max(ans, dp[i]);
        }

        //ans = Math.max(ans, st.query(0, 0, n - 1, 0, n - 1));

        out.println(ans);
    }
}
