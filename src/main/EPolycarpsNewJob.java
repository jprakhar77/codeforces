package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class EPolycarpsNewJob {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int xmax = 0;
        int ymax = 0;
        while (n-- > 0) {
            char ch = in.nextCharacter();

            if (ch == '+') {
                int x = in.nextInt();
                int y = in.nextInt();

                int min = Math.min(x, y);
                int max = Math.max(x, y);

                xmax = Math.max(xmax, min);
                ymax = Math.max(ymax, max);
            } else {
                int x = in.nextInt();
                int y = in.nextInt();

                int min = Math.min(x, y);
                int max = Math.max(x, y);

                if (min >= xmax && max >= ymax) {
                    out.println("YES");
                } else {
                    out.println("NO");
                }
            }
        }
    }
}
