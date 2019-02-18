package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class DMahmoudAndEhabAndAnotherArrayConstructionTask {
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

    int max = 10000000;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        int[] sieve = sieve(max);

        int[] b = new int[n];

        boolean isupdone = false;
        int urv = -1;

        Set<Integer> op = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!isupdone) {
                o:
                for (int j = a[i]; ; j++) {
                    int num = j;

                    while (num > 1) {
                        int lp = sieve[num];
                        if (op.contains(lp)) {
                            continue o;
                        } else {
                            num = num / lp;
                        }
                    }

                    b[i] = j;
                    if (j > a[i]) {
                        urv = 2;
                        isupdone = true;
                    }
                    break;
                }
            } else {
                o:
                for (; ; urv++) {
                    int num = urv;

                    while (num > 1) {
                        int lp = sieve[num];
                        if (op.contains(lp)) {
                            continue o;
                        } else {
                            num = num / lp;
                        }
                    }

                    b[i] = urv;
                    urv++;
                    break;
                }
            }

            update(op, sieve, b[i]);
        }

        for (int i = 0; i < n; i++) {
            out.print(b[i] + " ");
        }
    }

    void update(Set<Integer> s, int[] sieve, int num) {
        while (num > 1) {
            int lp = sieve[num];
            s.add(lp);
            num = num / lp;
        }
    }
}
