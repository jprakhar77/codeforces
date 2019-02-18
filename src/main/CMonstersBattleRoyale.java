package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CMonstersBattleRoyale {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);

        long gcd = a[0];

        for (int i  =1; i < n; i++)
        {
            gcd = gcd(a[i], gcd);
        }

        out.println(gcd);

    }

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
