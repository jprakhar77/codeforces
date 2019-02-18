package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _900E {
    class KmpString {
        String s;
        String p;
        int n;
        int m;
        int[] f;

        boolean[] match;

        public KmpString(String s, String p) {
            super();
            this.s = s;
            this.p = p;
            this.n = s.length();
            this.m = p.length();
            this.f = new int[m];
            this.match = new boolean[n];
        }

        void fail() {
            f[0] = -1;

            for (int i = 1; i < m; i++) {
                int c = f[i - 1];

                f[i] = -1;
                while (c > -1) {
                    if (p.charAt(c + 1) == p.charAt(i)) {
                        f[i] = c + 1;
                        break;
                    } else {
                        c = f[c];
                    }
                }

                if (f[i] == -1) {
                    if (p.charAt(0) == p.charAt(i)) {
                        f[i] = 0;
                    }
                }
            }
        }

        int matches() {

            int matches = 0;

            int pques = 0;

            int j = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '?') {
                    pques++;
                } else {
                    pques = 0;
                }
                if (s.charAt(i) == '?' || s.charAt(i) == p.charAt(j)) {
                    j++;
                    if (j == m) {
                        matches++;
                        match[i] = true;
                        j = f[j - 1] + 1;
                        j = Math.min(Math.max(j, pques), m - 1);
                    }
                } else {
                    while (j > 0 && p.charAt(j) != s.charAt(i)) {
                        j = f[j - 1] + 1;
                    }

                    if (s.charAt(i) == '?' || p.charAt(j) == s.charAt(i)) {
                        j++;
                    }
                }
            }

            return matches;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        String s = in.next();

        int m = in.nextInt();

        int[] ap = new int[n];
        int[] bp = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '?') {
                if (s.charAt(i) == 'a') {
                    if (i % 2 == 0) {
                        ap[i] = 1;
                    } else {
                        bp[i] = 1;
                    }
                } else {
                    if (i % 2 == 1) {
                        ap[i] = 1;
                    } else {
                        bp[i] = 1;
                    }
                }
            }
        }

        ap = in.calculatePrefixSum(ap);
        bp = in.calculatePrefixSum(bp);

        boolean[] matchEnd = new boolean[n];

        for (int i = m - 1; i < n; i++) {
            int av = ap[i];
            int bv = bp[i];

            if (i - m >= 0) {
                av -= ap[i - m];
                bv -= bp[i - m];
            }

            if ((i - m + 1) % 2 == 0) {
                if (bv == 0) {
                    matchEnd[i] = true;
                }
            } else {
                if (av == 0) {
                    matchEnd[i] = true;
                }
            }
        }

        int[] matches = new int[n + 1];

        for (int i = m - 1; i < n; i++) {
            if (matchEnd[i]) {
                matches[i] = Math.max((i > 0) ? matches[i - 1] : 0, (i >= m) ? matches[i - m] + 1 : 1);
            } else {
                matches[i] = Math.max((i > 0) ? matches[i - 1] : 0, (i >= m) ? matches[i - m] : 0);
            }
        }

        int[] q = new int[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '?') {
                q[i] = 1;
            }
        }

        int[] qpre = new int[n];

        qpre[0] = q[0];

        for (int i = 1; i < n; i++) {
            qpre[i] = qpre[i - 1] + q[i];
        }

        int[] dp = new int[n];

        for (int i = m - 1; i < n; i++) {
            if (matchEnd[i]) {
                if (i - m < 0) {
                    dp[i] = qpre[i];
                } else {
                    if (matches[i] == matches[i - 1]) {
                        dp[i] = dp[i - 1];
                        dp[i] = Math.min(dp[i], dp[i - m] + qpre[i] - qpre[i - m]);
                    } else {
                        dp[i] = dp[i - m] + qpre[i] - qpre[i - m];
                    }
                }
            } else {
                if (i > 0)
                    dp[i] = dp[i - 1];
            }
        }

        out.println(dp[n - 1]);
    }
}
