package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _976B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextInt();
        long m = in.nextInt();

        long k = in.nextLong();

        if (k <= n - 1) {
            out.println((1 + k) + " " + 1);
            return;
        } else if (k == n) {
            out.println(n + " " + 2);
            return;
        } else {
            long lap = 2 * m - 2;

            long nk = k - (n - 1);

            long t = nk / lap;

            long rem = nk % lap;

            long s = n - 2 * t + 1;
            long c = 2;
            if (rem == 0) {
                out.println(s + " " + 2);
                return;
            } else if (rem == 1) {
                s--;
                out.println(s + " " + 2);
                return;
            } else if (rem <= m - 1) {
                s--;
                c += (rem - 1);
                out.println(s + " " + c);
                return;
            } else if (rem == m) {
                s -= 2;
                out.println(s + " " + m);
                return;
            } else {
                s -= 2;
                c = m;
                c -= (rem - m);
                out.println(s + " " + c);
                return;
            }
        }
    }
}
