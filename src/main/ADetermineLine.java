package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class ADetermineLine {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        Set<Integer> s = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int r = in.nextInt();

            if (i == 0) {
                for (int j = 0; j < r; j++) {
                    int num = in.nextInt();

                    s.add(num);
                }
            } else {
                Set<Integer> ns = new HashSet<>();

                for (int j = 0; j < r; j++) {
                    ns.add(in.nextInt());
                }

                Set<Integer> ns2 = new HashSet<>();
                for (int val : s) {
                    if (ns.contains(val)) {
                        ns2.add(val);
                    }
                }

                s = ns2;
            }
        }

        for (int val : s) {
            out.print(val + " ");
        }
    }
}
