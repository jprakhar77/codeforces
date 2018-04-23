package library;

class LazySegmentTree {
    double[] tree;
    int n;
    double[] a;
    double[] lazyadd;
    double[] lazysub;

    public LazySegmentTree(double[] a, int n) {
        super();
        this.a = a;
        this.n = n;
        this.tree = new double[4 * n];
        this.lazyadd = new double[4 * n];
        this.lazysub = new double[4 * n];
    }

    public double build(int i, int rs, int re) {
        if (rs == re) {
            tree[i] = a[rs];
            return tree[i];
        } else {
            int mid = (rs + re) / 2;

            double left = build(i * 2 + 1, rs, mid);
            double right = build(i * 2 + 2, mid + 1, re);

            tree[i] = (left + right);

            return tree[i];
        }
    }

    public double query(int i, int rs, int re, int qs, int qe) {
        lazyAdjustment(i, rs, re);

        if (re < qs || qe < rs) {
            return 0;
        }

        if (qs <= rs && qe >= re) {
            return tree[i];
        }

        int mid = (rs + re) / 2;

        double p1 = query(i * 2 + 1, rs, mid, qs, qe);
        double p2 = query(i * 2 + 2, mid + 1, re, qs, qe);

        return p1 + p2;
    }

    private void lazyAdjustment(int i, int rs, int re) {
        tree[i] += lazyadd[i] * (re - rs + 1) - lazysub[i] * tree[i];

        if (rs < re) {
            lazyadd[i * 2 + 1] += lazyadd[i] - lazysub[i] * lazyadd[i * 2 + 1];
            lazysub[i * 2 + 1] += lazysub[i] - lazysub[i] * lazysub[i * 2 + 1];

            lazyadd[i * 2 + 2] += lazyadd[i] - lazysub[i] * lazyadd[i * 2 + 2];
            lazysub[i * 2 + 2] += lazysub[i] - lazysub[i] * lazysub[i * 2 + 2];
        }

        lazyadd[i] = 0;
        lazysub[i] = 0;
    }

    public double update(int i, int rs, int re, int us, int ue, double add, double sub) {
        lazyAdjustment(i, rs, re);

        if (re < us || ue < rs) {
            return 0;
        }

        if (us <= rs && ue >= re) {
            lazyadd[i] = add;
            lazysub[i] = sub;
            return (re - rs + 1) * add - sub * tree[i];
        }

        int mid = (rs + re) / 2;

        double p1 = update(i * 2 + 1, rs, mid, us, ue, add, sub);
        double p2 = update(i * 2 + 2, mid + 1, re, us, ue, add, sub);

        tree[i] += (p1 + p2);

        return (p1 + p2);
    }
}
