package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class agc016_b {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            s.add(a[i]);
        }

        Arrays.sort(a);

        if (s.size() == 1) {
            int num = a[0];

            if (num == n - 1 || n / num >= 2) {
                out.println("Yes");
                return;
            }
        } else if (s.size() == 2) {
            int num1 = a[0];
            int num2 = a[n - 1];

            if (num1 + 1 == num2) {
                for (int i = 1; i < n; i++) {
                    if (a[i - 1] + 1 == a[i]) {
                        if (num2 > i && ((n - i) / (num2 - i)) >= 2) {
                            out.println("Yes");
                            return;
                        }

                    }
                }
            }
        }

        out.println("No");

    }
}
