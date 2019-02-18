package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BZuhairAndStrings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        String s = in.next();

        int ans = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            int rc = 0;
            int val = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == c) {
                    rc++;
                    if (rc == k) {
                        val++;
                        rc = 0;
                    }
                } else {
                    rc = 0;
                }
            }

            ans = Math.max(ans, val);
        }

        out.print(ans);
    }
}
