package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class MagicianVersusChef2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();
        int k = in.nextInt();

        if (n == 2) {
            out.println(0.5);
            return;
        }

        long cn = n;
        int ck = k;

        while (true) {
            if (ck == 0) {
                out.println(1.0 / cn);
                return;
            }

            if (cn == 1) {
                out.println(1.0);
                return;
            }

            if (cn % 2 == 0) {
                long cn2 = cn / 2;

                if (cn2 % 2 == 0) {
                    cn = cn2;
                } else {
                    if (cn == n) {
                        cn = cn2 + 1;
                    } else {
                        cn = cn2;
                    }
                }
            } else {
                cn = (cn + 1) / 2;
            }

            ck--;
        }
    }
}
