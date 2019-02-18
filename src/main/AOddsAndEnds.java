package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AOddsAndEnds {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        if (n % 2 == 0) {
            out.println("No");
            return;
        }

        if (a[0] % 2 == 0 || a[n - 1] % 2 == 0) {
            out.println("No");
            return;
        }

        int cl = 1;

        int c = 0;
        for (int i = 1; i < n; i++) {
            if (a[i] % 2 == 0) {
                cl++;
            } else {
                if (cl % 2 == 0) {
                    cl++;
                } else {
                    if (i < n - 1 && a[i + 1] % 2 == 0) {
                        cl++;
                    } else {
                        c++;
                        cl = 1;
                    }
                }
            }
        }

        if (cl != 0 && cl % 2 == 0) {
            out.println("No");
            return;
        }

        if (cl % 2 == 1) {
            c++;
        }

        if (c % 2 == 0) {
            out.println("No");
            return;
        }

        out.println("Yes");
    }
}
