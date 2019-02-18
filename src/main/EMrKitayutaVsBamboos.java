package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class EMrKitayutaVsBamboos {
    class ele {
        long h;
        long a;

        public ele(long h, long a) {
            this.h = h;
            this.a = a;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long m = in.nextInt();

        int k = in.nextInt();

        int p = in.nextInt();

        long max = 0;

        h = new long[n];
        a = new long[n];

        for (int i = 0; i < n; i++) {
            h[i] = in.nextInt();
            a[i] = in.nextInt();
            max = Math.max(max, h[i] + m * a[i]);
        }

        long lo = 0;
        long hi = max;

        long ans = hi;

        t = new long[n];
        dif = new long[n];
        req = new int[(int) m];

        while (lo <= hi) {
            long mid = (lo + hi) / 2;

            if (isp(n, m, k, p, mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        out.println(ans);
    }

    long[] h;
    long[] a;
    long[] t;

    long[] dif;

    int[] req;

    boolean isp(int n, long m, int k, int p, long mid) {

        long tot = 0;
        for (int i = 0; i < n; i++) {
            dif[i] = h[i] + a[i] * m - mid;
//            if (dif[i] < 0) {
//                dif[i] = 0;
//            }
            t[i] = (dif[i] + p - 1) / p;
            tot += t[i];
        }

        if (tot > k * m)
            return false;

        Arrays.fill(req, 0);

        for (int i = 0; i < n; i++) {
            long cd = dif[i];
            long ct = t[i];

            if (cd <= 0)
                continue;

            long lind = -1;
            long lval = -1;
            while (ct-- > 0) {
                long cind = -1;
                long x = (cd - h[i]) / a[i];
                if (cd <= h[i]) {
                    x = 0;
                }
                if (cd <= h[i] || (cd - h[i]) % a[i] == 0) {
                    if (x >= m)
                        return false;
                    req[(int) x]++;
                    cind = x;
                } else {
                    if (x + 1 >= m)
                        return false;
                    req[(int) x + 1]++;
                    cind = x + 1;
                }
                if (cind == lind) {
                    long td = Math.min(p, lval);
                    cd -= td;
                    lval -= td;
                } else {
                    long cv = h[i] + cind * a[i];
                    long td = Math.min(p, cv);
                    cd -= td;
                    lval = cv - td;
                    lind = cind;
                }
            }
        }

        long rsum = 0;
        for (int i = (int) m - 1; i >= 0; i--) {
            rsum += req[i];

            if (rsum > (m - i) * k) {
                return false;
            }
        }

        return true;
    }
}
