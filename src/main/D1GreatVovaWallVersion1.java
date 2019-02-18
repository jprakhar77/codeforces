package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.TreeSet;

public class D1GreatVovaWallVersion1 {
    class pair {
        int l;
        int r;

        public pair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        ArrayDeque<pair> pairs = new ArrayDeque<>();

        for (int i = 0; i < n - 1; i++) {
            if (a[i] % 2 == a[i + 1] % 2) {
                pairs.add(new pair(i, i + 1));
            }
        }

        TreeSet<Integer> ts = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            ts.add(i);
        }

        while (!pairs.isEmpty()) {
            pair cp = pairs.removeFirst();

            if (a[cp.l] % 2 == a[cp.r] % 2 && ts.contains(cp.l) && ts.contains(cp.r)) {
                ts.remove(cp.l);
                ts.remove(cp.r);

                Integer li = ts.floor(cp.l - 1);
                Integer ri = ts.ceiling(cp.r + 1);

                if (li != null && ri != null) {
                    if (a[li] % 2 == a[ri] % 2) {
                        pairs.addLast(new pair(li, ri));
                    }
                }
            }
        }

        if (ts.size() <= 1) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
