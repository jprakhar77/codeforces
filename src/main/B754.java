package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class B754 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();

        int ans = 10000000;
        for (int i = 0; i < n - 2; i++) {
            String cs = s.substring(i, i + 3);

            Integer num = Integer.parseInt(cs);

            ans = Math.min(ans, Math.abs(num - 753));
        }

        out.println(ans);
    }
}
