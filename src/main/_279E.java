package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _279E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String st = in.next();

        StringBuilder s = new StringBuilder(st);

        int n = s.length();

        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                int j = i;
                while (j >= 0 && s.charAt(j) == '1') {
                    j--;
                }

                int sz = i - j;

                if (sz == 1) {
                    ans++;
                } else {
                    ans++;
                    if (j >= 0)
                        s.setCharAt(j, '1');
                    else ans++;
                    i = j + 1;
                }
            }
        }

        out.println(ans);
    }
}
