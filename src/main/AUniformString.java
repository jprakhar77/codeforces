package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AUniformString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();

            int k = in.nextInt();

            StringBuilder ans = new StringBuilder();

            for (int i = 0; i < n; i++) {
                int ia = i % k;

                char c = (char) ('a' + ia);

                ans.append(c);
            }

            out.println(ans.toString());
        }
    }
}
