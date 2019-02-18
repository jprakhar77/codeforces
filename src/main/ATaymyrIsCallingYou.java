package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ATaymyrIsCallingYou {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int z = in.nextInt();

        long g = gcd(n, m);

        long l = (n * m) / g;

        out.println(z / l);
    }

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
