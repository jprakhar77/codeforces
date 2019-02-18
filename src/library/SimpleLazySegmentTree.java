package library;

public class SimpleLazySegmentTree {
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

        if (rs < re) {
            lazy[i * 2 + 1] = lazy[i];

            lazy[i * 2 + 2] = lazy[i];
        }

        lazy[i] = 0;
    }

    public long update(int i, int rs, int re, int us, int ue, long add) {
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

        long p1 = update(i * 2 + 1, rs, mid, us, ue, add);
        long p2 = update(i * 2 + 2, mid + 1, re, us, ue, add);

        tree[i] = (p1 + p2);

        return (p1 + p2);
    }
}