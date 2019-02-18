package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DBabaeiAndBirthdayCake {
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
                st[i] = Math.max(st[i], val);
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
        int n = in.nextInt();

        Long[] v = new Long[n];
        Long[] v2 = new Long[n];

        for (int i = 0; i < n; i++) {
            long r = in.nextInt();
            long h = in.nextInt();

            v[i] = r * r * h;
            v2[i] = v[i];
        }

        Arrays.sort(v2);

        Map<Long, Integer> cc = new HashMap<>();

        int ind = 0;
        for (long val : v2) {
            cc.put(val, ind++);
        }

        int an = ind;

        SegmentTree st = new SegmentTree(an);

        long ans = v[0];
        st.update(0, 0, an - 1, cc.get(v[0]), v[0]);

        for (int i = 1; i < n; i++) {
            int ci = cc.get(v[i]);
            long cans = v[i] + st.query(0, 0, an - 1, 0, ci - 1);

            ans = Math.max(ans, cans);

            st.update(0, 0, an - 1, cc.get(v[i]), cans);
        }

        out.println(Math.PI * ans);
    }
}
