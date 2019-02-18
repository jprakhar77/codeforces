package library;

import java.util.HashMap;
import java.util.Map;

//Useful for finding how many times a particular value
//appear in a range from l to r in an array.
//Since, it's implicit, numbers in the array can be from
//some large range, like 10^9. The height of the
//segment tree will be log of the range.
public class ImplicitPersistentSegmentTreePolicyBased {
    int[] a;
    Map<Integer, Integer> h;
    int n;
    int maxValue;

    node[] roots;

    public ImplicitPersistentSegmentTreePolicyBased(int[] a, int maxValue) {
        super();
        this.n = a.length;
        this.maxValue = maxValue;
        this.a = a;
        this.roots = new node[n + 1];
        this.h = new HashMap<>();

        for (int i = 0; i <= n; i++) {
            roots[i] = new node(0, maxValue);
        }
    }

//    node build(int rs, int re, node curNode) {
//        if (rs == re) {
//            curNode.cnt = h[rs];
//
//            return curNode;
//        } else {
//            int mid = (rs + re) / 2;
//
//            curNode.left = new node(rs, mid);
//            build(rs, mid, curNode.left);
//            curNode.right = new node(mid + 1, re);
//            build(mid + 1, re, curNode.right);
//
//            curNode.cnt = curNode.left.cnt + curNode.right.cnt;
//
//            return curNode;
//        }
//    }

    void upgrade(int rs, int re, node prevNode, node curNode, int ind) {
        if (ind < rs || ind > re || rs > re) {
            return;
        }

        if (rs < re) {
            int mid = (rs + re) / 2;

            if (ind <= mid) {
                curNode.left = new node(rs, mid);
                if (prevNode != null)
                    curNode.right = prevNode.right;

                upgrade(rs, mid, prevNode == null ? null : prevNode.left, curNode.left, ind);
            } else {
                curNode.right = new node(mid + 1, re);
                if (prevNode != null)
                    curNode.left = prevNode.left;

                upgrade(mid + 1, re, prevNode == null ? null : prevNode.right, curNode.right, ind);
            }

            curNode.cnt = (curNode.left == null ? 0 : curNode.left.cnt) +
                    (curNode.right == null ? 0 : curNode.right.cnt);
        } else
            curNode.cnt = h.get(ind);
    }

    int query(node curNode1, node curNode2, int rs, int re, int ett) {
        if (re < ett || rs > ett) {
            return 0;
        }

        if (rs == re) {
            int cnt2 = curNode2 != null ? curNode2.cnt : 0;
            int cnt1 = curNode1 != null ? curNode1.cnt : 0;

            return cnt2 - cnt1;
        }

        int m = (rs + re) / 2;

        int p1 = query(curNode1 == null ? null : curNode1.left,
                curNode2 == null ? null : curNode2.left, rs, m, ett);
        int p2 = query(curNode1 == null ? null : curNode1.right,
                curNode2 == null ? null : curNode2.right, m + 1, re, ett);

        return p1 + p2;
    }

    void build() {
        //build(0, maxValue, roots[0]);

        for (int i = 0; i < n; i++) {
            //h[a[i]]++;
            h.merge(a[i], 1, (x, y) -> x + y);
            upgrade(0, maxValue, roots[i], roots[i + 1], a[i]);
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

    public static void main(String[] args) {
        int n = 10;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = 100000000;
        }

        ImplicitPersistentSegmentTreePolicyBased pst = new ImplicitPersistentSegmentTreePolicyBased(a, 1000000000);

        pst.build();

        int ans = pst.query(pst.roots[0], pst.roots[1], 0, pst.maxValue, 100000000);

        System.out.println(ans);
    }

}
