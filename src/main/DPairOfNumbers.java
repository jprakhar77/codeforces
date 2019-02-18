package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class DPairOfNumbers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        n = in.nextInt();

        a = in.nextIntArray(n);

        log = 0;

        while ((1 << log) <= n) {
            log++;
        }

        stmin = new int[n][log];
        stgcd = new int[n][log];

        for (int i = n - 1; i >= 0; i--) {
            stmin[i][0] = stgcd[i][0] = a[i];
            for (int j = 1; j < log; j++) {
                stmin[i][j] =
                        Math.min(stmin[i][j - 1],
                                stmin[Math.min(i + (1 << (j - 1)), n - 1)][j - 1]);
                stgcd[i][j] =
                        gcd(stgcd[i][j - 1],
                                stgcd[Math.min(i + (1 << (j - 1)), n - 1)][j - 1]);
            }
        }

        int lo = 1;
        int hi = n;

        int ans = lo;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            boolean poss = solve(mid);

            if (poss) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        int cl = 0;

        while ((1 << cl) <= ans) {
            cl++;
        }

        cl--;

        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < n - ans + 1; i++) {
            int j = i + ans - 1;

            int gcd = querygcd(i, j, cl);
            int min = querymin(i, j, cl);

            if (gcd == min) {
                l.add(i + 1);
            }
        }

        out.println(l.size() + " " + (ans - 1));
        for (int i = 0; i < l.size(); i++) {
            out.print(l.get(i) + " ");
        }
    }

    int[][] stmin;
    int[][] stgcd;

    int[] a;
    int n;
    int log;

    int querygcd(int l, int r, int cl) {
        return gcd(stgcd[l][cl], stgcd[r - (1 << cl) + 1][cl]);
    }

    int querymin(int l, int r, int cl) {
        return Math.min(stmin[l][cl], stmin[r - (1 << cl) + 1][cl]);
    }

    boolean solve(int size) {
        int cl = 0;

        while ((1 << cl) <= size) {
            cl++;
        }

        cl--;

        for (int i = 0; i < n - size + 1; i++) {
            int j = i + size - 1;

            int gcd = querygcd(i, j, cl);
            int min = querymin(i, j, cl);

            if (gcd == min) {
                return true;
            }
        }

        return false;
    }

    int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
