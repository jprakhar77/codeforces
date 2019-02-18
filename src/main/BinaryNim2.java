package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BinaryNim2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        String st = in.next();

        int start = -1;

        if (st.equals("Dee"))
            start = 0;
        else
            start = 1;

        int f = 0;
        int s = 0;

        for (int i = 0; i < n; i++) {
            String b = in.next();

            if (b.charAt(0) == b.charAt(b.length() - 1)) {
                int val = b.charAt(0) - '0';

                if (val == start) {
                    f++;
                } else {
                    s++;
                }
            }
        }

        if (f <= s) {
            out.println(st);
        } else {
            if (st.equals("Dee"))
                out.println("Dum");
            else
                out.println("Dee");
        }
    }
}
