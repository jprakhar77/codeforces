package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class ECyclicGCDs {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        Arrays.sort(a);

        long ans = a[0];

        for (int i = 1; i < n; i++) {
            ans *= gcd(i, a[i]);
            ans %= mod;
        }

        out.println(ans);
    }

    int mod = 998244353;

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
