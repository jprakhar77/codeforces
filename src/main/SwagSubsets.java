//package main;
//
//import fastio.InputReader;
//import fastio.OutputWriter;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class SwagSubsets {
//    class SimpleLazySegmentTree {
//        long[] tree;
//        int n;
//        long[] a;
//        long[] lazy;
//
//        public SimpleLazySegmentTree(long[] a, int n) {
//            super();
//            this.a = a;
//            this.n = n;
//            this.tree = new long[4 * n];
//            this.lazy = new long[4 * n];
//            Arrays.fill(lazy, 1);
//        }
//
//        public long build(int i, int rs, int re) {
//            if (rs == re) {
//                tree[i] = a[rs];
//                return tree[i];
//            } else {
//                int mid = (rs + re) / 2;
//
//                long left = build(i * 2 + 1, rs, mid);
//                long right = build(i * 2 + 2, mid + 1, re);
//
//                tree[i] = (left + right);
//
//                return tree[i];
//            }
//        }
//
//        public long query(int i, int rs, int re, int qs, int qe) {
//            lazyAdjustment(i, rs, re);
//
//            if (re < qs || qe < rs) {
//                return 0;
//            }
//
//            if (qs <= rs && qe >= re) {
//                return tree[i];
//            }
//
//            int mid = (rs + re) / 2;
//
//            long p1 = query(i * 2 + 1, rs, mid, qs, qe);
//            long p2 = query(i * 2 + 2, mid + 1, re, qs, qe);
//
//            return (p1 + p2) % mod;
//        }
//
//        private void lazyAdjustment(int i, int rs, int re) {
//            if (lazy[i] == 1)
//                return;
//
//            //Do something with the lazy value
//            //tree[i] = pst.query(pst.roots[rs], pst.roots[re + 1], 0, pst.mv, lazy[i]);
//
//            tree[i] *= lazy[i];
//            tree[i] %= mod;
//
//            if (rs < re) {
//                lazy[i * 2 + 1] *= lazy[i];
//                lazy[i * 2 + 1] %= mod;
//
//                lazy[i * 2 + 2] *= lazy[i];
//                lazy[i * 2 + 2] %= mod;
//            }
//
//            lazy[i] = 1;
//        }
//
//        int mod = 1000000007;
//
//        public long update(int i, int rs, int re, int us, int ue, int add) {
//            if (re < us || ue < rs) {
//                lazyAdjustment(i, rs, re);
//                return tree[i];
//            }
//
//            if (us <= rs && ue >= re) {
//                lazy[i] = add;
//                lazyAdjustment(i, rs, re);
//                return tree[i];
//            }
//
//            lazyAdjustment(i, rs, re);
//
//            int mid = (rs + re) / 2;
//
//            long p1 = update(i * 2 + 1, rs, mid, us, ue, add);
//            long p2 = update(i * 2 + 2, mid + 1, re, us, ue, add);
//
//            tree[i] = (p1 + p2) % mod;
//
//            return tree[i];
//        }
//    }
//
//    class SegmentTree {
//        long[] st;
//        int n;
//
//        public SegmentTree(int n) {
//            st = new long[4 * n];
//            this.n = n;
//        }
//
//        void build(int i, long[] a, int r1, int r2) {
//            if (r1 == r2) {
//                st[i] = a[r1];
//            } else {
//                build(i * 2 + 1, a, r1, (r1 + r2) / 2);
//                build(i * 2 + 2, a, (r1 + r2) / 2 + 1, r2);
//
//                st[i] = st[i * 2 + 1] + st[i * 2 + 2];
//            }
//        }
//
//        long query(int i, int ra, int rb, int r1, int r2) {
//            if (ra > r2 || rb < r1) {
//                return 0;
//            }
//
//            if (ra >= r1 && rb <= r2) {
//                return st[i];
//            }
//
//            long p1 = query(i * 2 + 1, ra, (ra + rb) / 2, r1, r2);
//            long p2 = query(i * 2 + 2, (ra + rb) / 2 + 1, rb, r1, r2);
//
//            return p1 + p2;
//        }
//
//        long update(int i, int ra, int rb, int ind, long val) {
//            if (ra == rb && rb == ind) {
//                st[i] = val;
//                return st[i];
//            }
//
//            if (ra > ind || rb < ind) {
//                return st[i];
//            }
//
//            long p1 = update(i * 2 + 1, ra, (ra + rb) / 2, ind, val);
//            long p2 = update(i * 2 + 2, (ra + rb) / 2 + 1, rb, ind, val);
//
//            return st[i] = p1 + p2;
//        }
//    }
//
//    int max = 1000000;
//
//    int mod = 1000000007;
//
//    public void solve(int testNumber, InputReader in, OutputWriter out) {
//
//        int t = in.nextInt();
//
//        long[] ar = new long[max + 1];
//
//        long[] tp = new long[max + 1];
//
//        tp[0] = 1;
//
//        for (int i = 1; i <= max; i++) {
//            tp[i] = 2 * tp[i - 1];
//            tp[i] %= mod;
//        }
//
//        long[] pti = new long[max + 1];
//
//        pti[0] = pow(tp[0], mod - 2, (int) mod);
//        long ti = pow(2, mod - 2, (int) mod);
//        for (int i = 1; i <= max; i++) {
//            pti[i] = pti[i - 1] * ti;
//            pti[i] %= mod;
//        }
//
//        SimpleLazySegmentTree st = new SimpleLazySegmentTree(ar, max + 1);
//
//        SegmentTree st2 = new SegmentTree(max + 1);
//
//        SegmentTree st3 = new SegmentTree(max + 1);
//
//        while (t-- > 0) {
//            int n = in.nextInt();
//
//            int[] a = in.nextIntArray(n);
//            int[] b = in.nextIntArray(n);
//
//            List<ele> eles = new ArrayList<>();
//
//            for (int i = 0; i < n; i++) {
//                eles.add(new ele(a[i], i));
//            }
//
//            eles.sort((e1, e2) -> e1.num - e2.num);
//
//            long ans = 0;
//
//            st.build(0, 0, max);
//            st2.build(0, ar, 0, max);
//            for (int i = 0; i < n; i++) {
//                ele ce = eles.get(i);
//
//                //small
//                long small = st3.query(0, 0, max, 0, ce.num);
//
//                ans += ce.num * tp[(int) small];
//                ans %= mod;
//                long large = st.query(0, 0, max, ce.num + 1, max);
//                ans += (((ce.num * pti[(int) small]) % mod) * large) % mod;
//                ans %= mod;
//
//                st3.update(0, 0, max, ce.num, 1);
//                st.update(0, 0, max, ce.num, ce.num, tp[])
//            }
//        }
//    }
//
//    void _2And2InvPowers(int n, long mod) {
//        long[] pt = new long[n + 1];
//
//        pt[0] = 1;
//
//        for (int i = 1; i <= n; i++) {
//            pt[i] = (pt[i - 1] * 2) % mod;
//        }
//
//        long[] pti = new long[n + 1];
//
//        pti[0] = pow(pt[0], mod - 2, (int) mod);
//        long ti = pow(2, mod - 2, (int) mod);
//        for (int i = 1; i <= n; i++) {
//            pti[i] = pti[i - 1] * ti;
//            pti[i] %= mod;
//        }
//    }
//
//    long pow(long a, long p, int mod) {
//        if (p == 0) {
//            return 1;
//        }
//
//        long t = pow(a, p / 2, mod);
//
//        if (p % 2 != 0) {
//            return (((t * t) % mod) * a) % mod;
//        } else {
//            return (t * t) % mod;
//        }
//    }
//
//    class ele {
//        int num;
//        int i;
//
//        public ele(int num, int i) {
//            this.num = num;
//            this.i = i;
//        }
//    }
//}
