package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _675A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.nextInt();
        long b = in.nextInt();
        long c = in.nextInt();

        if (c == 0) {
            if (b == a) {
                out.print("YES");
            } else {
                out.print("NO");
            }
            return;
        }
        if ((b - a) % c == 0 && (b - a) / c >= 0) {
            out.print("YES");
        } else {
            out.print("NO");
        }
    }
}
