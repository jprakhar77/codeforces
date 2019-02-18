package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class NicenessOfTheArrays {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int s = in.nextInt();

        int[] a = new int[n];

        in.readArray(a, n, 0);

        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                gcd[i][j] = gcd(i, j);
            }
        }

        int srem = s;

        int miss = 0;
        for (int val : a) {
            if (val != -1) {
                srem -= val;
            } else miss++;
        }

        int[] b = new int[51];
        HashMap<Integer, Integer> set = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (a[i] != -1) {
                set.merge(a[i], 1, (x, y) -> x + y);
            }
        }

        if (miss == 0 && srem != 0) {
            out.println(0);
            return;
        }

        if (srem < miss) {
            out.println(0);
            return;
        }

        int missrem = miss;
        ans = 0;
        bt(0, a, set, srem, n, missrem);

        out.println(ans);
    }

    long ans = 0;
    int mod = 1000000007;

    void bt(int i, int[] a, Map<Integer, Integer> b, int srem, int n, int missrem) {
        if (i == n) {
            ans += cal(b);
            ans %= mod;
            return;
        }

        if (a[i] != -1) {
            bt(i + 1, a, b, srem, n, missrem);
            return;
        }

        if (missrem == 1) {
            b.merge(srem, 1, (x, y) -> x + y);
            bt(n, a, b, 0, n, 0);
            remove(b, srem);
            return;
        }

        for (int j = 1; j <= srem - (missrem - 1); j++) {
            b.merge(j, 1, (x, y) -> x + y);

            bt(i + 1, a, b, srem - j, n, missrem - 1);

            remove(b, j);
        }
    }

    void remove(Map<Integer, Integer> b, int num)
    {
        b.merge(num, -1, (x, y) -> x + y);
        if (b.get(num) == 0) {
            b.remove(num);
        }
    }

    int cal(Map<Integer, Integer> b) {
        int ans = 0;
        for (int key1 : b.keySet()) {
            for (int key2 : b.keySet()) {
                if (key1 > key2)
                    continue;

                if (key1 == key2) {
                    int val = b.get(key1);

                    ans += ((val * (val - 1)) / 2) * key1;
                    continue;
                }

                ans += (b.get(key1) * b.get(key2) * gcd[key1][key2]);
            }
        }
        return ans;
    }

    int[][] gcd = new int[51][51];

    int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
