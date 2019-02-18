package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AnEasyProblem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        String s = in.next();

        int x = in.nextInt();

        int ch = Integer.valueOf(s.substring(0, 2));
        int cm = Integer.valueOf(s.substring(3, 5));

        int ans = -1;
        for (int i = 0; i < 24 * 60; i++) {
            int h = i / 60;

            int m = i % 60;

            int nh = ch + h;

            if (nh >= 24) {
                nh -= 24;
            }

            int nm = cm + m;
            if (nm >= 60) {
                nh++;
                nm -= 60;
                if (nh >= 24) {
                    nh -= 24;
                }
            }

            int sum = (nh % 10) + (nh / 10);
            int sum2 = (nm % 10) + (nm / 10);

            if ((sum + sum2) % x == 0) {
                ans = i;
                break;
            }
        }

        out.println(ans);
    }
}
