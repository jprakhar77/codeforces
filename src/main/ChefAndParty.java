package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class ChefAndParty {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();

            Integer[] a = new Integer[n];

            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            Arrays.sort(a);

            long cs = 0;
            for (int i = 0; i < n; i++) {
                if (cs < a[i]) {

                } else {
                    cs++;
                }
            }

            out.println(cs);
        }
    }
}
