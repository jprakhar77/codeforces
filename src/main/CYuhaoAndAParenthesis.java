package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CYuhaoAndAParenthesis {
    int max = (int) 5e5;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] pos = new int[max + 1];
        int[] neg = new int[max + 1];

        int eq = 0;

        for (int i = 0; i < n; i++) {
            String s = in.next();

            int cn = s.length();

            int o = 0;

            boolean po = false;
            boolean ne = false;
            int maxneg = 0;
            for (int j = 0; j < cn; j++) {
                char ch = s.charAt(j);

                if (ch == '(') {
                    o++;
                } else {
                    o--;
                    if (o < 0) {
                        maxneg = Math.max(maxneg, -o);
                    }
                    if (j == 0) {
                        ne = true;
                    }
                }
            }

            if (o == 0) {
                if (maxneg == 0) {
                    eq++;
                }
            } else if (o > 0) {
                if (maxneg == 0) {
                    pos[o]++;
                }
            } else {
                if (maxneg <= -o) {
                    neg[-o]++;
                }
            }
        }

        long ans = eq / 2;

        for (int i = 0; i <= max; i++) {
            ans += Math.min(pos[i], neg[i]);
        }

        out.println(ans);

    }
}
