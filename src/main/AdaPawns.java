package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class AdaPawns {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int[] grundy = new int[230];

        for (int i = 0; i <= 200; i++) {
            grundy[i] = i % 3;
        }

        int t = in.nextInt();

        while (t-- > 0) {
            String s = in.next();

            int n = s.length();

            int pf = 0;

            int xor = 0;

            int lp = -1;
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == 'P') {
                    int ai = i - lp - 1;
                    l.add(grundy[ai]);
                    //xor = grundy[ai];
                    lp = i;
                }
            }

            for (int i = l.size() - 1; i >= 0; i--) {
                int j = l.size() - i - 1;

                if (j % 2 == 0) {
                    xor ^= l.get(i);
                }
            }

            if (xor == 0) {
                out.println("No");
            } else {
                out.println("Yes");
            }
        }
    }
}
