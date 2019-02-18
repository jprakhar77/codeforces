package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _960D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int q = in.nextInt();

        long[] shift = new long[60];

        long[] rs = new long[60];
        long[] re = new long[60];

        rs[0] = 1;
        re[0] = 1;
        for (int i = 1; i < 60; i++) {
            rs[i] = rs[i - 1] * 2;
            re[i] = (rs[i] * 2) - 1;
        }

        while (q-- > 0) {
            int t = in.nextInt();

            long x = in.nextLong(), k = -1;
            if (t <= 2) {
                k = in.nextLong();
            }

            if (t == 1) {

                for (int i = 0; i < 60; i++) {
                    if (x >= rs[i] && x <= re[i]) {

                        shift[i] += k;
                        break;

                    }
                }

            } else if (t == 2) {
                boolean f = false;
                int g = 0;
                for (int i = 0; i < 60; i++) {
                    if (x >= rs[i] && x <= re[i]) {

                        shift[i] += k;
                        f = true;

                        g++;
                    } else if (f) {
                        shift[i] += (1l << g) * k;
                        g++;
                    }
                }
            } else {

                norm(shift, rs, re);

                int ci = -1;
                for (int i = 0; i < 60; i++) {
                    if (x >= rs[i] && x <= re[i]) {

                        ci = i;
                        break;

                    }
                }

                long cli = getIndFromNum(x, rs[ci], re[ci], shift[ci]) + rs[ci] - 1;

                while (ci >= 0) {

                    out.print(getNumFromInd(cli - rs[ci] + 1, rs[ci], re[ci], shift[ci]));
                    out.print(" ");
                    ci--;
                    cli /= 2;
                }

                out.println();

            }
        }
    }

    long getNumFromInd(long ind, long rs, long re, long shift) {

        long acn = rs + ind - 1;

        acn -= shift;

        long r = re - rs + 1;

        if (acn < rs) {
            acn += r;
        }

        if (acn > re) {

            acn -= r;
        }

        return acn;
    }

    long getIndFromNum(long num, long rs, long re, long shift) {

        long aci = num - rs + 1;

        aci += shift;

        long r = re - rs + 1;

        if (aci > r) {

            aci -= r;
        }

        if (aci < 1) {

            aci += r;
        }

        return aci;
    }

    void norm(long[] shifts, long[] rs, long[] re) {
        for (int i = 0; i < 60; i++) {

            long r = re[i] - rs[i] + 1;

            shifts[i] %= r;
        }
    }
}
