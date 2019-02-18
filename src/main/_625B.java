package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _625B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();

        int min = Math.min(Math.min(a + c, a + b), Math.min(b + d, c + d));
        int max = Math.max(Math.max(a + c, a + b), Math.max(b + d, c + d));

        long ans = 0;

        for (int i = 1; i <= n; i++) {
            int cmin = i + max + 1;
            int cmax = i + min + n;

            if (cmin <= cmax) {
                ans += (cmax - cmin + 1);
            }
        }

        out.print(ans);
    }
}
