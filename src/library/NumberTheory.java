package library;

import java.util.*;

public class NumberTheory {
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

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    void _2And2InvPowers(int n, long mod) {
        long[] pt = new long[n + 1];

        pt[0] = 1;

        for (int i = 1; i <= n; i++) {
            pt[i] = (pt[i - 1] * 2) % mod;
        }

        long[] pti = new long[n + 1];

        pti[0] = pow(pt[0], mod - 2, (int) mod);
        long ti = pow(2, mod - 2, (int) mod);
        for (int i = 1; i <= n; i++) {
            pti[i] = pti[i - 1] * ti;
            pti[i] %= mod;
        }
    }

    List<Integer> prime(int n) {
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

        List<Integer> primes = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (isp[i])
                primes.add(i);
        }

        return primes;
    }

    long[] nCr(int n, int mod) {
        long[] ncr = new long[n + 1];

        ncr[0] = 1;

        for (int i = 1; i <= n; i++) {
            ncr[i] = ncr[i - 1] * (n - (i - 1));
            ncr[i] %= mod;
            ncr[i] *= pow(i, mod - 2, mod);
            ncr[i] %= mod;
        }

        return ncr;
    }

    long nCr(int n, int r, long[] fac, long[] ifac, int mod) {
        long ans = fac[n];
        ans *= ifac[n - r];
        ans %= mod;
        ans *= ifac[r];
        ans %= mod;

        return ans;
    }

    HashMap<Long, Integer> primeFactors(long n) {
        HashMap<Long, Integer> cm = new HashMap<>();

        long cn = n;
        for (long i = 2; i * i <= cn; i++) {
            if (cn % i == 0) {
                while (cn % i == 0) {
                    cn /= i;
                    cm.merge(i, 1, (x, y) -> x + y);
                }
            }
        }

        if (cn > 1) {
            cm.merge(cn, 1, (x, y) -> x + y);
        }

        return cm;
    }

    Set<Long> factors(long n) {
        Set<Long> factors = new HashSet<>();

        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }

        return factors;
    }

    int[] sieve(int n) {
        int[] sieve = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            sieve[i] = i;
        }

        for (int i = 2; i * i <= n; i++) {
            if (sieve[i] == i) {
                for (int j = i; j * i <= n; j++) {
                    if (sieve[j * i] == j * i)
                        sieve[j * i] = i;
                }
            }
        }

        return sieve;
    }

    int[] mobius(int n) {
        int[] sieve = sieve(n);

        int[] mobius = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            Set<Integer> primeFactors = new HashSet<>();

            int curNum = i;

            boolean isSquareFree = true;
            while (curNum > 1) {
                int nextPrime = sieve[curNum];

                if (primeFactors.contains(nextPrime)) {
                    isSquareFree = false;
                    break;
                }

                primeFactors.add(nextPrime);
                curNum /= nextPrime;
            }

            if (isSquareFree) {
                if ((primeFactors.size() & 1) == 0) {
                    mobius[i] = 1;
                } else {
                    mobius[i] = -1;
                }
            }
        }

        return mobius;
    }

    boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    Set<Integer> primeFactors(int n) {

        Set<Integer> set = new HashSet<>();

        int cn = n;
        for (int i = 2; i * i <= cn; i++) {
            if (cn % i == 0) {
                set.add(i);
                while (cn % i == 0) {
                    cn /= i;
                }
            }
        }

        if (cn > 1) {
            set.add(cn);
        }

        return set;
    }

    long[] factorial(int n, int mod) {
        long[] factorial = new long[n + 1];

        factorial[0] = 1;

        for (int i = 1; i <= n; i++) {
            factorial[i] = i * factorial[i - 1];
            factorial[i] %= mod;
        }

        return factorial;
    }

    long[] factorialInverse(int n, int mod) {
        long[] factorialInverse = new long[n + 1];

        long[] inv = new long[n + 1];

        inv[1] = 1;

        for (int i = 2; i <= n; i++) {
            inv[i] = inv[mod % i];
            inv[i] *= (mod - mod / i);
            inv[i] %= mod;
        }

        factorialInverse[0] = 1;

        for (int i = 1; i <= n; i++) {
            factorialInverse[i] = inv[i] * factorialInverse[i - 1];
            factorialInverse[i] %= mod;
        }

        return factorialInverse;
    }
}
