package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class BEhabAndSubtraction {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int k = in.nextInt();

        Integer[] a = new Integer[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        Arrays.sort(a);

        int j = 0;
        int cmin = 0;
        for (int i = 0; i < n && j < k; i++) {
            int val = a[i] - cmin;

            if (val > 0) {
                out.println(val);

                cmin = a[i];

                j++;
            }
        }

        if (k > j) {
            for (int i = 0; i < k - j; i++) {
                out.println(0);
            }
        }
    }
}
