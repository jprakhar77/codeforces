package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AIrreversibleOperation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        String s = in.next();

        int n = s.length();

        int ws = 0;

        long ans = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'B') {
                ans += ws;
            } else {
                ws++;
            }
        }

        out.println(ans);
    }
}
