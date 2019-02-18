package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BPetrAndACombinationLock {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n
        );

        for (int i = 0; i < (1 << n); i++) {
            int sum = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 0) {
                    sum += a[j];
                } else {
                    sum -= a[j];
                }
            }

            if (sum == 0 || sum % 360 == 0) {
                out.println("YES");
                return;
            }
        }

        out.println("NO");
    }
}
