package library;

public class SegmentTree {
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

            st[i] = st[i * 2 + 1] + st[i * 2 + 2];
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

        return p1 + p2;
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

        return st[i] = p1 + p2;
    }
}