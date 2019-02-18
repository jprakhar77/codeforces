package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class MatchTheStreams {
    class ImplicitPersistentSegmentTreePolicyBased {
        int[] a;
        Map<Integer, Integer> h;
        int n;
        int mv;

        node[] roots;

        public ImplicitPersistentSegmentTreePolicyBased(int[] a, int mv) {
            super();
            this.n = a.length;
            this.mv = mv;
            this.a = a;
            this.roots = new node[n + 1];
            this.h = new HashMap<>();

            for (int i = 0; i <= n; i++) {
                roots[i] = new node(0, mv);
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
            //build(0, mv, roots[0]);

            for (int i = 0; i < n; i++) {
                //h[a[i]]++;
                h.merge(a[i], 1, (x, y) -> x + y);
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

//        public static void main(String[] args) {
//            int n = 10;
//            int[] a = new int[n];
//            for (int i = 0; i < n; i++) {
//                a[i] = 100000000;
//            }
//
//            ImplicitPersistentSegmentTreePolicyBased pst = new ImplicitPersistentSegmentTreePolicyBased(a, 1000000000);
//
//            pst.build();
//
//            int ans = pst.query(pst.roots[0], pst.roots[1], 0, pst.mv, 100000000);
//
//            System.out.println(ans);
//        }

    }

    class LazySegmentTree {
        int[] tree;
        int n;
        int[] a;
        int[] lazyadd;

        ImplicitPersistentSegmentTreePolicyBased pst;

        public LazySegmentTree(int[] a, int n) {
            super();
            this.a = a;
            this.n = n;
            this.tree = new int[4 * n];
            this.lazyadd = new int[4 * n];
        }

        public int build(int i, int rs, int re) {
            if (rs == re) {
                tree[i] = a[rs];
                return tree[i];
            } else {
                int mid = (rs + re) / 2;

                int left = build(i * 2 + 1, rs, mid);
                int right = build(i * 2 + 2, mid + 1, re);

                tree[i] = (left + right);

                return tree[i];
            }
        }

        public int query(int i, int rs, int re, int qs, int qe) {
            lazyAdjustment(i, rs, re);

            if (re < qs || qe < rs) {
                return 0;
            }

            if (qs <= rs && qe >= re) {
                return tree[i];
            }

            int mid = (rs + re) / 2;

            int p1 = query(i * 2 + 1, rs, mid, qs, qe);
            int p2 = query(i * 2 + 2, mid + 1, re, qs, qe);

            return p1 + p2;
        }

        private void lazyAdjustment(int i, int rs, int re) {
            if (lazyadd[i] == 0)
                return;

            tree[i] = pst.query(pst.roots[rs], pst.roots[re + 1], 0, pst.mv, lazyadd[i]);

            if (rs < re) {
                lazyadd[i * 2 + 1] = lazyadd[i];

                lazyadd[i * 2 + 2] = lazyadd[i];
            }

            lazyadd[i] = 0;
        }

        public int update(int i, int rs, int re, int us, int ue, int add) {
            if (re < us || ue < rs) {
                lazyAdjustment(i, rs, re);
                return tree[i];
            }

            if (us <= rs && ue >= re) {
                lazyadd[i] = add;
                lazyAdjustment(i, rs, re);
                return tree[i];
            }

            lazyAdjustment(i, rs, re);

            int mid = (rs + re) / 2;

            int p1 = update(i * 2 + 1, rs, mid, us, ue, add);
            int p2 = update(i * 2 + 2, mid + 1, re, us, ue, add);

            tree[i] = (p1 + p2);

            return (p1 + p2);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        int q = in.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int[] b = new int[n];

        int[] s = new int[n];

        int sim = 0;
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();

            if (a[i] == b[i]) {
                sim++;
                s[i] = 1;
            }


        }


        LazySegmentTree lst = new LazySegmentTree(s, n);
        lst.build(0, 0, n - 1);

        ImplicitPersistentSegmentTreePolicyBased pst = new ImplicitPersistentSegmentTreePolicyBased(b, 1000000000);
        pst.build();
        lst.pst = pst;

        StringBuilder ans = new StringBuilder();

        while (q-- > 0) {
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.nextInt();

            int l = x ^ sim;
            int r = y ^ sim;
            int c = z ^ sim;

            lst.update(0, 0, n - 1, l - 1, r - 1, c);

            sim = lst.query(0, 0, n - 1, 0, n - 1);

            ans.append(sim);
            ans.append("\n");
        }

        out.println(ans.toString());

    }
}
