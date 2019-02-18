package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class _997B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        Set<Long> s = new HashSet<>();

        for (long i = 0; i <= n; i++) {
            for (long j = 0; i + j <= n; j++) {
                for (long k = 0; i + j + k <= n; k++) {
                    long rn = n - i - j - k;

                    long sum = i * 1 + j * 5 + k * 10 + rn * 50;

                    s.add(sum);
                }
            }
        }

        out.println(s.size());
    }
}
