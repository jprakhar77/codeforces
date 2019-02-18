package library;

public class MinSegmentTreeDescent {

    int[] st;
    int[] a;
    int n;

    public MinSegmentTreeDescent(int n, int[] a) {
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

    //Call init before query for descent to work
    void init() {
        rae = -1;
        rbe = -1;
        min = -1;
        ans = -1;
        ind = -1;
    }

    int query(int i, int ra, int rb, int r1, int r2) {
        if (ra > r2 || rb < r1) {
            return -1;
        }

        if (ra >= r1 && rb <= r2) {
            if (min == -1 || a[st[i]] < min) {
                min = a[st[i]];
                rae = ra;
                rbe = rb;
                ind = i;
            }
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

    int ind = -1;
    int rae = -1;
    int rbe = -1;
    int min = -1;
    int ans = -1;

    //Call query before descent for this to work with these params: ind, rae, rbe, min
    //ans will store the index of minimum element after the method returns
    void des(int i, int r1, int r2, int min) {
        if (r1 == r2) {
            ans = r1;
        } else {
            if (a[st[i * 2 + 1]] == min) {
                des(i * 2 + 1, r1, (r1 + r2) / 2, min);
            } else {
                des(i * 2 + 2, (r1 + r2) / 2 + 1, r2, min);
            }
        }
    }

    int first(int i, int ra, int rb) {
        if (ra == rb) {
            if (st[i] > 0)
                return ra;
            else return -1;
        }

        if (st[i * 2 + 1] > 0) {
            return first(i * 2 + 1, ra, (ra + rb) / 2);
        } else {
            return first(i * 2 + 2, (ra + rb) / 2 + 1, rb);
        }
    }

    int last(int i, int ra, int rb) {
        if (ra == rb) {
            if (st[i] > 0)
                return ra;
            else return -1;
        }

        if (st[i * 2 + 2] > 0) {
            return last(i * 2 + 2, (ra + rb) / 2 + 1, rb);
        } else {
            return last(i * 2 + 1, ra, (ra + rb) / 2);
        }
    }
}