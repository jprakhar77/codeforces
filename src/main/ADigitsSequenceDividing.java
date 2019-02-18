package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ADigitsSequenceDividing {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int q = in.nextInt();

        while (q-- > 0) {
            int n = in.nextInt();
            String s = in.next();

            if (s.length() == 2) {
                if (s.charAt(0) >= s.charAt(1)) {
                    out.println("NO");
                    continue;
                }
            }

            out.println("YES");
            out.println(2);
            out.print(s.charAt(0) + " " + s.substring(1));
            out.println();
        }
    }
}
