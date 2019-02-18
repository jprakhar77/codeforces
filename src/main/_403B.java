package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class _403B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        HashSet<Integer> bs = new HashSet<>();

        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
            bs.add(b[i]);
        }

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int gcd = a[i];

            int bc = 0;
            int gc = 0;

            for (int j = 2; j * j <= gcd; j++) {
                if (gcd % j == 0) {
                    boolean isb = bs.contains(j);
                    while (gcd % j == 0) {
                        gcd /= j;
                        if (isb) {
                            bc++;
                        } else {
                            gc++;
                        }
                    }
                }
            }

            if (gcd > 1) {
                if (bs.contains(gcd)) {
                    bc++;
                } else {
                    gc++;
                }
            }

            ans -= bc;
            ans += gc;
        }

        int[] gcda = new int[n];

        gcda[0] = a[0];

        for (int i = 1; i < n; i++) {
            gcda[i] = gcd(gcda[i - 1], a[i]);
        }

        int td = 1;

        Map<Integer, Integer> pm = new HashMap<>();

        long bc = 0;
        long gc = 0;

        for (int i = n - 1; i >= 0; i--) {
            int gcd = gcda[i] / td;
            int ogcd = gcda[i];

            int cgcd = gcd;

            if (i == n - 1) {
                for (int j = 2; j * j <= gcd; j++) {
                    if (gcd % j == 0) {
                        boolean isb = bs.contains(j);
                        while (gcd % j == 0) {
                            pm.merge(j, 1, (x, y) -> x + y);
                            gcd /= j;
                            if (isb) {
                                bc++;
                            } else {
                                gc++;
                            }
                        }
                    }
                }

                if (gcd > 1) {
                    pm.merge(gcd, 1, (x, y) -> x + y);
                    if (bs.contains(gcd)) {
                        bc++;
                    } else {
                        gc++;
                    }
                }
            } else {
                int num = ogcd / gcda[i + 1];

                for (int j = 2; j * j <= num; j++) {
                    if (num % j == 0) {
                        boolean isb = bs.contains(j);
                        while (num % j == 0) {
                            pm.merge(j, 1, (x, y) -> x + y);
                            num /= j;
                            if (isb) {
                                bc++;
                            } else {
                                gc++;
                            }
                        }
                    }
                }

                if (num > 1) {
                    pm.merge(num, 1, (x, y) -> x + y);
                    if (bs.contains(num)) {
                        bc++;
                    } else {
                        gc++;
                    }
                }
            }

            if (bc > gc) {
                ans += (i + 1) * bc;
                ans -= (i + 1) * gc;
                td *= cgcd;
                bc = 0;
                gc = 0;
                pm = new HashMap<>();
            }
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
