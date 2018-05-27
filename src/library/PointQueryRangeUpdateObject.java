package library;

public class PointQueryRangeUpdateObject {
    obj[] tree;
    int n;
    obj[] lazy;

    public PointQueryRangeUpdateObject(int n) {
        super();
        this.n = n;
        this.tree = new obj[4 * n];
        this.lazy = new obj[4 * n];
    }

    public obj query(int i, int rs, int re, int qs, int qe) {
        lazyAdjustment(i, rs, re);

        if (re < qs || qe < rs) {
            return null;
        }

        if (rs == re && qs == rs && qe == re) {
            return tree[i];
        }

        int mid = (rs + re) / 2;

        obj p1 = query(i * 2 + 1, rs, mid, qs, qe);
        obj p2 = query(i * 2 + 2, mid + 1, re, qs, qe);

        if (p1 == null)
            return p2;
        else
            return p1;
    }

    private void lazyAdjustment(int i, int rs, int re) {
        if (lazy[i] == null)
            return;

        tree[i] = lazy[i];

        if (rs < re) {
            lazy[i * 2 + 1] = tree[i];

            lazy[i * 2 + 2] = tree[i];
        }

        lazy[i] = null;
    }

    public void update(int i, int rs, int re, int us, int ue, obj min) {
        lazyAdjustment(i, rs, re);

        if (re < us || ue < rs) {
            return;
        }

        if (us <= rs && ue >= re) {
            if (rs == re) {
                tree[i] = min;
                return;
            }
            lazy[i] = min;
            return;
        }

        int mid = (rs + re) / 2;

        update(i * 2 + 1, rs, mid, us, ue, min);
        update(i * 2 + 2, mid + 1, re, us, ue, min);
    }


    class obj {
        int i;
        int s;
        int e;
        int sz;

        public obj(int i, int s, int e) {
            this.i = i;
            this.s = s;
            this.e = e;
            this.sz = e - s + 1;
        }
    }

}
