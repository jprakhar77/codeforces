package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CInnaAndCandyBoxes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int w = in.nextInt();

        int[][] val = new int[k][n];
        int[][] valn = new int[k][n];

        String s = in.next();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                val[i % k][i]++;
            } else {
                valn[i % k][i]++;
            }
        }

        int[][] pre = new int[k][n];

        for (int i = 0; i < k; i++) {
            pre[i] = in.calculatePrefixSum(val[i]);
        }

        int[][] pren = new int[k][n];

        for (int i = 0; i < k; i++) {
            pren[i] = in.calculatePrefixSum(valn[i]);
        }

        while (w-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();

            l--;
            r--;

            int mod = l + k - 1;
            mod %= k;

            int ans = 0;
            for (int i = 0; i < k; i++) {
                if (i == mod) {
                    ans += cal(pren[i], l, r);
                } else {
                    ans += cal(pre[i], l, r);
                }
            }

            out.println(ans);
        }

    }

    int cal(int[] pre, int l, int r) {
        int ans = pre[r];

        if (l > 0) {
            ans -= pre[l - 1];
        }

        return ans;
    }
}
