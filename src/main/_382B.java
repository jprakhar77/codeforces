package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class _382B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int w = in.nextInt();
        int x = in.nextInt();

        int c = in.nextInt();

        if (c <= a) {
            out.println(0);
            return;
        }

        int[] step = new int[w];
        int[] ad = new int[w];

        Arrays.fill(step, -1);
        step[b] = 0;

        int cp = -1;
        int cl = -1;
        int fad = -1;
        int t = 1;
        while (true) {
            if (b >= x) {
                b = b - x;
                if (step[b] != -1) {
                    cp = b;
                    cl = t - step[b];
                    fad = ad[b + x] - ad[b];
                    break;
                } else {
                    step[b] = t;
                    ad[b] = ad[b + x];
                }
            } else {
                int nb = w - (x - b);

                if (step[nb] != -1) {
                    cp = nb;
                    cl = t - step[nb];
                    fad = ad[b] + 1 - ad[nb];
                    break;
                } else {
                    step[nb] = t;
                    ad[nb] = ad[b] + 1;
                }

                b = nb;
            }
            t++;
        }

        long ct = 0;
        if (cp != b) {
            if (c - step[cp] <= a - ad[cp]) {
                out.println(simul(a, b, w, x, c));
                return;
            }
            a -= ad[cp];
            ct += step[cp];
            c -= step[cp];
            b = cp;
        }

        if (a + cl + 1 >= c) {
            out.println(ct + simul(a, b, w, x, c));
            return;
        }

        long tot = (c - (a + cl + 1)) / (cl - fad);

        ct += tot * cl;
        a -= tot * fad;
        c -= tot * cl;

        out.println(ct + simul(a, b, w, x, c));
    }

    int simul(int a, int b, int w, int x, int c) {
        int ct = 0;
        while (c > a) {
            if (b >= x) {
                b = b - x;
            } else {
                b = w - (x - b);
                a = a - 1;
            }
            ct++;
            c--;
        }

        return ct;
    }
}

