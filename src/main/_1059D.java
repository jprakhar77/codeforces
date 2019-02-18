package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1059D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        double[] x = new double[n];
        double[] y = new double[n];

        maxy = -1e9;
        double miny = 1e9;

        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();

            maxy = Math.max(maxy, y[i]);
            miny = Math.min(miny, y[i]);
        }

        if (miny < 0 && maxy > 0) {
            out.println(-1);
            return;
        }

        if (maxy < 0) {
            for (int i = 0; i < n; i++) {
                y[i] = -y[i];
            }
            maxy = -miny;
        }

        for (int i = 0; i < n; i++) {
            maxx = Math.max(maxx, x[i]);
            minx = Math.min(minx, x[i]);
        }

        double lo = 0;
        double hi = 5e13 + 5;

        double ans = inf;

        for (int i = 0; i < 190; i++) {
            double mid = (lo + hi) / 2;

            if (isPoss(mid, x, y)) {
                ans = Math.min(ans, mid);
                hi = mid;
            } else {
                lo = mid;
            }
        }

        out.println(ans);


    }

    double inf = 1e15;

    double maxy;

    double minx = 1e9;
    double maxx = 1e-9;

    boolean isPoss(double r, double[] x, double[] y) {
        if (maxy > 2 * r)
            return false;

        int n = x.length;

        double xans = 1e9;

        double lo = minx;
        double hi = maxx;

        for (int j = 0; j < 100; j++) {
            double mid = (lo + hi) / 2;

            boolean goleft = false;
            boolean goright = false;

            double r2 = r * r;
            for (int i = 0; i < n; i++) {
                if (dis(x[i], y[i], mid, r) > r) {
                    if (x[i] < mid) {
                        goleft = true;
                    } else if (x[i] > mid) {
                        goright = true;
                    }
                }
            }

            if (goleft && goright)
                return false;
            else if (goleft)
                hi = mid;
            else if (goright)
                lo = mid;
            else
                return true;
        }

        return false;
    }

    double eps = 1e-6;

    double dis(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

}
