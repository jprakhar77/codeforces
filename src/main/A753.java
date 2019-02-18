package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class A753 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x = in.nextInt();

        if (x == 3 || x == 5 || x == 7) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
