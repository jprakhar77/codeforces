package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _954E {
    class tap {
        long x;
        long t;

        public tap(long x, long t) {
            this.x = x;
            this.t = t;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long tt = in.nextInt();

        long[] a = new long[n];

        long xsum = 0;
        long xtsum = 0;

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        long[] t = new long[n];

        List<tap> taps = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            t[i] = in.nextInt();
        }

        long ex = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (t[i] == tt) {
                ex += a[i];
                continue;
            }
            taps.add(new tap(a[i], t[i]));
            j++;
            xsum += a[i];
            xtsum += t[i] * a[i];
        }

        if (j == 0) {
            out.println(ex);
            return;
        }

        n = j;

        if (((double) xtsum / xsum) < tt) {
            taps.sort((t1, t2) -> (int) Math.signum(t1.t - t2.t));

            long cxtsum = xtsum;
            long cxsum = xsum;

            for (int i = 0; i < n; i++) {
                long nxtsum = cxtsum - taps.get(i).t * taps.get(i).x;
                long nxsum = cxsum - taps.get(i).x;

                if (nxsum != 0 && ((double) nxtsum / nxsum) == tt) {
                    out.println(nxsum + ex);
                    return;
                }

                double x = ((double) nxtsum - tt * nxsum) / (tt - taps.get(i).t);

                if (x >= 0) {
                    out.println(nxsum + x + ex);
                    return;
                }

                cxtsum = nxtsum;
                cxsum = nxsum;
            }
        }

        if (((double) xtsum / xsum) == tt) {
            out.println(xsum + ex);
            return;
        }


        taps.sort((t1, t2) -> (int) Math.signum(t2.t - t1.t));

        long cxtsum = xtsum;
        long cxsum = xsum;

        for (int i = 0; i < n; i++) {
            long nxtsum = cxtsum - taps.get(i).t * taps.get(i).x;
            long nxsum = cxsum - taps.get(i).x;

            if (nxsum != 0 && ((double) nxtsum / nxsum) == tt) {
                out.println(nxsum + ex);
                return;
            }

            double x = ((double) nxtsum - tt * nxsum) / (tt - taps.get(i).t);

            if (x >= 0) {
                out.println(nxsum + x + ex);
                return;
            }

            cxtsum = nxtsum;
            cxsum = nxsum;
        }

        out.println(ex);

    }
}
