package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.BitSet;

public class BinaryStrings {
    class trie {
        int depth;

        node root = new node();

        boolean iscal = false;

        public trie(int depth) {
            this.depth = depth;
            this.roots = new node[depth + 1];
        }

        class node {
            node zero;
            node one;
        }

        char nil = '\0';

        void insert(String bs) {
            int bsl = bs.length();

            node cnode = root;

            for (int i = 0; i < depth; i++) {
                int remdep = depth - i;

                int cc = -1;
                if (remdep <= bsl) {
                    cc = bs.charAt(bsl - remdep) - '0';
                } else {
                    cc = 0;
                }

                if (cc == 0) {
                    if (cnode.zero == null) {
                        cnode.zero = new node();
                    }
                    cnode = cnode.zero;
                } else {
                    if (cnode.one == null) {
                        cnode.one = new node();
                    }
                    cnode = cnode.one;
                }
            }
        }

        String search(String x) {

            if (!iscal) {
                calroots();
                iscal = true;
            }

            int xs = x.length();

            StringBuilder ans = new StringBuilder(xs);

            node cnode = roots[depth - x.length()];
            for (int i = depth - x.length(); i < depth; i++) {
                int remdep = depth - i;

                int cc = -1;
                if (remdep <= xs) {
                    cc = x.charAt(xs - remdep) - '0';
                } else {
                    cc = 0;
                }

                if (cnode == null) {
                    if (cc == 0)
                        ans.append('0');
                    else
                        ans.append('1');
                    continue;
                }

                if (cc == 0) {
                    if (cnode.one != null) {
                        cnode = cnode.one;
                        ans.append('1');
                    } else {
                        cnode = cnode.zero;
                        ans.append('0');
                    }
                } else {
                    if (cnode.zero != null) {
                        cnode = cnode.zero;
                        ans.append('1');
                    } else {
                        cnode = cnode.one;
                        ans.append('0');
                    }
                }
            }

            return ans.toString();
        }

        node[] roots;

        void calroots() {
            node cnode = root;

            roots[0] = root;

            for (int i = 0; i < depth; i++) {
                if (cnode == null) {
                    roots[i + 1] = null;
                    break;
                }

                if (cnode.one != null) {
                    cnode = cnode.one;
                    roots[i + 1] = cnode;
                } else {
                    cnode = cnode.zero;
                    roots[i + 1] = cnode;
                }
            }
        }
    }

    class SegmentTree {
        trie[] st;
        int n;

        public SegmentTree(int n) {
            st = new trie[4 * n];
            this.n = n;
        }

        void build(int i, String[] a, int r1, int r2, int depth) {
            if (r1 == r2) {
                st[i] = new trie(depth);
                st[i].insert(a[r1]);
            } else {
                build(i * 2 + 1, a, r1, (r1 + r2) / 2, depth);
                build(i * 2 + 2, a, (r1 + r2) / 2 + 1, r2, depth);

//                st[i] = new trie(depth);
//                for (int j = r1; j <= r2; j++) {
//                    st[i].insert(a[j]);
//                }

                st[i] = mergeTries(st[i * 2 + 1], st[i * 2 + 2], depth);
            }
        }

        trie mergeTries(trie t1, trie t2, int depth) {
            trie merge = new trie(depth);

            merge.root = dfs(t1.root, t2.root, merge);

            return merge;
        }

        trie.node dfs(trie.node t1, trie.node t2, trie m) {
            if (t1 != null && t2 != null) {
                trie.node nn = m.new node();
                nn.zero = dfs(t1.zero, t2.zero, m);
                nn.one = dfs(t1.one, t2.one, m);

                return nn;
            } else if (t1 != null) {
                trie.node nn = m.new node();
                nn.zero = dfs(t1.zero, null, m);
                nn.one = dfs(t1.one, null, m);

                return nn;
            } else if (t2 != null) {
                trie.node nn = m.new node();
                nn.zero = dfs(null, t2.zero, m);
                nn.one = dfs(null, t2.one, m);

                return nn;
            } else return null;
        }

        String max = "";
        int ind = -1;
        int rs;
        int re;

        void queryInit() {
            max = "";
            ind = -1;
            rs = -1;
            re = -1;
        }

        void query(int i, int ra, int rb, int r1, int r2, String x) {
            if (ra > r2 || rb < r1) {
                return;
            }

            if (ra >= r1 && rb <= r2) {
                String cmax = st[i].search(x);
                int cmp = max.compareTo(cmax);

                if (cmp < 0) {
                    max = cmax;
                    ind = i;
                    rs = ra;
                    re = rb;
                } else if (cmp == 0) {
                    if (ra < rs) {
                        ind = i;
                        rs = ra;
                        re = rb;
                    }
                }

                return;
            }

            query(i * 2 + 1, ra, (ra + rb) / 2, r1, r2, x);
            query(i * 2 + 2, (ra + rb) / 2 + 1, rb, r1, r2, x);
        }

        int bestInd = n;

        String descent(int i, int ra, int rb, String x) {
            if (ra == rb) {
                bestInd = ra;
                return max;
            } else {
                if (st[i * 2 + 1].search(x).equals(max)) {
                    return descent(i * 2 + 1, ra, (ra + rb) / 2, x);
                } else {
                    return descent(i * 2 + 2, (ra + rb) / 2 + 1, rb, x);
                }
            }
        }
    }

    class trieb {
        int depth;

        node root = new node();

        public trieb(int depth) {
            this.depth = depth;
        }

        class node {
            node zero;
            node one;
        }

        char nil = '\0';

        void insert(BitSet bs) {
            node cnode = root;

            for (int i = 0; i < depth; i++) {
                boolean cc = bs.get(i);

                if (!cc) {
                    if (cnode.zero == null) {
                        cnode.zero = new node();
                    }
                    cnode = cnode.zero;
                } else {
                    if (cnode.one == null) {
                        cnode.one = new node();
                    }
                    cnode = cnode.one;
                }
            }
        }

        BitSet search(BitSet x) {
            BitSet ans = (BitSet) x.clone();

            node cnode = root;
            for (int i = 0; i < depth; i++) {
                boolean cc = x.get(i);

                if (cnode == null) {
                    return ans;
                }

                if (!cc) {
                    if (cnode.one != null) {
                        cnode = cnode.one;
                        ans.set(i);
                    } else {
                        cnode = cnode.zero;
                    }
                } else {
                    if (cnode.zero != null) {
                        cnode = cnode.zero;
                    } else {
                        cnode = cnode.one;
                        ans.set(i, false);
                    }
                }
            }

            return ans;
        }
    }

    class SegmentTreeb {
        trieb[] st;
        int n;

        public SegmentTreeb(int n) {
            st = new trieb[4 * n];
            this.n = n;
        }

        void build(int i, BitSet[] a, int r1, int r2, int depth) {
            if (r1 == r2) {
                st[i] = new trieb(depth);
                st[i].insert(a[r1]);
            } else {
                build(i * 2 + 1, a, r1, (r1 + r2) / 2, depth);
                build(i * 2 + 2, a, (r1 + r2) / 2 + 1, r2, depth);

//                st[i] = new trie(depth);
//                for (int j = r1; j <= r2; j++) {
//                    st[i].insert(a[j]);
//                }

                st[i] = mergeTries(st[i * 2 + 1], st[i * 2 + 2], depth);
            }
        }

        trieb mergeTries(trieb t1, trieb t2, int depth) {
            trieb merge = new trieb(depth);

            merge.root = dfs(t1.root, t2.root, merge);

            return merge;
        }

        trieb.node dfs(trieb.node t1, trieb.node t2, trieb m) {
            if (t1 != null && t2 != null) {
                trieb.node nn = m.new node();
                nn.zero = dfs(t1.zero, t2.zero, m);
                nn.one = dfs(t1.one, t2.one, m);

                return nn;
            } else if (t1 != null) {
                trieb.node nn = m.new node();
                nn.zero = dfs(t1.zero, null, m);
                nn.one = dfs(t1.one, null, m);

                return nn;
            } else if (t2 != null) {
                trieb.node nn = m.new node();
                nn.zero = dfs(null, t2.zero, m);
                nn.one = dfs(null, t2.one, m);

                return nn;
            } else return null;
        }

        BitSet max = null;
        int ind = -1;
        int rs;
        int re;

        void queryInit() {
            max = null;
            ind = -1;
            rs = -1;
            re = -1;
        }


        int compare(BitSet b1, BitSet b2) {

            if (b1 == null)
                return -1;

            if (b1.equals(b2))
                return 0;

            BitSet xor = (BitSet) b1.clone();
            xor.xor(b2);

            int firstDiffer = xor.length() - 1;

            if (firstDiffer == -1)
                return 0;

            if (b1.get(firstDiffer)) {
                return 1;
            } else return -1;
        }

        void query(int i, int ra, int rb, int r1, int r2, BitSet x) {
            if (ra > r2 || rb < r1) {
                return;
            }

            if (ra >= r1 && rb <= r2) {
                BitSet cmax = st[i].search(x);
                int cmp = compare(max, cmax);

                if (cmp < 0) {
                    max = cmax;
                    ind = i;
                    rs = ra;
                    re = rb;
                } else if (cmp == 0) {
                    if (ra < rs) {
                        ind = i;
                        rs = ra;
                        re = rb;
                    }
                }

                return;
            }

            query(i * 2 + 1, ra, (ra + rb) / 2, r1, r2, x);
            query(i * 2 + 2, (ra + rb) / 2 + 1, rb, r1, r2, x);
        }

        int bestInd = n;

        BitSet descent(int i, int ra, int rb, BitSet x) {
            if (ra == rb) {
                bestInd = ra;
                return max;
            } else {
                if (st[i * 2 + 1].search(x).equals(max)) {
                    return descent(i * 2 + 1, ra, (ra + rb) / 2, x);
                } else {
                    return descent(i * 2 + 2, (ra + rb) / 2 + 1, rb, x);
                }
            }
        }
    }

    class triei {
        int depth;

        node root = new node();

        public triei(int depth) {
            this.depth = depth;
        }

        class node {
            node zero;
            node one;
        }

        char nil = '\0';

        void insert(String bs) {
            int bsl = bs.length();

            node cnode = root;

            for (int i = 0; i < depth; i++) {
                int remdep = depth - i;

                int cc = -1;
                if (remdep <= bsl) {
                    cc = bs.charAt(bsl - remdep) - '0';
                } else {
                    cc = 0;
                }

                if (cc == 0) {
                    if (cnode.zero == null) {
                        cnode.zero = new node();
                    }
                    cnode = cnode.zero;
                } else {
                    if (cnode.one == null) {
                        cnode.one = new node();
                    }
                    cnode = cnode.one;
                }
            }
        }

        int search(String x) {
            int xs = x.length();

            int ans = 0;

            for (int i = 0; i < xs; i++) {
                if (x.charAt(i) == '1')
                    ans |= (1 << (xs - i - 1));
            }
            if (xs > depth) {
                //ans.append(x.substring(0, xs - depth));
            }

            node cnode = root;
            for (int i = 0; i < depth; i++) {
                int remdep = depth - i;

                int cc = -1;
                if (remdep <= xs) {
                    cc = x.charAt(xs - remdep) - '0';
                } else {
                    cc = 0;
                }

                if (cnode == null) {
                    continue;
                }

                if (cc == 0) {
                    if (cnode.one != null) {
                        cnode = cnode.one;
                        ans |= (1 << (depth - i - 1));
                    } else {
                        cnode = cnode.zero;
                    }
                } else {
                    if (cnode.zero != null) {
                        cnode = cnode.zero;
                    } else {
                        cnode = cnode.one;
                        ans &= ~(1 << (depth - i - 1));
                    }
                }
            }

            return ans;
        }
    }

    class SegmentTreei {
        triei[] st;
        int n;

        public SegmentTreei(int n) {
            st = new triei[4 * n];
            this.n = n;
        }

        void build(int i, String[] a, int r1, int r2, int depth) {
            if (r1 == r2) {
                st[i] = new triei(depth);
                st[i].insert(a[r1]);
            } else {
                build(i * 2 + 1, a, r1, (r1 + r2) / 2, depth);
                build(i * 2 + 2, a, (r1 + r2) / 2 + 1, r2, depth);

                st[i] = new triei(depth);
                for (int j = r1; j <= r2; j++) {
                    st[i].insert(a[j]);
                }
            }
        }

        int max = 0;
        int ind = -1;
        int rs;
        int re;

        void queryInit() {
            max = 0;
            ind = -1;
            rs = -1;
            re = -1;
        }

        void query(int i, int ra, int rb, int r1, int r2, String x) {
            if (ra > r2 || rb < r1) {
                return;
            }

            if (ra >= r1 && rb <= r2) {
                int cmax = st[i].search(x);

                if (cmax > max) {
                    max = cmax;
                    ind = i;
                    rs = ra;
                    re = rb;
                } else if (cmax == max) {
                    if (ra < rs) {
                        ind = i;
                        rs = ra;
                        re = rb;
                    }
                }

                return;
            }

            query(i * 2 + 1, ra, (ra + rb) / 2, r1, r2, x);
            query(i * 2 + 2, (ra + rb) / 2 + 1, rb, r1, r2, x);
        }

        int bestInd = n;

        int descent(int i, int ra, int rb, String x) {
            if (ra == rb) {
                bestInd = ra;
                return max;
            } else {
                if (st[i * 2 + 1].search(x) == max) {
                    return descent(i * 2 + 1, ra, (ra + rb) / 2, x);
                } else {
                    return descent(i * 2 + 2, (ra + rb) / 2 + 1, rb, x);
                }
            }
        }

    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();
        int q = in.nextInt();

        //BitSet[] a = new BitSet[n];
        String[] a = new String[n];
        int size = -1;

        for (int i = 0; i < n; i++) {
//            String s = in.next();
//            if (i == 0) {
//                size = s.length();
//            }
//            a[i] = new BitSet();
//            for (int j = 0; j < s.length(); j++) {
//                if (s.charAt(j) == '1') {
//                    a[i].set(j);
//                }
//            }

            a[i] = in.next();
        }

        size = a[0].length();

        SegmentTree st = new SegmentTree(n);
        st.build(0, a, 0, n - 1, size);

        for (int i = 0; i < q; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;

            String x = in.next();

//            BitSet xb = new BitSet();
//
//            if (x.length() > size) {
//                for (int j = x.length() - size; j < x.length(); j++) {
//                    if (x.charAt(j) == '1')
//                        xb.set(j - (x.length() - size));
//                }
//            } else {
//                for (int j = 0; j < x.length(); j++) {
//                    if (x.charAt(j) == '1')
//                        xb.set((size - x.length()) + j);
//                }
//            }

            if (x.length() > size) {
                x = x.substring(x.length() - size);
            }

            st.queryInit();
            st.query(0, 0, n - 1, l, r, x);

            st.descent(st.ind, st.rs, st.re, x);

            out.println(st.bestInd + 1);
        }
    }
}
