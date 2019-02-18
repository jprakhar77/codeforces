package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _985D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();
        long h = in.nextLong();

        long lo = 1;
        long hi = Integer.MAX_VALUE;

        long ans = hi;
        while (lo <= hi) {
            long mid = (lo + hi) / 2;

            if (poss(mid, n, h)) {
                ans = Math.min(ans, mid);
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        out.println(ans);
    }

    boolean poss(long num, long n, long h) {
        if (num <= h) {
            long ini = num;

            long ans = (ini * (ini + 1)) / 2;

            if (ans >= n)
                return true;
            else
                return false;
        } else {
            long lo = 1;
            long hi = num;

            long ans = lo;
            while (lo <= hi) {
                long mid = (lo + hi) / 2;

                long max = h + mid - 1;

                if (num - mid + 1 >= max) {
                    ans = Math.max(ans, mid);
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            long high = h + ans - 1;
            long val = cal(high);
            val -= cal(h - 1);

            if (ans + high - 1 < num) {
                val += cal(high);
            } else {
                val += cal(high - 1);
            }

            if (val >= n)
                return true;
            else
                return false;
        }
    }

    long cal(long num) {
        return (num * (num + 1)) / 2;
    }
}
