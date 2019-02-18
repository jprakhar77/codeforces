package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DMatchMatching {
    class state {
        int[] val = new int[10];

        public state(int num) {
            val[num] = 1;
        }

        public state(int[] val) {
            this.val = val;
        }

        public state() {
        }

        state add(state state) {
            state ns = new state(state.val);

            for (int j = 1; j < 10; j++) {
                ns.val[j] += val[j];
            }

            return ns;
        }
    }


    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        int[] a = in.nextIntArray(m);

        boolean[] p = new boolean[10];

        for (int i = 0; i < m; i++) {
            p[a[i]] = true;
        }

        int[] match = {0, 2
                ,
                5
                ,
                5
                ,
                4
                ,
                5
                ,
                6
                ,
                3
                ,
                7
                ,
                6};

        state[] dp = new state[n + 1];

        dp[0] = new state();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < 10; j++) {
                if (p[j] && i >= match[j] && dp[i - match[j]] != null) {
                    dp[i] = max(dp[i - match[j]].add(new state(j)), dp[i]);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int j = 9; j >= 1; j--) {
            char ch = (char) (j + '0');
            for (int i = 0; i < dp[n].val[j]; i++) {
                ans.append(ch);
            }
        }

        out.println(ans);
    }

    state max(state s1, state s2) {
        if (s1 == null)
            return s2;

        if (s2 == null)
            return s1;
        int[] v1 = s1.val;
        int[] v2 = s2.val;

        int sum1 = 0;
        int sum2 = 0;

        for (int val : v1) {
            sum1 += val;
        }

        for (int val : v2) {
            sum2 += val;
        }

        if (sum1 > sum2) {
            return s1;
        } else if (sum2 > sum1) {
            return s2;
        } else {
            for (int j = 9; j > 0; j--) {
                if (v1[j] > v2[j]) {
                    return s1;
                } else if (v2[j] > v1[j]) {
                    return s2;
                }
            }
            return s1;
        }
    }
}
