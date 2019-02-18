package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class BEmotes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long m = in.nextInt();

        long k = in.nextInt();

        //int[] a = in.nextIntArray(n);

        Integer[] a = new Integer[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        Arrays.sort(a);

        long max = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, a[i]);
        }

        long t = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == max) {
                t++;
            }
        }

        if (t > 1) {
            out.println(max * m);
        } else {
            long smax = a[n - 2];

            long tm = m / (k + 1);

            long ans = 0;

            ans += tm * (k * max + smax);

            long rem = m - tm * (k + 1);

            ans += rem * max;

            out.println(ans);
        }
    }
}
