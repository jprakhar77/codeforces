package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class TaskD {
    long maxk = (long) 1e18;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        long maxn = 1;
        long val = 0;
        for (long i = 1; ; i++) {
            val = 1 + 4 * val;
            if (val >= maxk) {
                maxn = i;
                break;
            }
        }

        long[] vala = new long[(int) maxn + 1];

        vala[1] = 1;

        for (int i = 2; i <= maxn; i++) {
            vala[i] = 1 + 4 * vala[i - 1];
        }

        o:
        while (t-- > 0) {
            long n = in.nextInt();
            long k = in.nextLong();

            if (n - 1 >= maxn) {
                out.println("YES " + (n - 1));
                continue;
            }

            k--;

            if (k <= vala[(int)n - 1]) {
                out.println("YES " + (n - 1));
                continue;
            }

            long cs = n - 1;
            long cc = 3;
            int ind = 2;

            long end = -1;
            while (k > 0) {
                if (k >= cc && cs > 0) {
                    k -= cc;
                    cc += (1l << ind);
                    cs--;
                    ind++;
                } else {
//                    if (cs > 0 && k < cc) {
//                        out.println("YES " + cs);
//                        continue o;
//                    }
                    end = ind;
                    break;
                }
            }

            long fcs = cs;

            if (k == 0) {
                out.println("YES " + cs);
                continue;
            }

            k -= vala[(int) n - 1];

            cs = n - 1;
            cc = 3;
            ind = 2;

            long rv = 5;

            long sind = 3;

            while (k > 0 && ind < end) {
                k -= rv * vala[(int) cs - 1];
                rv += (1l << sind);
                sind++;
                cs--;
                ind++;
            }

            if (k <= 0) {
                out.println("YES " + fcs);
            } else {
                out.println("NO");
            }
        }
    }
}
