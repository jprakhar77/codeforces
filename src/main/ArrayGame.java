package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ArrayGame {
    class SegmentTree {
        long[] st;
        int n;

        public SegmentTree(int n) {
            st = new long[4 * n];
            this.n = n;
        }

        void build(int i, int[] a, int r1, int r2) {
            if (r1 == r2) {
                st[i] = a[r1];
            } else {
                build(i * 2 + 1, a, r1, (r1 + r2) / 2);
                build(i * 2 + 2, a, (r1 + r2) / 2 + 1, r2);

                st[i] = Math.max(st[i * 2 + 1], st[i * 2 + 2]);
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

            return Math.max(p1, p2);
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

            return st[i] = Math.max(p1, p2);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();

            long[] a = in.nextLongArray(n);

            long[] premax = in.calculatePrefixMax(a);
            long[] sufmin = in.calculateSuffixMin(a);

            int val = 0;
            for (int i = n - 1; i > 0; i--) {
                long smin = sufmin[i];
                long pmax = premax[i - 1];

                if (pmax < smin) {
                    val++;
                }
            }

            if (val % 2 == 1) {
                out.println("Jeel");
            } else {
                out.println("Ashish");
            }
//            List<Integer> l = new ArrayList<>();
//
//            for (int val : a) {
//                l.add(val);
//            }
//
//            l = l.stream().distinct().collect(Collectors.toList());
//            l.sort(Comparator.naturalOrder());
//
//            Map<Integer, Integer> cc = new HashMap<>();
//
//            int num = 0;
//            for (int val : l) {
//                cc.put(val, num);
//                num++;
//            }
//
//            for (int i = 0; i < n; i++) {
//                a[i] = cc.get(a[i]);
//            }
//
//            int[] b = new int[n];
//            Arrays.fill(b, -1);
//
//            SegmentTree st = new SegmentTree(n);
//            st.build(0, b, 0, n - 1);
//
//            int[] li = new int[n];
//
//            for (int i = n - 1; i >= 0; i--) {
//                long ind = st.query(0, 0, n - 1, 0, a[i]);
//                li[i] = (int)ind;
//                if (b[a[i]] == -1) {
//                    b[a[i]] = i;
//                    st.update(0, 0, n - 1, a[i], i);
//                }
//            }


        }
    }
}
