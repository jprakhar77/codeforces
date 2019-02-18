package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class LicensePlates {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();

        int ans = 1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'c') {
                if (i > 0 && s.charAt(i - 1) == 'c') {
                    ans *= 25;
                } else {
                    ans *= 26;
                }
            } else {
                if (i > 0 && s.charAt(i - 1) == 'd') {
                    ans *= 9;
                } else {
                    ans *= 10;
                }
            }
        }

        out.println(ans);
    }
}
