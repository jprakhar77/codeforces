package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class XTower {
    class box {
        int w;
        int s;
        int v;

        public box(int w, int s, int v) {
            this.w = w;
            this.s = s;
            this.v = v;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        box[] boxes = new box[n];

        for (int i = 0; i < n; i++) {
            boxes[i] = new box(in.nextInt(), in.nextInt(), in.nextInt());
        }

        Arrays.sort(boxes, (b1, b2) -> (b1.w + b1.s - b2.w - b2.s));

        long[][] dp = new long[n][20001];

        for (int i = boxes[0].w; i <= 20000; i++) {
            dp[0][i] = boxes[0].v;
        }

        for (int i = 1; i < n; i++) {
            int cw = boxes[i].w;
            int cs = boxes[i].s;
            int cv = boxes[i].v;

            for (int j = 0; j <= 20000; j++) {
                dp[i][j] = dp[i - 1][j];
            }

            for (int j = 0; j <= Math.min(cs, 20000 - cw); j++) {
                dp[i][j + cw] = Math.max(dp[i][j + cw], dp[i - 1][j] + cv);
            }

            for (int j = 1; j <= 20000; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i][j]);
            }
        }

        out.println(dp[n - 1][20000]);
    }
}
