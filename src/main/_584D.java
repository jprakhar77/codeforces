package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _584D {
    Set<Integer> prime(int n) {
        boolean[] isp = new boolean[n + 1];

        Arrays.fill(isp, true);

        isp[1] = false;

        for (int i = 2; i <= n; i++) {
            if (isp[i]) {
                for (int j = 2; j * i <= n; j++) {
                    isp[i * j] = false;
                }
            }
        }

        Set<Integer> primes = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            if (isp[i])
                primes.add(i);
        }

        return primes;
    }

    boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        Set<Integer> primes = prime(10000);

        if (isPrime(n)) {
            out.println("1");
            out.println(n);
            return;
        }

        for (int i = n; ; i--) {
            if (isPrime(i)) {
                if (primes.contains(n - i)) {
                    out.println("2");
                    out.println(i + " " + (n - i));
                    return;
                }

                for (int j = 2; j <= n - i; j++) {
                    if (isPrime(j) && isPrime(n - i - j)) {
                        out.println("3");
                        out.println(i + " " + j + " " + (n - i - j));
                        return;
                    }
                }
            }
        }
    }
}
