package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ANewYearAndTheChristmasOrnament {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int y = in.nextInt();
        int b = in.nextInt();
        int r = in.nextInt();

        int ans = 1;
        for (int i = 1; i <= y; i++) {
            if (b > i && r > i + 1) {
                ans = i;
            }
        }

        out.println(3 * ans + 3
        );
    }
}
