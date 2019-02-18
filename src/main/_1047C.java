package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1047C {
    int[] sieve(int n) {
        int[] sieve = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            sieve[i] = i;
        }

        for (int i = 2; i * i <= n; i++) {
            if (sieve[i] == i) {
                for (int j = i; j * i <= n; j++) {
                    sieve[j * i] = i;
                }
            }
        }

        return sieve;
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();


        int[] a = new int[n];

        int gcd = a[0];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            gcd = gcd(a[i], gcd);
        }


        for (int i = 0; i < n; i++)
            a[i] /= gcd;

        int[] sieve = sieve(15000000);

        int[] cm = new int[15000001];

        for (int i = 0; i < n; i++) {
            int cn = a[i];

            while (cn > 1) {
                int sn = sieve[cn];
                if (cn % sn == 0) {
                    cm[sn]++;
                    while (cn % sn == 0)
                        cn /= sn;
                }
            }
        }

        int ans = n;
        for (int i = 2; i <= 15000000; i++) {
            ans = Math.min(ans, n - cm[i]);
        }

        if (ans == n) {
            ans = -1;
        }

        out.println(ans);
    }

    int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
