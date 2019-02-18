package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class D2GreatVovaWallVersion2 {
    class pair {
        int l;
        int r;

        public pair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    class seg {
        int l;
        int r;
        int val;
        int i;

        public seg(int l, int r, int val, int i) {
            this.l = l;
            this.r = r;
            this.val = val;
            this.i = i;

        }

        public seg(int l) {
            this.l = l;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);


        PriorityQueue<seg> segs = new PriorityQueue<>((s1, s2) -> {
            if (s1.val == s2.val) {
                return ((s1.r - s1.l + 1) % 2) - ((s2.r - s2.l + 1) % 2);
            } else
                return s1.val - s2.val;
        });

        int si = 0;
        int css = 0;
        for (int i = 1; i < n; i++) {
            if (a[i] != a[i - 1]) {
                segs.add(new seg(css, i - 1, a[i - 1], si));
                si++;
                css = i;
            }
        }

        segs.add(new seg(css, n - 1, a[n - 1], si));
        si++;

        TreeSet<seg> ts = new TreeSet<>((s1, s2) -> s1.l - s2.l);

        ts.addAll(segs);

        HashSet<Integer> vs = new HashSet<>();

        for (int i = 0; i < si; i++) {
            vs.add(i);
        }

        while (ts.size() > 1) {
            seg cs = segs.poll();

            if (!vs.contains(cs.i))
                continue;

            vs.remove(cs.i);
            ts.remove(cs);

            if (sz(cs) % 2 == 1) {
                out.println("NO");
                return;
            }

            seg ls = ts.floor(new seg(cs.l));
            seg rs = ts.ceiling(new seg(cs.r));

            if (ls != null && rs != null && ls.val == rs.val) {
                vs.remove(ls.i);
                vs.remove(rs.i);
                ts.remove(ls);
                ts.remove(rs);
                seg nseg = new seg(ls.l, rs.r, ls.val, si);
                segs.add(nseg);
                ts.add(nseg);
                vs.add(si);
                si++;
            }
        }

        out.println("YES");
    }

    int sz(seg seg) {
        return seg.r - seg.l + 1;
    }
}
