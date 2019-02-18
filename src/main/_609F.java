package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class _609F {
    public static class IntervalTreeNonOverlap {

        int s;
        int e;

        public IntervalTreeNonOverlap(int s, int e) {
            this.s = s;
            this.e = e;
            root = new Node((s + e) / 2);
        }

        static class Interval {
            int s;
            int e;
            int i;
            Interval next;

            public Interval(int i, int s, int e) {
                this.i = i;
                this.s = s;
                this.e = e;
            }
        }

        static class Node {
            int c;
            Interval interval;
            Node left;
            Node right;

            public Node(int c) {
                this.c = c;
            }
        }

        Node root;

        void addInterval(Interval interval) {
            Node ci = root;

            int cs = s;
            int ce = e;

            while (true) {
                if (ci.c >= interval.s && ci.c <= interval.e) {
                    ci.interval = interval;
                    break;
                } else if (ci.c > interval.e) {
                    if (ci.left == null) {
                        ci.left = new Node((cs + ci.c - 1) / 2);
                    }
                    ce = ci.c - 1;
                    ci = ci.left;
                } else {
                    if (ci.right == null) {
                        ci.right = new Node((ci.c + 1 + ce) / 2);
                    }
                    cs = ci.c + 1;
                    ci = ci.right;
                }
            }
        }

        Interval searchInterval(int x) {
            Node ci = root;

            while (true) {
                if (ci.interval != null && ci.interval.s <= x && ci.interval.e >= x) {
                    return ci.interval;
                } else if (ci.interval == null && x == ci.c) {
                    return null;
                } else if (x < ci.c) {
                    if (ci.left == null)
                        return null;
                    ci = ci.left;
                } else if (x > ci.c) {
                    if (ci.right == null)
                        return null;
                    ci = ci.right;
                }
            }
        }

        void deleteInterval(Interval interval) {
            Node ci = root;

            while (true) {
                if (ci.c >= interval.s && ci.c <= interval.e) {
                    ci.interval = null;
                    break;
                } else if (ci.c > interval.e) {
                    ci = ci.left;
                } else {
                    ci = ci.right;
                }
            }
        }
    }

    class frog {
        int x;
        int t;
        int i;

        public frog(int x, int t, int i) {
            this.x = x;
            this.t = t;
            this.i = i;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int lie = -1;

        IntervalTreeNonOverlap it = new IntervalTreeNonOverlap(0, (int) 1e9);

        IntervalTreeNonOverlap.Interval head = null;
        IntervalTreeNonOverlap.Interval prev = null;
        int[] count = new int[n];
        long[] size = new long[n];

        frog[] frogs = new frog[n];

        for (int i = 0; i < n; i++) {
            frogs[i] = new frog(in.nextInt(), in.nextInt(), i);
        }

        Arrays.sort(frogs, (f1, f2) -> f1.x - f2.x);

        for (int i = 0; i < n; i++) {
            frog cf = frogs[i];
            int x = cf.x;
            int t = cf.t;
            int ci = cf.i;

            size[ci] += t;
            if (x + t <= lie)
                continue;
            IntervalTreeNonOverlap.Interval cint =
                    new IntervalTreeNonOverlap.Interval(ci, Math.max(lie + 1, x), x + t);

            if (head == null) {
                head = cint;
                prev = cint;
            } else {
                prev.next = cint;
                prev = cint;
            }

            lie = x + t;
        }

        IntervalTreeNonOverlap.Interval cint = head;

        while (cint != null) {
            it.addInterval(cint);
            cint = cint.next;
        }

        TreeMap<Integer, List<Integer>> mm = new TreeMap<>();

        for (int i = 0; i < m; i++) {
            int p = in.nextInt();
            int b = in.nextInt();

            IntervalTreeNonOverlap.Interval sint = it.searchInterval(p);

            if (sint == null) {
                if (!mm.containsKey(p)) {
                    mm.put(p, new ArrayList<>());
                }
                mm.get(p).add(b);
            } else {
                int bl = sint.e;

                count[sint.i]++;
                size[sint.i] += b;

                Integer nm = mm.ceilingKey(bl + 1);

                long nbl = (long) bl + b;
                while (nm != null && nm <= nbl) {
                    List<Integer> l = mm.get(nm);
                    for (int j = 0; j < l.size(); j++) {
                        count[sint.i]++;
                        size[sint.i] += l.get(j);
                        nbl += l.get(j);
                    }
                    mm.remove(nm);
                    nm = mm.ceilingKey(bl + 1);
                }

                nbl = Math.min(nbl, (int) 1e9);

                IntervalTreeNonOverlap.Interval pn = sint;
                for (IntervalTreeNonOverlap.Interval cn = sint.next; cn != null; cn = cn.next) {
                    if (cn.e <= nbl) {
                        pn.next = cn.next;
                        it.deleteInterval(cn);
                    } else if (cn.s <= nbl) {
                        it.deleteInterval(cn);
                        IntervalTreeNonOverlap.Interval nint = new IntervalTreeNonOverlap.Interval(cn.i, (int) nbl + 1, cn.e);
                        pn.next = nint;
                        nint.next = cn.next;
                        it.addInterval(nint);
                        pn = nint;
                    } else if (cn.s > nbl)
                        break;
                }

                it.deleteInterval(sint);
                sint.e = (int) nbl;
                it.addInterval(sint);
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            ans.append(count[i]);
            ans.append(" ");
            ans.append(size[i]);
            ans.append("\n");
        }

        out.println(ans.toString());
    }
}
