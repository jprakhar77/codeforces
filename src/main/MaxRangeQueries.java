package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class MaxRangeQueries {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        int[] l = new int[100000];
        int[] r = new int[100000];

        int[] a = new int[100001];
        int[] ka = new int[100001];
        int[] k1a = new int[100001];
        while (t-- > 0) {
            int n = in.nextInt();

            int k = in.nextInt();

            Arrays.fill(a, 0);
            Arrays.fill(ka, 0);
            Arrays.fill(k1a, 0);
            for (int i = 0; i < n; i++) {
                l[i] = in.nextInt();
                r[i] = in.nextInt();

                l[i]--;
                r[i]--;

                a[l[i]]++;
                a[r[i] + 1]--;
            }

            if (a[0] == k) {
                ka[0]++;
            } else if (a[0] == k + 1) {
                k1a[0]++;
            }
            int num = a[0];
            for (int i = 1; i < max; i++) {
                num += a[i];

                ka[i] = ka[i - 1];
                k1a[i] = k1a[i - 1];
                if (num == k) {
                    ka[i]++;
                } else if (num == k + 1) {
                    k1a[i]++;
                }
            }

            int ans = 0;

            for (int i = 0; i < n; i++) {
                int ca = ka[max - 1];
                ca += cal1(l[i], r[i], k1a);
                ca -= cal(l[i], r[i], ka);

                ans = Math.max(ca, ans);
            }

            out.println(ans);
        }
    }

    int cal1(int l, int r, int[] k1a) {
        int val = k1a[r];

        if (l > 0) {
            val -= k1a[l - 1];
        }

        return val;
    }

    int cal(int l, int r, int[] ka) {
        int val = ka[r];

        if (l > 0) {
            val -= ka[l - 1];
        }

        return val;
    }

    int max = 100000;
}
