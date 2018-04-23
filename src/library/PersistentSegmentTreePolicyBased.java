package library;

public class PersistentSegmentTreePolicyBased {
    int[] a;
    int[] h;
    int n;
    int mv;

    node[] roots;

    public PersistentSegmentTreePolicyBased(int[] a, int mv) {
        super();
        this.n = a.length;
        this.mv = mv;
        this.a = a;
        this.roots = new node[n + 1];
        this.h = new int[mv + 1];

        for (int i = 0; i <= n; i++) {
            roots[i] = new node(0, mv);
        }
    }

    node build(int rs, int re, node curNode) {
        if (rs == re) {
            curNode.cnt = h[rs];

            return curNode;
        } else {
            int mid = (rs + re) / 2;

            curNode.left = new node(rs, mid);
            build(rs, mid, curNode.left);
            curNode.right = new node(mid + 1, re);
            build(mid + 1, re, curNode.right);

            curNode.cnt = curNode.left.cnt + curNode.right.cnt;

            return curNode;
        }
    }

    void upgrade(int rs, int re, node prevNode, node curNode, int ind) {
        if (ind < rs || ind > re || rs > re) {
            return;
        }

        if (rs < re) {
            int mid = (rs + re) / 2;

            if (ind <= mid) {
                curNode.left = new node(rs, mid);
                curNode.right = prevNode.right;

                upgrade(rs, mid, prevNode.left, curNode.left, ind);
            } else {
                curNode.right = new node(mid + 1, re);
                curNode.left = prevNode.left;

                upgrade(mid + 1, re, prevNode.right, curNode.right, ind);
            }

            curNode.cnt = curNode.left.cnt + curNode.right.cnt;
        } else {
            curNode.cnt = h[ind];
        }
    }

    int query(node curNode1, node curNode2, int rs, int re, int gtt) {
        if (re <= gtt) {
            return 0;
        }

        if (rs > gtt) {
            return curNode2.cnt - curNode1.cnt;
        }

        int m = (rs + re) / 2;

        int p1 = query(curNode1.left, curNode2.left, rs, m, gtt);
        int p2 = query(curNode1.right, curNode2.right, m + 1, re, gtt);

        return p1 + p2;
    }

    void build() {
        build(0, mv, roots[0]);

        for (int i = 0; i < n; i++) {
            h[a[i]]++;
            upgrade(0, mv, roots[i], roots[i + 1], a[i]);
        }
    }

    class node {
        int rs;
        int re;
        int cnt;

        node left;
        node right;

        public node(int rs, int re) {
            super();
            this.rs = rs;
            this.re = re;
        }

    }

}
