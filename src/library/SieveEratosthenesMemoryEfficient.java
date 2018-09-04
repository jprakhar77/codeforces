package library;

import java.util.BitSet;

public class SieveEratosthenesMemoryEfficient {
    int n;

    public SieveEratosthenesMemoryEfficient(int n) {
        this.n = n;
    }

    long sumOfMultipliedPrimeFactors = 0;

    int mod;

    public BitSet sieve() {
        BitSet bs = new BitSet(((n + 5) / 6) * 2);

        long num = 2;

        long cans = 0;
        while (num <= n) {
            cans += n / num;
            num *= 2;
        }

        sumOfMultipliedPrimeFactors += cans;
        sumOfMultipliedPrimeFactors %= mod;

        for (int j = 2 * 2; j <= n; j += 2) {
            int jm6 = j % 6;

            if (jm6 == 1) {
                int bi = (j / 6) * 2;
                bs.set(bi);
            }

            if (jm6 == 5) {
                int bi = (j / 6) * 2 + 1;
                bs.set(bi);
            }
        }

        num = 3;

        cans = 0;
        while (num <= n) {
            cans += n / num;
            num *= 3;
        }
        sumOfMultipliedPrimeFactors += cans;
        sumOfMultipliedPrimeFactors %= mod;

        for (int j = 2 * 3; j <= n; j += 3) {
            if ((j & 1) == 0)
                continue;
            int jm6 = j % 6;

            if (jm6 == 1) {
                int bi = (j / 6) * 2;
                bs.set(bi);
            }

            if (jm6 == 5) {
                int bi = (j / 6) * 2 + 1;
                bs.set(bi);
            }
        }

        num = 5;

        cans = 0;
        while (num <= n) {
            cans += n / num;
            num *= 5;
        }
        sumOfMultipliedPrimeFactors += cans;
        sumOfMultipliedPrimeFactors %= mod;

        for (int j = 2 * 5; j <= n; j += 5) {
            if ((j & 1) == 0)
                continue;
            int jm6 = j % 6;

            if (jm6 == 1) {
                int bi = (j / 6) * 2;
                bs.set(bi);
            }

            if (jm6 == 5) {
                int bi = (j / 6) * 2 + 1;
                bs.set(bi);
            }
        }

        for (int i = 1; i * 6 <= n + 5; i++) {
            int p1 = i * 6 + 1;
            if (p1 > n)
                break;
            if (bs.get(i * 2) == false) {
                num = p1;
                cans = 0;
                while (num <= n) {
                    cans += n / num;
                    num *= p1;
                }
                sumOfMultipliedPrimeFactors += cans;
                sumOfMultipliedPrimeFactors %= mod;

                if (n / p1 >= p1) {
                    for (int j = p1 * p1; j <= n; j += p1) {
                        if ((j & 1) == 0)
                            continue;
                        int t = j / 6;
                        int jm6 = j - t * 6;

                        int bi = t << 1;
                        if (jm6 == 1) {
                            bs.set(bi);
                        }

                        if (jm6 == 5) {
                            bi++;
                            bs.set(bi);
                        }
                    }
                }

            }

            int p2 = i * 6 + 5;
            if (p2 > n)
                break;
            if (bs.get(i * 2 + 1) == false) {
                num = p2;
                cans = 0;
                while (num <= n) {
                    cans += n / num;
                    num *= p2;
                }
                sumOfMultipliedPrimeFactors += cans;
                sumOfMultipliedPrimeFactors %= mod;

                if (n / p2 >= p2) {
                    for (int j = p2 * p2; j <= n; j += p2) {
                        if ((j & 1) == 0)
                            continue;
                        int t = j / 6;
                        int jm6 = j - t * 6;

                        int bi = t << 1;
                        if (jm6 == 1) {
                            bs.set(bi);
                        }

                        if (jm6 == 5) {
                            bi++;
                            bs.set(bi);
                        }
                    }
                }
            }
        }
        return bs;
    }
}
