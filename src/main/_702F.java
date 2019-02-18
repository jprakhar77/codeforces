package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class _702F {
    class ts {
        int c;
        int q;

        public ts(int c, int q) {
            this.c = c;
            this.q = q;
        }
    }

    class c {
        long b;
        int i;

        public c(long b, int i) {
            this.b = b;
            this.i = i;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        ts[] ts = new ts[n];

        TreeMap<Integer, List<ts>> qts = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            ts[i] = new ts(in.nextInt(), in.nextInt());

            if (!qts.containsKey(ts[i].q)) {
                qts.put(ts[i].q, new ArrayList<>());
            }

            qts.get(ts[i].q).add(ts[i]);
        }

        for (int q : qts.keySet()) {
            qts.get(q).sort((ts1, ts2) -> ts1.c - ts2.c);
        }

        int k = in.nextInt();

        TreeSet<c> cts = new TreeSet<>((c1, c2) ->
        {
            if (c1.b == c2.b)
                return c1.i - c2.i;
            else
                return (int) Math.signum(c1.b - c2.b);
        });

        for (int i = 0; i < k; i++) {
            c c = new c(in.nextInt(), i);
            cts.add(c);
        }

        long cc = 0;
        int ct = 0;

        int[] ans = new int[k];

        for (int q : qts.descendingKeySet()) {
            List<ts> tsl = qts.get(q);

            long ncc = cc;
            for (int i = 0; i < tsl.size(); i++) {
                ncc += tsl.get(i).c;

                while (cts.size() > 0) {
                    c ce = cts.ceiling(new c(cc, -1));
                    if (ce.b < ncc) {
                        ans[ce.i] = ct;
                        cts.remove(ce);
                    } else {
                        break;
                    }
                }

                cc = ncc;
                ct++;
            }
        }

        for (c c : cts) {
            ans[c.i] = n;
        }

        for (int i = 0; i < k; i++) {
            out.print(ans[i] + " ");
        }
    }
}
