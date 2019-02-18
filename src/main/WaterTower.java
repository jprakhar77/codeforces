package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.PriorityQueue;

public class WaterTower {
    class leak {
        int i;
        int h;
        int t;

        public leak(int i, int h, int t) {
            this.i = i;
            this.h = h;
            this.t = t;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int h = in.nextInt();
        int q = in.nextInt();


        //leak[] leakhs = new leak[q];
        leak[] leakts = new leak[q];

        boolean isp = false;

        PriorityQueue<leak> leakhs = new PriorityQueue<>((l1, l2) ->
        {
            if (l1.h == l2.h) {
                return l1.t - l2.t;
            } else {
                return l2.h - l1.h;
            }
        });

        for (int i = 0; i < q; i++) {
            int ct = in.nextInt();
            int ch = in.nextInt();

            if (ch == 0) {
                isp = true;
            }

            //leakhs.add(new leak(i, ct, ch));
            leakts[i] = new leak(i, ch, ct);
        }

        if (!isp) {
            out.println(-1);
            return;
        }

        Arrays.sort(leakts, (l1, l2) ->
        {
            if (l1.t == l2.t) {
                return l2.h - l1.h;
            } else {
                return l1.t - l2.t;
            }
        });

        double ch = h;
        double ct = 0;

        double cr = 0;

        int cti = 0;

        for (cti = 0; cti < q && ch > eps; cti++) {
            while (leakhs.size() > 0 && (leakhs.peek().h > ch || issame(leakhs.peek().h, ch))) {
                leakhs.poll();
                cr--;
            }

            leak maxleak = leakhs.size() > 0 ? leakhs.peek() : null;

            double trh = issame(cr, 0.0) ? inf : ((ch - maxleak.h) / cr);
            double trt = leakts[cti].t - ct;

            if (Double.compare(trh, trt) < 0) {
                ct += ((ch - maxleak.h) / cr);
                ch = maxleak.h;
                cr--;
                cti--;
                leakhs.poll();
            } else {
                ch -= cr * (leakts[cti].t - ct);
                ct = leakts[cti].t;
                if (leakts[cti].h < ch) {
                    cr++;
                    leakhs.add(leakts[cti]);
                }
            }
//            } else {
//                ct = leakts[cti].t;
//                ch = maxleak.h;
//                cr--;
//                leakhs.poll();
//                if (leakts[cti].h < ch) {
//                    cr++;
//                    leakhs.add(leakts[cti]);
//                }
//            }
        }

        while (ch > eps && leakhs.size() > 0) {
            while (leakhs.size() > 0 && (leakhs.peek().h > ch || issame(leakhs.peek().h, ch))) {
                leakhs.poll();
                cr--;
            }
            if (leakhs.size() == 0)
                break;
            leak maxleak = leakhs.peek();
            ct += ((ch - maxleak.h) / cr);
            ch = maxleak.h;
            cr--;
            leakhs.poll();
        }

        out.println(ct);
    }

    boolean issame(double a, double b) {
        return Math.abs(a - b) < eps;
    }

    double inf = 1e12;
    double eps = 1e-7;
}
