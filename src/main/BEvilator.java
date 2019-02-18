package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BEvilator {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();

        long ans = 0;
        for (int i = 0; i < n; i++)
        {
            if (s.charAt(i) == 'U')
            {
                ans += (n - 1 - i);
                ans += 2 * i;
            }else {
                ans += i;
                ans += 2 * (n - 1 - i);
            }
        }

        out.println(ans);
    }
}
