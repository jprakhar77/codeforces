package library;

public class MinSegmentTree {

    int[] st;
    int[] a;
    int n;

    public MinSegmentTree(int n, int[] a) {
        this.a = a;
        st = new int[4 * n];
        this.n = n;
    }

    int build(int i, int r1, int r2) {
        if (r1 == r2) {
            st[i] = r1;
            return st[i];
        } else {
            int fi = build(i * 2 + 1, r1, (r1 + r2) / 2);
            int si = build(i * 2 + 2, (r1 + r2) / 2 + 1, r2);

            st[i] = (a[fi] <= a[si]) ? fi : si;

            return st[i];
        }
    }

    int query(int i, int ra, int rb, int r1, int r2) {
        if (ra > r2 || rb < r1) {
            return -1;
        }

        if (ra >= r1 && rb <= r2) {
            return st[i];
        }

        int p1 = query(i * 2 + 1, ra, (ra + rb) / 2, r1, r2);
        int p2 = query(i * 2 + 2, (ra + rb) / 2 + 1, rb, r1, r2);

        if (p1 == -1) {
            return p2;
        } else if (p2 == -1) {
            return p1;
        } else {
            return a[p1] <= a[p2] ? p1 : p2;
        }
    }

}
