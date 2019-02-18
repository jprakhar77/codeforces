package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ARepeatingCipher {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        String s = in.next();


        int sz = 1;
        for (int i = 0; i < n; ) {
            char ch = s.charAt(i);

            out.print(ch);

            i += sz;

            sz++;
        }
    }
}
