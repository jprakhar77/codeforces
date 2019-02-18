package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _665D {
    int max = 2000000;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        boolean[] isp = new boolean[max + 1];

        for (int i = 2; i <= max; i++) {
            isp[i] = true;
        }

        for (int i = 2; i <= max; i++) {
            if (isp[i]) {
                for (int j = 2; j * i <= max; j++) {
                    isp[j * i] = false;
                }
            }
        }
        int ones = 0;
        int twos = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            if (a[i] == 1) {
                ones++;
            }
            if (a[i] == 2) {
                twos++;
            }
        }

        if (twos > 0 && ones > 0) {
            out.println(ones + 1);
            for (int l = 0; l < ones; l++) {
                out.print(1 + " ");
            }
            out.print(2 + " ");
            return;
        }
        if (twos == 0 && ones > 0) {
            for (int i = 0; i < n; i++) {
                if (a[i] %2 == 0 && isp[a[i] + 1]) {
                    out.println(ones + 1);
                    out.print(a[i] + " ");
                    for (int l = 0; l < ones; l++) {
                        out.print(1 + " ");
                    }
                    return;
                }
            }
        }

        if (ones > 1) {
            out.println(ones);
            for (int l = 0; l < ones; l++) {
                out.print(1 + " ");
            }
            return;
        }

        int ev = -1;
        int od = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isp[a[i] + a[j]]) {
                    out.println(2);
                    out.println(a[i] + " " + a[j]);
                    return;
                }
            }
        }

        out.println(1);
        out.println(a[0]);
    }
}
