package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ENegativeDoubling {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        long start = System.currentTimeMillis();

        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        //pos sta
        int lo = 0;
        int hi = n;

        long ans = inf;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            long val1 = solve(a, mid, n);

            if (mid < hi) {
                long val2 = solve(a, mid + 1, n);

                if (val1 < val2) {
                    ans = Math.min(ans, val1);
                    hi = mid - 1;
                } else {
                    ans = Math.min(ans, val2);
                    lo = mid + 2;
                }
            } else {
                ans = Math.min(ans, val1);
                break;
            }
        }


        //Random ran = new Random();

        int st = 0;
        int en = n;
        while ((System.currentTimeMillis() - start) < 1900 && st <= en) {
            //int ind = ran.nextInt(n + 1);

            long val1 = solve(a, st, n);

            long val2 = solve(a, en, n);

            ans = Math.min(ans, val1);
            ans = Math.min(ans, val2);

            st++;
            en--;
        }

        out.println(ans);
    }

    long inf = Long.MAX_VALUE;

    long solve(int[] a, int st, int n) {

        //long[] pow = new long[300000];

        long ans = 0;
        if (st < n) {
            int ind = 0;
            //pow[0] = a[st];
            for (int i = st + 1; i < n; i++) {
                if (a[i] == a[i - 1]) {
                } else if (a[i] > a[i - 1]) {
                    long num = a[i];

                    int exp = 0;

                    while (num >= a[i - 1]) {
                        num /= 2;
                        exp++;
                    }

                    exp--;

                    ind -= exp;

                } else {
                    long num = a[i];

                    int exp = 0;
                    while (num < a[i - 1]) {
                        num *= 2;
                        exp++;
                    }

                    ind += exp;
                }

                if (ind < 0)
                    ind = 0;

                if (ind % 2 == 1)
                    ind++;

                ans += ind;
            }
        }

        if (st > 0) {
            ans++;
            int ind = 1;
            for (int i = st - 2; i >= 0; i--) {
                if (a[i] == a[i + 1]) {
                } else if (a[i] > a[i + 1]) {
                    long num = a[i];

                    int exp = 0;

                    while (num >= a[i + 1]) {
                        num /= 2;
                        exp++;
                    }

                    exp--;

                    ind -= exp;

                } else {
                    long num = a[i];

                    int exp = 0;
                    while (num < a[i + 1]) {
                        num *= 2;
                        exp++;
                    }

                    ind += exp;
                }

                if (ind < 0)
                    ind = 0;

                if (ind % 2 == 0)
                    ind++;

                ans += ind;
            }
        }

        return ans;
    }
}
