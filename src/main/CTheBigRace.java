package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CTheBigRace {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long t = in.nextLong();
        long w = in.nextLong();
        long b = in.nextLong();

        long l = w / gcd(w, b);

        if (t / l < b) {
            l = t + 10;
        } else {

            l *= b;
        }

        long min = Math.min(w, b);

        long times = t / l;

        long ans = 0;

        if (times > 1) {
            ans += (times - 1) * min;
        }

        if (t >= min - 1) {
            ans += min - 1;
        } else {
            ans += t;
        }

        if (times > 0) {
            long ll = l * times;

            if (ll + min - 1 <= t) {
                ans += min;
            } else {
                ans += t - ll + 1;
            }
        }

        long g = gcd(ans, t);

        ans /= g;
        t /= g;

        out.println(ans + "/" + t);
    }

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
