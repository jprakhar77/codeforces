package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AGennadyAndACardGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        char ch1 = s.charAt(0);
        char ch2 = s.charAt(1);

        for (int i = 0; i < 5; i++) {
            String t = in.next();
            char ch3 = t.charAt(0);
            char ch4 = t.charAt(1);

            if (ch1 == ch3 || ch2 == ch4) {
                out.println("YES");
                return;
            }
        }

        out.println("NO");
    }
}
