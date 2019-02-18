package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BYetAnotherArrayPartitioningTask {
    class num {
        int val;

        public num(int val, int i) {
            this.val = val;
            this.i = i;
        }

        int i;


    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        num[] na = new num[n];

        for (int i = 0; i < n; i++) {
            na[i] = new num(in.nextInt(), i);
        }

        Arrays.sort(na, (n1, n2) -> n2.val - n1.val);

        Set<Integer> s = new HashSet<>();

        long ans = 0;
        for (int i = 0; i < m * k; i++) {
            s.add(na[i].i);
            ans += na[i].val;
        }

        out.println(ans);

        int cc = 0;
        for (int i = 0, j = 0; i < n && j < k - 1; i++) {
            if (s.contains(i)) {
                cc++;

                if (cc == m) {
                    out.print(i + 1 + " ");
                    j++;
                    cc = 0;
                }
            }
        }

    }
}
