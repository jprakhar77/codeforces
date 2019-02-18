package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CFamilDoorAndBrackets {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int d = n - m;

        String s = in.next();

        int[][] dpf = new int[d + 1][d + 1];

        dpf[0][0] = 1;

        for (int i = 1; i <= d; i++) {
            for (int j = 0; j <= i; j++) {
                //(
                if (j > 0)
                    dpf[i][j] += dpf[i - 1][j - 1];

                //)
                if (j < d)
                    dpf[i][j] += dpf[i - 1][j + 1];

                dpf[i][j] %= mod;
            }
        }

        int[][] dpb = new int[d + 1][d + 1];

        dpb[0][0] = 1;

        for (int i = 1; i <= d; i++) {
            for (int j = 0; j <= i; j++) {
                //(
                if (j < d)
                    dpb[i][j] += dpb[i - 1][j + 1];

                if (j > 0)
                    dpb[i][j] += dpb[i - 1][j - 1];

                dpb[i][j] %= mod;
            }
        }

        int o = 0;
        int mino = 0;

        for (int i = 0; i < m; i++) {
            if (s.charAt(i) == '(') {
                o++;
            } else {
                o--;

                if (o < mino) {
                    mino = o;
                }
            }
        }

        long ans = 0;
        for (int ps = 0; ps <= d; ps++) {
            int qs = d - ps;

            for (int pos = 0; pos <= ps; pos++) {
                if (pos >= -mino && pos + o >= 0 && pos + o <= d) {
                    long ca = 1;

                    ca *= dpf[ps][pos];
                    ca *= dpb[qs][pos + o];

                    ca %= mod;

                    ans += ca;
                    ans %= mod;
                }
            }
        }

        out.println(ans);
    }

    int mod = (int) 1e9 + 7;
}
