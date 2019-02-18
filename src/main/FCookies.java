package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FCookies {
    class SimpleLazySegmentTree {
        long[] tree;
        int n;
        long[] a;
        long[] lazy;

        public SimpleLazySegmentTree(long[] a, int n) {
            super();
            this.a = a;
            this.n = n;
            this.tree = new long[4 * n];
            this.lazy = new long[4 * n];
        }

        public long build(int i, int rs, int re) {
            if (rs == re) {
                tree[i] = a[rs];
                return tree[i];
            } else {
                int mid = (rs + re) / 2;

                long left = build(i * 2 + 1, rs, mid);
                long right = build(i * 2 + 2, mid + 1, re);

                tree[i] = (left + right);

                return tree[i];
            }
        }

        public long query(int i, int rs, int re, int qs, int qe) {
            lazyAdjustment(i, rs, re);

            if (re < qs || qe < rs) {
                return 0;
            }

            if (qs <= rs && qe >= re) {
                return tree[i];
            }

            int mid = (rs + re) / 2;

            long p1 = query(i * 2 + 1, rs, mid, qs, qe);
            long p2 = query(i * 2 + 2, mid + 1, re, qs, qe);

            return p1 + p2;
        }

        private void lazyAdjustment(int i, int rs, int re) {
            if (lazy[i] == 0)
                return;

            //Do something with the lazy value
            //tree[i] = pst.query(pst.roots[rs], pst.roots[re + 1], 0, pst.mv, lazy[i]);

            tree[i] += lazy[i];

            if (rs < re) {
                lazy[i * 2 + 1] += lazy[i];

                lazy[i * 2 + 2] += lazy[i];
            }

            lazy[i] = 0;
        }

        public long update(int i, int rs, int re, int us, int ue, long add) {
            if (re < us || ue < rs) {
                lazyAdjustment(i, rs, re);
                return tree[i];
            }

            if (us <= rs && ue >= re) {
                lazy[i] += add;
                lazyAdjustment(i, rs, re);
                return tree[i];
            }

            lazyAdjustment(i, rs, re);

            int mid = (rs + re) / 2;

            long p1 = update(i * 2 + 1, rs, mid, us, ue, add);
            long p2 = update(i * 2 + 2, mid + 1, re, us, ue, add);

            tree[i] = (p1 + p2);

            return (p1 + p2);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();

        T = in.nextLong();

        x = in.nextLongArray(n);
        t = in.nextIntArray(n);

        g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        l = new long[n];

        for (int i = 0; i < n - 1; i++) {
            int p = in.nextInt() - 1;

            g[i + 1].add(p);
            g[p].add(i + 1);

            l[i + 1] = in.nextInt();
        }

        tt = new long[n];

        dfs(0, -1);

        sta = new long[max + 1];
        st = new SimpleLazySegmentTree(sta, max);
        st2 = new SimpleLazySegmentTree(sta, max);

        dp = new long[n];
        val = new long[n];

        dfs2(0, -1);

        dfs3(0, -1);

        out.println(dp[0]);
    }

    int max = 1000000;

    long[] sta;

    long[] x;
    int[] t;
    long T;
    int n;

    List[] g;

    long[] l;

    long[] tt;
    SimpleLazySegmentTree st;
    SimpleLazySegmentTree st2;

    long[] val;

    long[] dp;

    void dfs(int u, int p) {
        if (u == 0) {
            tt[u] = T;
        } else {
            tt[u] = tt[p] - 2 * l[u];
        }

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs(v, u);
            }
        }
    }

    void dfs2(int u, int p) {
        if (tt[u] < 0)
            return;

        st.update(0, 0, max, t[u], t[u], x[u] * t[u]);
        st2.update(0, 0, max, t[u], t[u], x[u]);

        int lo = 0;
        int hi = max;

        int ind = 0;

        long maxsum = -1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            long csum = st.query(0, 0, max, 0, mid);

            if (csum <= tt[u]) {
                maxsum = Math.max(maxsum, csum);
                ind = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        long cook = st2.query(0, 0, max, 0, ind);

        if (ind < max) {
            long cc = st2.query(0, 0, max, ind + 1, ind + 1);

            cook += Math.min(cc, (tt[u] - maxsum) / (ind + 1));
        }

        val[u] = cook;

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs2(v, u);
            }
        }

        st.update(0, 0, max, t[u], t[u], -x[u] * t[u]);
        st2.update(0, 0, max, t[u], t[u], -x[u]);
    }

    void dfs3(int u, int p) {
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs3(v, u);
            }
        }

        List<Long> l = new ArrayList<>();
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                l.add(dp[v]);
            }
        }

        l.sort(Comparator.naturalOrder());

        long ans = val[u];

        for (int i = 0; i < l.size() - 1; i++) {
            ans = Math.max(ans, l.get(i));
        }

        if (u == 0) {
            ans = Math.max(ans, l.get(l.size() - 1));
        }

        dp[u] = ans;
    }
}
