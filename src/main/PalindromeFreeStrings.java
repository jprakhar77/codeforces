package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class PalindromeFreeStrings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();

        int ans = 0;

        StringBuilder sb = new StringBuilder(s);

        int nc = 1;
        for (int i = 1; i < n; i++) {
            if (sb.charAt(i) == sb.charAt(i - 1)) {
                sb.setCharAt(i, (char) ('z' + nc));
                nc++;
                ans++;
            }
            if (i > 1 && sb.charAt(i) == sb.charAt(i - 2)) {
                sb.setCharAt(i, (char) ('z' + nc));
                nc++;
                ans++;
            }
        }

        out.println(ans);
    }
}
