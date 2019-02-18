package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class EAlmostRegularBracketSequence {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        String s = in.next();

        int o = 0;

        int[] oa = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                o++;
                oa[i] = o;
            } else {
                o--;
                oa[i] = o;
            }
        }

        int[] oam = new int[n];

        oam[n - 1] = oa[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            oam[i] = Math.min(oam[i + 1], oa[i]);
        }

        if (o != -2 && o != 2) {
            out.print(0);
            return;
        }

        if (o == 2) {
            o = 0;

            int ans = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '(') {
                    if (o > 0 && (i == n - 1 || oam[i + 1] >= 2)) {
                        ans++;
                    }
                    o++;
                } else {
                    o--;
                    if (o < 0) {
                        out.print(ans);
                        return;
                    }
                }
            }

            out.print(ans);
        } else {
            o = 0;

            int ans = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '(') {
                    o++;
                } else {
                    if (i < n - 1 && oam[i + 1] >= -2) {
                        ans++;
                    }
                    o--;
                    if (o < 0) {
                        out.print(ans);
                        return;
                    }
                }
            }

            out.print(ans);
        }
    }
}
