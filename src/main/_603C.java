package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _603C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] a = new int[n];

        in.readArray(a, n, 0);

        if (k % 2 == 0) {
            int xor = 0;

            for (int i = 0; i < n; i++) {
                xor ^= calgrun1(a[i]);
            }

            if (xor == 0) {
                out.println("Nicky");
            } else {
                out.println("Kevin");
            }
        } else {
            int xor = 0;

            for (int i = 0; i < n; i++) {
                xor ^= calgrun(a[i]);
            }

            if (xor == 0) {
                out.println("Nicky");
            } else {
                out.println("Kevin");
            }
        }
    }

    Map<Integer, Integer> map = new HashMap<>();

    int calgrun1(int num) {
        if (num <= 2) {
            return num;
        } else if (num % 2 == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    int calgrun(int num) {
        if (num == 0) {
            return 0;
        }
        if (num % 2 == 1) {
            if (num <= 3) {
                return 1;
            } else {
                return 0;
            }
        } else {
            Set<Integer> s = new HashSet<>();

            s.add(calgrun(num - 1));
            s.add(calgrun(num / 2));

            for (int i = 0; ; i++) {
                if (!s.contains(i)) {
                    return i;
                }
            }
        }
    }
}
