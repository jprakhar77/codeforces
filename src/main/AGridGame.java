package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AGridGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int vp = 1;
        int hp = 1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                out.println("1 " + vp);
                vp++;
                if (vp > 4)
                    vp = 1;
            } else {
                out.println("3 " + hp);
                hp += 2;
                if (hp > 4)
                    hp = 1;
            }
        }
    }
}
