package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class APawnChess {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String[] g = new String[8];

        for (int i = 0; i < 8; i++) {
            g[i] = in.next();
        }

        int n = 8;
        int wm = 10;
        int bm = 10;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i].charAt(j) == 'W') {
                    boolean poss = true;
                    for (int k = i - 1; k >= 0; k--) {
                        if (g[k].charAt(j) != '.') {
                            poss = false;
                            break;
                        }
                    }
                    if (poss) {
                        wm = Math.min(wm, i);
                    }
                }

                if (g[i].charAt(j) == 'B') {

                    boolean poss = true;
                    for (int k = i + 1; k < n; k++) {
                        if (g[k].charAt(j) != '.') {
                            poss = false;
                            break;
                        }
                    }
                    if (poss) {
                        bm = Math.min(bm, n - 1 - i);
                    }
                }
            }
        }

        if (wm <= bm) {
            out.println("A");
        } else {
            out.println("B");
        }
    }
}
