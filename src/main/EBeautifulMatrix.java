package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class EBeautifulMatrix {

    class BinaryIndexedTree {
        int n;
        int[] tree;

        public BinaryIndexedTree(int n) {
            super();
            this.n = n;
            this.tree = new int[n + 1];
        }

        public int sum(int idx) {
            idx++;

            int sum = 0;

            while (idx > 0) {
                sum += tree[idx];
                idx -= (idx & -idx);
            }

            return sum;
        }

        public void update(int idx, int v) {
            idx++;

            while (idx <= n) {
                tree[idx] += v;
                idx += (idx & -idx);
            }
        }

        public int sum(int l, int r) {
            int val = sum(r);

            if (l > 0) {
                val -= sum(l - 1);
            }

            return val;
        }

        public int value(int i) {
            int val = sum(i);

            if (i > 0) {
                val -= sum(i - 1);
            }

            return val;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt() - 1;
            }
        }

        fac = new long[2001];

        fac[0] = 1;

        for (int i = 1; i <= 2000; i++) {
            fac[i] = (i * fac[i - 1]) % mod;
        }

        long[] rank = new long[n];

        rank[0] = calRank(a[0], n);

        long tot = calTot(n);
        for (int i = 1; i < n; i++) {
            rank[i] = calDel(a[i - 1], a[i], n);
        }

        long ans = rank[0];

        for (int i = 1; i < n; i++) {
            ans *= tot;
            ans %= mod;

            ans += rank[i];
            ans %= mod;
        }

        out.println(ans);
    }

    long[] fac;

    long calRank(int[] a, int n) {
        long rank = 0;

        BinaryIndexedTree bit = new BinaryIndexedTree(n);
        for (int i = 0; i < n; i++) {
            int num = a[i];

            int prev = 0;
            if (num > 0) {
                prev = num - bit.sum(num - 1);
            }

            if (prev > 0) {
                rank += prev * fac[n - 1 - i];
                rank %= mod;
            }

            bit.update(num, 1);
        }

        return rank;
    }

    int mod = 998244353;

    long[][] dp;

    long calTot(int n) {

        dp = new long[n + 1][n + 1];

        dp[0][0] = 1;
        dp[1][0] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (i > j) {
                    if (j > 0) {
                        dp[i][j] += j * dp[i - 1][j - 1];
                        dp[i][j] %= mod;
                    }
                    dp[i][j] += (i - j) * dp[i - 1][j];
                    dp[i][j] %= mod;
                } else {
                    dp[i][j] += (i - 1) * dp[i - 1][j - 2];
                    dp[i][j] %= mod;
                }
            }
        }

        return dp[n][n];
    }

    long calDel(int[] pa, int[] a, int n) {

        long rank = 0;

        BinaryIndexedTree sbit = new BinaryIndexedTree(n);
        BinaryIndexedTree nsbit = new BinaryIndexedTree(n);

        for (int i = 0; i < n; i++) {
            sbit.update(i, 1);
        }

        int totsame = n;

        for (int i = 0; i < n; i++) {
            int same = sbit.sum(a[i] - 1);
            int usame = nsbit.sum(a[i] - 1);

            if (pa[i] < a[i]) {
                int val = sbit.value(pa[i]);

                same -= val;
            }

            if (sbit.value(pa[i]) == 1) {
                if (totsame > 1)
                    rank += same * dp[n - 1 - i][totsame - 2];
                rank %= mod;
                if (totsame > 0)
                    rank += usame * dp[n - 1 - i][totsame - 1];

                sbit.update(pa[i], -1);
                nsbit.update(pa[i], 1);

                totsame--;
            } else {
                if (totsame > 0)
                    rank += same * dp[n - 1 - i][totsame - 1];
                rank %= mod;
                rank += usame * dp[n - 1 - i][totsame];
            }

            if (sbit.value(a[i]) == 1) {
                sbit.update(a[i], -1);
                totsame--;
            } else {
                nsbit.update(a[i], -1);
            }

            rank %= mod;
        }

        return rank;
    }

}
