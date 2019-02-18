package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AdaSchool {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();

            int val = n * m;

            if (val % 2 == 0) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }
}
