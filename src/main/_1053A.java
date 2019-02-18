package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1053A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();
        long m = in.nextLong();
        long k = in.nextLong();

        long area = 2 * n * m;

        if (area % k != 0) {
            out.println("NO");
            return;
        }

        long nn = n;
        long nm = m;

        long ck = k;

        long rf = 2;

        for (long i = 2; i * i <= ck; i++) {
            if (ck % i == 0) {
                while (ck % i == 0) {
                    if (nn % i == 0) {
                        nn /= i;
                    } else if (nm % i == 0) {
                        nm /= i;
                    } else if (rf % i == 0) {
                        rf /= i;
                    }
                    ck /= i;
                }
            }
        }

        if (ck > 1) {
            if (nn % ck == 0) {
                nn /= ck;
            } else if (nm % ck == 0) {
                nm /= ck;
            }else if (rf % ck == 0) {
                rf /= ck;
            }
        }

        if (rf * nn <= n) {
            out.println("YES");
            out.println("0 0");
            out.println(rf * nn + " 0");
            out.println("0 " + nm);
        } else if (rf * nm <= m) {
            out.println("YES");
            out.println("0 0");
            out.println(nn + " 0");
            out.println("0 " + rf * nm);
        } else {
            out.println("NO");
        }
    }
}
