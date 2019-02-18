package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class GameRank {
    int[] rs = new int[26];

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        for (int i = 1; i <= 10; i++)
            rs[i] = 5;

        for (int i = 11; i <= 15; i++)
            rs[i] = 4;

        for (int i = 16; i <= 20; i++)
            rs[i] = 3;

        for (int i = 21; i <= 25; i++)
            rs[i] = 2;

        String s = in.next();

        int n = s.length();

        int cr = 25;
        int cs = 0;
        int cw = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'W') {
                cw++;
                if (cr >= 6 && cw >= 3) {
                    cs += 2;
                } else {
                    cs++;
                }

                if (cr >= 1 && cs > rs[cr]) {
                    cr--;
                    cs -= rs[cr + 1];
                }
            } else {
                if (cr <= 20 && cr >= 1) {
                    cs--;
                }

                if (cr >= 20) {
                    if (cs < 0)
                        cs = 0;
                } else if (cr >= 1) {
                    if (cs < 0) {
                        cr++;
                        cs = rs[cr] - cs;
                    }
                }

                cw = 0;
            }
        }

        if (cr == 0) {
            out.println("Legend");
        } else {
            out.println(cr);
        }
    }
}
