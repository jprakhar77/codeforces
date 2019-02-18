package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CNiceGarland {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        String s = in.next();

        char[] col = {'R', 'G', 'B'};

        int ans = n + 1;
        String anss = null;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (i == k)
                    continue;

                int l = -1;
                for (int j = 0; j < 3; j++) {
                    if (j != i && j != k) {
                        l = j;
                        break;
                    }
                }

                char[] cc = {col[i], col[k], col[l]};

                int ca = 0;
                StringBuilder cans = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    char tch = cc[j % 3];

                    if (s.charAt(j) != tch) {
                        ca++;
                    }

                    cans.append(tch);
                }

                if (ca < ans) {
                    ans = ca;
                    anss = cans.toString();
                }
            }
        }

        out.println(ans);
        out.println(anss);
    }
}
