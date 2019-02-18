package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CDuffAndWeightLifting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        Integer[] w = new Integer[n];

        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
        }

        Arrays.sort(w);

        Set<Long> s = new HashSet<>();
        for (int i = 0; i < n; i++) {
            long num = w[i];
            while (s.contains(num)) {
                s.remove(num);
                num++;
            }
            s.add(num);
        }

        out.println(s.size());
    }
}
