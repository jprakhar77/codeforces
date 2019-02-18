package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class GoodSubsequences {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            String s = in.ns();

            int[] h = new int[26];

            int n = s.length();
            for (int i = 0; i < n; i++) {
                h[s.charAt(i) - 'a']++;
            }

            long ans = 1;

            for (int i = 0; i < 26; i++) {
                if (h[i] > 0) {
                    ans *= h[i];
                    ans %= mod;
                }
            }

            out.println(ans);
        }
    }

    int mod = 1000000007;
}
