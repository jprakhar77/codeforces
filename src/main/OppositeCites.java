package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class OppositeCites {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        if (n % 2 == 1) {
            out.println("NO");
            return;
        }

        int maxp = 1;

        int nby2 = n / 2;
//        while (nby2 % maxp == 0) {
//            maxp *= 2;
//        }
//
//        maxp /= 2;
        //out.println(maxp);

        maxp = nby2;

        for (int i = 0; i < maxp; i++) {
            int num = -1;
            for (int j = i; j < n; j += maxp) {
                if (a[j] != -1) {
                    if (num != -1 && num != a[j]) {
                        out.println("NO");
                        return;
                    }
                    num = a[j];
                }
            }
            if (num == -1) {
                num = 1;
            }
            for (int j = i; j < n; j += maxp) {
                a[j] = num;
            }
        }

        out.println("YES");
        for (int i = 0; i < n; i++) {
            out.print(a[i] + " ");
        }
        out.println();
    }
}
