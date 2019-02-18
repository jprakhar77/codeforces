package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1071A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();

        int lo = 0;
        int hi = 100000;

        int ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (((long) mid * (mid + 1)) / 2 > a + b) {
                hi = mid - 1;
                continue;
            } else {
                ans = Math.max(ans, mid);

                lo = mid + 1;
            }

        }

        boolean[] ae = new boolean[(int) ans + 1];

        int csum = a;

        int av = 0;
        for (int i = 1; i <= ans; i++) {
            if (csum >= i) {
                ae[i] = true;
                csum -= i;
                av++;
            } else break;
        }

        int lg = ans;

        for (int i = ans; i >= 1; i--) {
            if (ae[(int) i]) {
                if (lg - i >= csum) {
                    ae[i] = false;
                    ae[i + csum] = true;
                    break;
                } else {
                    ae[i] = false;
                    ae[lg] = true;
                    csum -= (lg - i);
                    lg--;
                }
            }
        }

        out.println(av);

        for (int i = 1; i <= ans; i++)
        {
            if (ae[i])
            {
                out.print(i + " ");
            }
        }

        out.println();
        out.println(ans - av);

        for (int i = 1; i <= ans; i++)
        {
            if (!ae[i])
            {
                out.print(i + " ");
            }
        }
    }
}
