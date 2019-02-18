package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BGameWithModulo {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        while ((in.next()).equals("start")) {
            out.println("? 0 1");
            out.flush();
            char ch = in.nextCharacter();

            if (ch == 'x') {
                out.println("! 1");
                out.flush();
                continue;
            }

            int lo = 1;
            int hi = -1;

            while (true) {
                out.println("? " + lo + " " + (2 * lo));
                out.flush();

                ch = in.nextCharacter();

                if (ch == 'x') {
                    hi = 2 * lo;
                    break;
                } else {
                    lo *= 2;
                }
            }

            if (hi > 1000000000) {
                hi = 1000000000;
            }

            while (lo + 1 < hi) {
                int mid = (lo + hi) / 2;

                out.println("? " + lo + " " + mid);
                out.flush();

                ch = in.nextCharacter();

                if (ch == 'x') {
                    hi = mid;
                } else {
                    lo = mid;
                }
            }

            out.println("! " + (lo + 1));
            out.flush();
        }
    }
}
