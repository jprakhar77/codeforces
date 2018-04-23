package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskE {
    class ap {
        int x;
        int v;
        double t;

        public ap(int x, int v) {
            this.x = x;
            this.v = v;
            this.t = (double) x / v;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int w = in.nextInt();

        List<ap> l = new ArrayList<>();
        List<ap> r = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int v = in.nextInt();

            if (x < 0) {
                ap ap = new ap(-x, v);
                l.add(ap);
            } else {
                ap ap = new ap(x, -v);
                r.add(ap);
            }

        }

        l.sort((ap1, ap2) -> (int) Math.signum(ap1.t - ap2.t));
        r.sort((ap1, ap2) -> (int) Math.signum(ap1.t - ap2.t));

        long ans = 0;

        for (int i = 0; i < l.size(); i++) {
            int li = lbs(r, l.get(i), w);
            int ri = rbs(r, l.get(i), w);

            if (ri >= li)
                ans += (ri - li + 1);
        }

        out.println(ans);
    }

    int lbs(List<ap> r, ap l, int w) {
        int n = r.size();
        int lo = 0;
        int hi = n - 1;

        int ans = n;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            ap rap = r.get(mid);

            double val = cal(l.x, l.v, rap.x, rap.v);

            if (val >= -w && val <= w) {
                ans = Math.min(ans, mid);
                hi = mid - 1;
            } else {
                if (mid == 0) {
                    lo = mid + 1;
                } else if (mid == n - 1) {
                    hi = mid - 1;
                } else {
                    ap rapl = r.get(mid - 1);
                    double lval = cal(l.x, l.v, rapl.x, rapl.v);

                    ap rapr = r.get(mid + 1);
                    double rval = cal(l.x, l.v, rapr.x, rapr.v);

                    if (Math.abs(rval) < Math.abs(lval)) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
            }
        }

        return ans;
    }

    int rbs(List<ap> r, ap l, int w) {
        int n = r.size();
        int lo = 0;
        int hi = n - 1;

        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            ap rap = r.get(mid);

            double val = cal(l.x, l.v, rap.x, rap.v);

            if (val >= -w && val <= w) {
                ans = Math.max(ans, mid);
                lo = mid + 1;
            } else {
                if (mid == 0) {
                    lo = mid + 1;
                } else if (mid == n - 1) {
                    hi = mid - 1;
                } else {
                    ap rapl = r.get(mid - 1);
                    double lval = cal(l.x, l.v, rapl.x, rapl.v);

                    ap rapr = r.get(mid + 1);
                    double rval = cal(l.x, l.v, rapr.x, rapr.v);

                    if (Math.abs(rval) < Math.abs(lval)) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
            }
        }

        return ans;
    }

    double cal(long x1, long v1, long x2, long v2) {
        double num = (double) (x1 * v2 - x2 * v2);
        double den = x1 + x2;

        return num / den;
    }
}
