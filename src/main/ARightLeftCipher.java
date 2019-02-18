package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ARightLeftCipher {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();
        String t = "";

        if (n % 2 == 0) {
            t += s.charAt(n / 2 - 1);

            int i = n / 2 - 2;
            int j = n / 2;

            while (true) {
                if (j < n)
                    t += s.charAt(j);
                if (i >= 0)
                    t += s.charAt(i);

                j++;
                i--;

                if (i < 0 && j >= n)
                    break;
            }
        } else {
            t += s.charAt(n / 2);

            int i = n / 2 - 1;
            int j = n / 2 + 1;

            while (true) {
                if (j < n)
                    t += s.charAt(j);
                if (i >= 0)
                    t += s.charAt(i);

                j++;
                i--;

                if (i < 0 && j >= n)
                    break;
            }
        }

        out.println(t);
    }
}
