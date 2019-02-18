package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class TPermutation {
    class ele {
        char ch;
        int cn;

        public ele(char ch, int cn) {
            this.ch = ch;
            this.cn = cn;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        String s = in.next();

        long[][] dp = new long[n][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            int max = n - i + 1;

            long[] suf = in.calculateSuffixSum(dp[i - 1], mod);
            long[] pre = in.calculatePrefixSum(dp[i - 1], mod);

            for (int j = 1; j <= max; j++) {
                if (s.charAt(i - 1) == '>') {
                    if (j < max)
                        dp[i][j] = suf[j + 1];
                } else {
                    if (j > 1)
                        dp[i][j - 1] = pre[j - 1];
                }
            }
        }

        out.println(dp[n - 1][1]);

//        List<ele> l = new ArrayList<>();
//
//        int cc = 0;
//        for (int i = 0; i < n - 1; i++) {
//            if (i > 0 && s.charAt(i - 1) != s.charAt(i)) {
//                l.add(new ele(s.charAt(i - 1), cc));
//                cc = 1;
//            } else {
//                cc++;
//            }
//        }
//
//        l.add(new ele(s.charAt(n - 2), cc));
//
//        long ans = 1;
//
//        ele ne = l.get(0);
//        ele ce = new ele('<', 1);
//
//        int[][] ncr = nCr(n, n, mod);
//
//        int dc = 0;
//        int nei = 0;
//        do {
//            int rc = n - dc;
//
//            long ca = 0;
//            if (ce.ch == '<') {
//                for (int i = 1; i <= rc; i++) {
//                    int lessc = i - 1;
//
//                    if (lessc >= ce.cn - 1 + ne.cn) {
//                        ca += ncr[lessc][ce.cn - 1];
//                        ca %= mod;
//                    }
//                }
//            } else {
//                for (int i = rc; i >= 1; i--) {
//                    int morec = rc - i;
//
//                    if (morec >= ce.cn - 1 + ne.cn) {
//                        ca += ncr[morec][ce.cn - 1];
//                        ca %= mod;
//                    }
//                }
//            }
//
//            dc += ce.cn;
//
//            ans *= ca;
//            ans %= mod;
//
//            ce = ne;
//            nei++;
//            if (nei < l.size()) {
//                ne = l.get(nei);
//            } else {
//                ne = null;
//            }
//
//        } while (ne != null);
//
//        out.println(ans);
    }

    int mod = 1000000007;

    int[][] nCr(int n, int r, int mod) {
        int[][] ncr = new int[n + 1][r + 1];

        ncr[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            ncr[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= r; j++) {
                ncr[i][j] = ncr[i - 1][j - 1] + ncr[i - 1][j];
                ncr[i][j] %= mod;
            }
        }

        return ncr;
    }
}
