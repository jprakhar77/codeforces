package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _500E {
    class SimpleLazySegmentTree {
        int[] tree;
        int n;
        int[] a;
        int[] lazy;

        public SimpleLazySegmentTree(int[] a, int n) {
            super();
            this.a = a;
            this.n = n;
            this.tree = new int[4 * n];
            this.lazy = new int[4 * n];
        }

        public int build(int i, int rs, int re) {
            if (rs == re) {
                tree[i] = a[rs];
                return tree[i];
            } else {
                int mid = (rs + re) / 2;

                int left = build(i * 2 + 1, rs, mid);
                int right = build(i * 2 + 2, mid + 1, re);

                tree[i] = Math.max(left, right);

                return tree[i];
            }
        }

        public int query(int i, int rs, int re, int qs, int qe) {
            lazyAdjustment(i, rs, re);

            if (re < qs || qe < rs) {
                return 0;
            }

            if (qs <= rs && qe >= re) {
                return tree[i];
            }

            int mid = (rs + re) / 2;

            int p1 = query(i * 2 + 1, rs, mid, qs, qe);
            int p2 = query(i * 2 + 2, mid + 1, re, qs, qe);

            return Math.max(p1, p2);
        }

        private void lazyAdjustment(int i, int rs, int re) {
            if (lazy[i] == 0)
                return;

            //Do something with the lazy value
            //tree[i] = pst.query(pst.roots[rs], pst.roots[re + 1], 0, pst.mv, lazy[i]);
            tree[i] = lazy[i];

            if (rs < re) {
                lazy[i * 2 + 1] = lazy[i];

                lazy[i * 2 + 2] = lazy[i];
            }

            lazy[i] = 0;
        }

        public int update(int i, int rs, int re, int us, int ue, int add) {
            if (re < us || ue < rs) {
                lazyAdjustment(i, rs, re);
                return tree[i];
            }

            if (us <= rs && ue >= re) {
                lazy[i] = add;
                lazyAdjustment(i, rs, re);
                return tree[i];
            }

            lazyAdjustment(i, rs, re);

            int mid = (rs + re) / 2;

            int p1 = update(i * 2 + 1, rs, mid, us, ue, add);
            int p2 = update(i * 2 + 2, mid + 1, re, us, ue, add);

            return tree[i] = Math.max(p1, p2);
        }
    }

    class inter {
        int s;
        int e;
        int val;

        public inter(int s, int e, int val) {
            this.s = s;
            this.e = e;
            this.val = val;
        }
    }

    class quer {
        int y;
        int i;

        public quer(int y, int i) {
            this.y = y;
            this.i = i;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] p = new int[n];
        int[] l = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = in.nextInt();
            l[i] = in.nextInt();
        }

        int[] max = new int[n];

        max[n - 1] = p[n - 1] + l[n - 1];

        SimpleLazySegmentTree st = new SimpleLazySegmentTree(max, n);

        st.build(0, 0, n - 1);

        for (int i = n - 2; i >= 0; i--) {
            int maxd = p[i] + l[i];

            int lo = i + 1;
            int hi = n - 1;

            int ans = n;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                if (p[mid] > maxd) {
                    ans = Math.min(ans, mid);
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            ans--;

            if (ans > i)
                max[i] = Math.max(maxd, st.query(0, 0, n - 1, i + 1, ans));
            else max[i] = maxd;

            st.update(0, 0, n - 1, i, i, max[i]);
        }

        int maxp = p[n - 1];

        int[] gap = new int[n];

        Map<Integer, Integer> pmap = new HashMap<>();
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            ts.add(p[i]);
            pmap.put(p[i], i);
        }


        for (int i = n - 2; i >= 0; i--) {
            if (max[i] >= maxp) {
                continue;
            }

            int ceil = ts.ceiling(max[i]);

            int diff = ceil - max[i];

            gap[i] = diff + gap[pmap.get(ceil)];
        }

        int q = in.nextInt();

        List[] qs = new List[n];

        for (int i = 0; i < n; i++) {
            qs[i] = new ArrayList();
        }


        for (int i = 0; i < q; i++) {
            int x = in.nextInt();
            int y = in.nextInt();

            x--;
            y--;

            qs[x].add(new quer(y, i));
        }

        //TreeSet<inter> inters = new TreeSet<>((i1, i2) -> i1.e - i2.e);

//        int extra = 0;
//        for (int i = n - 1; i >= 0; i--) {
//            int cp = p[i];
//
//            int cmax = max[i];
//
//            inter ci = null;
//            while (inters.size() > 0 && (ci = inters.first()).e <= cmax) {
//                inters.remove(ci);
//            }
//
//            if (cmax < maxp) {
//                int ceil = ts.ceiling(cmax);
//
//                int is = cmax;
//                int ie = ceil;
//
//                if (inters.size() == 0 || (ci = inters.first()).s > ie) {
//                    inters.add(new inter(is, ie));
//                }
//            }
//
//            for (quer quer : (List<quer>) qs[i]) {
//
//            }
//        }

        int[] fans = new int[q];

        for (int i = n - 2; i >= 0; i--) {
            int maxd = p[i] + l[i];

            int lo = i + 1;
            int hi = n - 1;

            int ans = n;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                if (p[mid] > maxd) {
                    ans = Math.min(ans, mid);
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            ans--;

            if (ans > i) {
                st.update(0, 0, n - 1, i + 1, ans, max[i]);
            }

            for (quer quer : (List<quer>) qs[i]) {
                int x = i;
                int y = quer.y;

                if (max[x] >= p[y]) {
                    fans[quer.i] = 0;
                } else {
                    int ceil = ts.ceiling(max[x]);

                    int ind = pmap.get(ceil);

                    lo = ind;
                    hi = y;

                    ans = y;
                    while (lo <= hi) {
                        int mid = (lo + hi) / 2;

                        int maxmid = st.query(0, 0, n - 1, mid, mid);
                        if (maxmid >= p[y]) {
                            ans = Math.min(ans, mid);
                            hi = mid - 1;
                        } else {
                            lo = mid + 1;
                        }
                    }

                    fans[quer.i] = gap[x] - gap[ans];
                }
            }
        }

        for (int i = 0; i < q; i++) {
            out.println(fans[i]);
        }
        //out.println(gap[x] - gap[y]);

//            if (max[x] >= p[y]) {
//                out.println(0);
//            } else {
//                int ceil = ts.ceiling(max[x]);
//
//                int ind = pmap.get(ceil);
//
//                int lo = ind;
//                int hi = y;
//
//                int ans = y;
//                while (lo <= hi) {
//                    int mid = (lo + hi) / 2;
//
//                    if (max[mid] >= p[y]) {
//                        ans = Math.min(ans, mid);
//                        hi = mid - 1;
//                    } else {
//                        lo = mid + 1;
//                    }
//                }
//
//                out.println(gap[x] - gap[ans]);
//            }
//        }

    }
}
