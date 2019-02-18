package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ALunarNewYearAndCrossCounting {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.ni();

        String[] g = in.ng(n);

        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (g[i].charAt(j) == 'X') {
                    boolean poss = true;
                    for (int x = -1; x <= 1; x += 2) {
                        for (int y = -1; y <= 1; y += 2) {
                            if (g[i + x].charAt(j + y) != 'X') {
                                poss = false;
                            }
                        }
                    }

                    if (poss)
                    {
                        ans++;
                    }
                }
            }
        }

        out.println(ans);
    }
}
