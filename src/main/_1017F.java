package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.BitSet;

public class _1017F {
    long mod = (long) 1l << 32;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();

        BitSet bs = new BitSet(((n + 5) / 6) * 2);

        long ans = 0;

        long num = 2;

        long cans = 0;
        while (num <= n) {
            cans += n / num;
            num *= 2;
        }

        ans += cans * cal(2, a, b, c, d);
        ans %= mod;

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
        ans += cans * cal(3, a, b, c, d);
        ans %= mod;

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
        ans += cans * cal(5, a, b, c, d);
        ans %= mod;

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
                ans += cans * cal(p1, a, b, c, d);
                ans %= mod;

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
                ans += cans * cal(p2, a, b, c, d);
                ans %= mod;

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

        out.println(ans);

    }

    long cal(long num, int a, int b, int c, int d) {
        long num2 = (num * num) % mod;
        long num3 = (num2 * num) % mod;

        return (a * (num3) + b * (num2) + c * num + d) % mod;
    }
}
