package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BFarRelativesProblem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] fa = new int[400];
        int[] ma = new int[400];
        for (int i = 0; i < n; i++) {
            char ch = in.nextCharacter();

            int a = in.nextInt();
            int b = in.nextInt();

            for (int j = a; j <= b; j++) {
                if (ch == 'M') {
                    ma[j]++;
                } else {
                    fa[j]++;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= 366; i++) {
            ans = Math.max(ans, 2 * Math.min(fa[i], ma[i]));
        }

        out.println(ans);
    }
}
