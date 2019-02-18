package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class DChristmas {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long x = in.nextLong();

        dps[0] = dpp[0] = 1;

        for (int i = 1; i <= 50; i++) {
            dps[i] = 3 + 2 * dps[i - 1];
        }

        for (int i = 1; i <= 50; i++) {
            dpp[i] = 2 * dpp[i - 1] + 1;
        }

        out.println(rec(n, x));
    }

    long[] dps = new long[51];
    long[] dpp = new long[51];

    long rec(int n, long x) {

        if (n == 0) {
            if (x == 1) {
                return 1;
            } else {
                return 0;
            }
        }

        long ans = 0;
        long cv = x;

        cv--;

        long val = -1;

        if (cv > dps[n - 1]) {
            ans += dpp[n - 1];
            cv -= dps[n - 1];
        } else {
            ans += rec(n - 1, cv);
            return ans;
        }

        ans++;
        cv--;

        if (cv > dps[n - 1]) {
            ans += dpp[n - 1];
            cv -= dps[n - 1];
        } else {
            ans += rec(n - 1, cv);
            return ans;
        }

        int[] a = null;
        return ans;
    }

    class pair
    {
        int fi;
        int se;

        public pair(int fi, int se)
        {
            this.fi = fi;
            this.se = se;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            pair pair = (pair) o;
            return fi == pair.fi &&
                    se == pair.se;
        }

        @Override
        public int hashCode() {
            return Objects.hash(fi, se);
        }
    }
}

