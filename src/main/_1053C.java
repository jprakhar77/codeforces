package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1053C {
    int mod = 1000000007;

    class SegmentTree {
        long[] st;
        int n;

        public SegmentTree(int n) {
            st = new long[4 * n];
            this.n = n;
        }

        void build(int i, long[] a, int r1, int r2, boolean ism) {
            if (r1 == r2) {
                st[i] = a[r1];
            } else {
                build(i * 2 + 1, a, r1, (r1 + r2) / 2, ism);
                build(i * 2 + 2, a, (r1 + r2) / 2 + 1, r2, ism);

                st[i] = st[i * 2 + 1] + st[i * 2 + 2];
                if (ism)
                    st[i] %= mod;
            }
        }

        long query(int i, int ra, int rb, int r1, int r2, boolean ism) {
            if (ra > r2 || rb < r1) {
                return 0;
            }

            if (ra >= r1 && rb <= r2) {
                return st[i];
            }

            long p1 = query(i * 2 + 1, ra, (ra + rb) / 2, r1, r2, ism);
            long p2 = query(i * 2 + 2, (ra + rb) / 2 + 1, rb, r1, r2, ism);

            if (ism)
                return (p1 + p2) % mod;
            return (p1 + p2);
        }

        long update(int i, int ra, int rb, int ind, long val, boolean ism) {
            if (ra == rb && rb == ind) {
                st[i] = val % mod;
                return st[i];
            }

            if (ra > ind || rb < ind) {
                return st[i];
            }

            long p1 = update(i * 2 + 1, ra, (ra + rb) / 2, ind, val, ism);
            long p2 = update(i * 2 + 2, (ra + rb) / 2 + 1, rb, ind, val, ism);

            st[i] = p1 + p2;
            if (ism)
                st[i] %= mod;

            return st[i];
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int q = in.nextInt();

        int[] a = new int[n];

        long[] w = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
        }

//        long[] pre = new long[n];
//        long[] suf = new long[n];
//
//        pre[0] = w[0];
//
//        for (int i = 1; i < n; i++) {
//            pre[i] = w[i] + pre[i - 1];
//        }
//
//        suf[n - 1] = w[n - 1];
//
//        for (int i = n - 2; i >= 0; i--) {
//            suf[i] = suf[i + 1] + w[i];
//        }

        long[] preg = new long[n];

        for (int i = 1; i < n; i++) {
            preg[i] = preg[i - 1] + a[i] - a[i - 1] - 1;
        }

        long[] sufg = new long[n];

        for (int i = n - 2; i >= 0; i--) {
            sufg[i] = sufg[i + 1] + a[i + 1] - a[i] - 1;
        }

        long[] pregw = new long[n];

        for (int i = 0; i < n; i++) {
            pregw[i] = preg[i] * w[i];
            pregw[i] %= mod;
        }

        long[] sufgw = new long[n];

        for (int i = 0; i < n; i++) {
            sufgw[i] = sufg[i] * w[i];
            sufgw[i] %= mod;
        }

        SegmentTree pst = new SegmentTree(n);

        pst.build(0, pregw, 0, n - 1, true);

        SegmentTree sst = new SegmentTree(n);

        sst.build(0, sufgw, 0, n - 1, true);

        SegmentTree wst = new SegmentTree(n);

        wst.build(0, w, 0, n - 1, false);


        while (q-- > 0) {
            int x = in.nextInt();
            int y = in.nextInt();

            if (x < 0) {
                x = -x;
                wst.update(0, 0, n - 1, x - 1, y, false);

                pst.update(0, 0, n - 1, x - 1, preg[x - 1] * y, true);

                sst.update(0, 0, n - 1, x - 1, sufg[x - 1] * y, true);
            } else {
                x--;
                y--;

                long ts = wst.query(0, 0, n - 1, x, y, false);

                int lo = x;
                int hi = y;

                int li = hi;
                while (lo <= hi) {
                    int mid = (lo + hi) / 2;

                    long cs = wst.query(0, 0, n - 1, x, mid, false);

                    if (cs >= (ts + 1) / 2) {
                        li = Math.min(li, mid);
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                }

                long bg = preg[li];

                long fsum = pst.query(0, 0, n - 1, li, y, true);
                fsum -= bg * wst.query(0, 0, n - 1, li, y, true);
                fsum %= mod;

                long bsum = 0;
                if (li > x) {
                    bsum = sst.query(0, 0, n - 1, x, li - 1, true);
                    bsum -= sufg[li] * wst.query(0, 0, n - 1, x, li - 1, true);
                    bsum %= mod;
                }

                fsum += bsum;
                fsum %= mod;
                fsum += mod;
                fsum %= mod;

                out.println(fsum);
            }
        }

    }
}
