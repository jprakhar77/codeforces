package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BKEYENCEString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        String s = in.next();

        int n = s.length();

        String keyence = "keyence";

        if (s.startsWith(keyence) || s.endsWith(keyence)) {
            out.println("YES");
            return;
        }

        for (int i = 0; i < 7; i++) {
            String cs = s.substring(0, i + 1);

            int rsz = 7 - i - 1;
            String rs = s.substring(n - rsz, n);

            cs += rs;

            if (cs.equals(keyence)) {
                out.println("YES");
                return;
            }
        }

        out.println("NO");
    }
}
