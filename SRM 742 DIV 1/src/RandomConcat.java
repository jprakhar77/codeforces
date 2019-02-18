public class RandomConcat {

    class KmpString {
        String p;
        int m;
        int[] f;

        public KmpString(String p) {
            super();
            this.p = p;
            this.m = p.length();
            this.f = new int[m];
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

        int matches(String s) {

            int n = s.length();
            int matches = 0;

            int j = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == p.charAt(j)) {
                    j++;
                    if (j == m) {
                        matches++;
                        j = f[j - 1] + 1;
                    }
                } else {
                    while (j > 0 && p.charAt(j) != s.charAt(i)) {
                        j = f[j - 1] + 1;
                    }

                    if (p.charAt(j) == s.charAt(i)) {
                        j++;
                    }
                }
            }

            return matches;
        }

        class res {
            int matches;
            int still;

            public res(int matches, int still) {
                this.matches = matches;
                this.still = still;
            }
        }

        res match(String s, int already) {

            int n = s.length();
            int matches = 0;

            int j = already;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == p.charAt(j)) {
                    j++;
                    if (j == m) {
                        matches++;
                        j = f[j - 1] + 1;
                    }
                } else {
                    while (j > 0 && p.charAt(j) != s.charAt(i)) {
                        j = f[j - 1] + 1;
                    }

                    if (p.charAt(j) == s.charAt(i)) {
                        j++;
                    }
                }
            }

            return new res(matches, j);
        }
    }

    long[][] dp;
    long[][] dp2;

    public double expectation(String[] pieces, String needle) {
        int n = pieces.length;
        int nl = needle.length();

        dp = new long[1 << n][nl];
        dp2 = new long[1 << n][nl];

        for (int i = 0; i < (1 << n); i++) {
            for (int j = 1; j < nl; j++) {
                dp[i][j] = -1;
            }
        }

        dp2[0][0] = 1;
//        for (int i = 0; i < (1 << n); i++) {
//            dp2[i][0] = 1;
//        }

        KmpString kmp = new KmpString(needle);
        kmp.fail();

        for (int i = 1; i < (1 << n); i++) {
            for (int j = 0; j < nl; j++) {
                for (int k = 0; k < n; k++) {
                    if (((1 << k) & i) != 0) {
                        int pre = (i & (~(1 << k)));

                        if (dp[pre][j] >= 0) {
                            KmpString.res cres = kmp.match(pieces[k], j);
                            if (dp[i][cres.still] == -1) {
                                dp[i][cres.still] = 0;
                            }
                            dp2[i][cres.still] += dp2[pre][j];
                            dp[i][cres.still] += (dp[pre][j] + dp2[pre][j] * cres.matches);
                        }
                    }
                }
            }
        }

        double ans = 0;

        for (int j = 0; j < nl; j++) {
            if (dp[(1 << n) - 1][j] >= 0)
                ans += dp[(1 << n) - 1][j];
        }

        double fac = 1;

        for (int i = 2; i <= n; i++) {
            fac *= i;
        }
        ans /= fac;

        return ans;
    }
}
