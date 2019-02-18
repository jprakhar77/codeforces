package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class CPrefixesAndSuffixes {
    class ss {
        String s;
        int i;

        public ss(String s, int i) {
            this.s = s;
            this.i = i;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        ss[] sa = new ss[2 * n - 2];

        for (int i = 0; i < 2 * n - 2; i++) {
            sa[i] = new ss(in.next(), i);
        }

        Arrays.sort(sa, (s1, s2) -> s1.s.length() - s2.s.length());

        String prefix = sa[2 * n - 3].s;
        String suffix = sa[2 * n - 4].s;

        char[] ans = new char[2 * n - 2];

        for (int i = 0; i < 2 * n - 2; i++) {
            ans[i] = 'S';
        }

        boolean poss = true;
        for (int i = 2 * n - 3; i >= 0; i -= 2) {
            if (prefix.startsWith(sa[i].s) && suffix.endsWith(sa[i - 1].s)) {
                ans[sa[i].i] = 'P';
            } else if (prefix.startsWith(sa[i - 1].s) && suffix.endsWith(sa[i].s)) {
                ans[sa[i - 1].i] = 'P';
            } else {
                poss = false;
                break;
            }
        }

        if (poss) {
            out.println(ans);
        } else {
            prefix = sa[2 * n - 4].s;
            suffix = sa[2 * n - 3].s;

            ans = new char[2 * n - 2];

            for (int i = 0; i < 2 * n - 2; i++) {
                ans[i] = 'S';
            }

            poss = true;
            for (int i = 2 * n - 3; i >= 0; i -= 2) {
                if (prefix.startsWith(sa[i].s) && suffix.endsWith(sa[i - 1].s)) {
                    ans[sa[i].i] = 'P';
                } else if (prefix.startsWith(sa[i - 1].s) && suffix.endsWith(sa[i].s)) {
                    ans[sa[i - 1].i] = 'P';
                } else {
                    poss = false;
                    break;
                }
            }

            out.println(ans);
        }

    }
}
