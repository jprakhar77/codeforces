package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class _1029D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long k = in.nextInt();

        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        Map[] sm = new Map[10];

        for (int i = 0; i < 10; i++) {
            sm[i] = new HashMap<Long, Integer>();
        }

        for (int i = 0; i < n; i++) {
            int sz = calsize(a[i]);

            sm[sz - 1].merge(a[i] % k, 1, (x, y) -> (int) x + (int) y);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int sz = calsize(a[i]);
            long cnum = a[i] % k;
            for (int j = 1; j <= 10; j++) {
                cnum *= 10;
                long cmod = cnum % k;
                cnum = cmod;
                ans += (int) sm[j - 1].getOrDefault(k - cmod, 0);
                if (cmod == 0) {
                    ans += (int) sm[j - 1].getOrDefault(0l, 0);
                }
                if (j == sz && cmod + (a[i] % k) == k) {
                    ans--;
                }
                if (j == sz && cmod == 0 && (a[i] % k) == 0) {
                    ans--;
                }
            }
        }

        out.println(ans);
    }

    int calsize(long num) {
        int sz = 0;

        while (num > 0) {
            num /= 10;
            sz++;
        }

        return sz;
    }
}
