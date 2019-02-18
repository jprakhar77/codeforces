package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BSubstringRemoval {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        String s = in.next();

        int[] hs = new int[26];
        int[] he = new int[26];

        Arrays.fill(hs, -1);

        Set<Character> scs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            int chi = ch - 'a';
            if (hs[chi] == -1) {
                hs[chi] = i;
            }

            he[chi] = i;

            scs.add(ch);
        }

        if (scs.size() == 1) {
            out.print(((long) n * (n + 1)) / 2);
            return;
        }

        int[] hs2 = new int[26];
        int[] he2 = new int[26];

        Arrays.fill(hs2, n);

        for (int i = 0; i < 26; i++) {
            if (hs[i] != -1) {
                for (int j = 0; j < 26; j++) {
                    if (i != j && hs[j] != -1) {
                        hs2[i] = Math.min(hs[j], hs2[i]);
                        he2[i] = Math.max(he2[i], he[j]);
                    }
                }
            }
        }

        long ans = 0;
        long ex = 0;
        for (int i = 0; i < 26; i++) {
            int st = hs2[i];
            int en = he2[i];

            if (hs[i] == -1)
                continue;

            long sc = st + 1;
            long ec = n - en;

            ans += sc * ec;

            ans %= 998244353;

            if (sc * ec > 0) {
                ex++;
            }
        }

        if (ex > 1) {
            ans -= (ex - 1);
            ans %= 998244353;
        }

        if (ans < 0)
        {
            ans += 998244353;
        }

            out.print(ans);
    }
}
