package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ASalemAndSticks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        int ans = -1;
        int mincos = 100000000;
        for (int i = 1; i <= 100; i++) {
            int cost = 0;
            for (int j = 0; j < n; j++) {
                if (a[j] > i) {
                    cost += (a[j] - i - 1);
                } else if (a[j] < i) {
                    cost += (i - a[j] - 1);
                }
            }

            if (cost < mincos) {
                ans = i;
                mincos = cost;
            }
        }

        out.print(ans + " " + mincos);
    }
}
