package library;

public class PointQueryRangeUpdate {
    double[] tree;
    int n;
    double[] a;
    double[] lazy;

    int inf = Integer.MAX_VALUE;

    public PointQueryRangeUpdate(double[] a, int n) {
        super();
        this.a = a;
        this.n = n;
        this.tree = new double[4 * n];
        this.lazy = new double[4 * n];
    }

    public double build(int i, int rs, int re) {
        if (rs == re) {
            tree[i] = inf;
            return tree[i];
        } else {
            int mid = (rs + re) / 2;

            double left = build(i * 2 + 1, rs, mid);
            double right = build(i * 2 + 2, mid + 1, re);

            tree[i] = Integer.MAX_VALUE;

            return tree[i];
        }
    }

    public double query(int i, int rs, int re, int qs, int qe) {
        lazyAdjustment(i, rs, re);

        if (re < qs || qe < rs) {
            return inf;
        }

        if (rs == re && qs == rs && qe == re) {
            return tree[i];
        }

        int mid = (rs + re) / 2;

        double p1 = query(i * 2 + 1, rs, mid, qs, qe);
        double p2 = query(i * 2 + 2, mid + 1, re, qs, qe);

        return Math.min(p1, p2);
    }

    private void lazyAdjustment(int i, int rs, int re) {
        tree[i] = Math.min(lazy[i], tree[i]);

        if (rs < re) {
            lazy[i * 2 + 1] = Math.min(lazy[i * 2 + 1], tree[i]);

            lazy[i * 2 + 2] = Math.min(lazy[i * 2 + 2], tree[i]);
        }

        lazy[i] = 0;
    }

    public void update(int i, int rs, int re, int us, int ue, int min) {
        lazyAdjustment(i, rs, re);

        if (re < us || ue < rs) {
            return;
        }

        if (us <= rs && ue >= re) {
            if (rs == re) {
                tree[i] = Math.min(min, tree[i]);
                return;
            }
            lazy[i] = min;
            return;
        }

        int mid = (rs + re) / 2;

        update(i * 2 + 1, rs, mid, us, ue, min);
        update(i * 2 + 2, mid + 1, re, us, ue, min);
    }
}
