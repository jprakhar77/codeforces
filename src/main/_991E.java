package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _991E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();

        long cn = n;

        int[] h = new int[10];

        while (cn > 0) {
            h[(int) (cn % 10)]++;
            cn /= 10;
        }

        int[] h1 = new int[10];

//        int cnt = 0;
//        for (int i = 0; i < 10; i++) {
//            if (h[i] > 0) {
//                h1[i] = 1;
//                cnt++;
//            }
//        }
//
//        ans = 0;
//
//        long ca = 1;
//        if (h[0] > 0) {
//            ca = cnt - 1;
//        } else {
//            ca = cnt;
//        }
//
//        for (int i = cnt - 1; i >= 1; i--) {
//            ca *= i;
//        }
//
//        ans += ca;

        fac[0] = 1;

        for (int i = 1; i <= 19; i++) {
            fac[i] = i * fac[i - 1];
        }

        bt(0, new int[10], h);

        out.println(ans);
    }

    long ans = 0;

    void bt(int i, int[] ch, int[] h) {
        if (i == 10) {
            ans += cal(ch);
            return;
        }

        if (h[i] == 0) {
            bt(i + 1, ch, h);
            return;
        }

        for (int j = 1; j <= h[i]; j++) {
            ch[i]++;
            bt(i + 1, ch, h);
        }

        ch[i] = 0;
    }

    long[] fac = new long[20];


    long cal(int[] h) {

        int cnt = 0;
        for (int i = 0; i < 10; i++) {
            if (h[i] > 0) {
                cnt += h[i];
            }
        }

        long ca = 1;

        if (h[0] > 0) {
            ca = cnt - h[0];
        } else {
            ca = cnt;
        }

        for (int i = cnt - 1; i >= 1; i--) {
            ca *= i;
        }

        for (int i = 0; i < 10; i++) {
            if (h[i] > 1) {
                ca /= fac[h[i]];
            }
        }

        return ca;
    }
}
