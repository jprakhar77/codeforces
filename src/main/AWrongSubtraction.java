package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AWrongSubtraction {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        while (k-- > 0) {
            if (n % 10 != 0) {
                n--;
            } else {
                n /= 10;
            }
        }

        out.println(n);
    }
}
