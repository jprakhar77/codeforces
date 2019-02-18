package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AParity {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int b = in.nextInt();
        int k = in.nextInt();

        int[] a = in.nextIntArray(k);

        long sum = 0;
        for (int i = 0; i < k; i++) {
            long val = a[i];

            val *= pow(b, k - i - 1, 2);

            sum += val;
            sum %= 2;
        }

        if (sum == 0) {
            out.println("even");
        } else {
            out.println("odd");
        }
    }

    long pow(long a, long p, int mod) {
        if (p == 0) {
            return 1;
        }

        long t = pow(a, p / 2, mod);

        if (p % 2 != 0) {
            return (((t * t) % mod) * a) % mod;
        } else {
            return (t * t) % mod;
        }
    }
}
