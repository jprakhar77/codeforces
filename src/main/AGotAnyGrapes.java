package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AGotAnyGrapes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x = in.ni();
        int y = in.ni();
        int z = in.ni();

        int a = in.ni();
        int b = in.ni();
        int c = in.ni();

        if (a < x) {
            out.println("NO");
            return;
        }

        a -= x;

        if (a + b < y) {
            out.println("NO");
            return;
        }

        int sum = a + b - y;

        if (sum + c < z) {
            out.println("NO");
            return;
        }

        out.println("YES");

    }
}
