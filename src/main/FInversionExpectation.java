package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class FInversionExpectation {
    class BinaryIndexedTree {
        int n;
        long[] tree;

        int mod = 998244353;

        public BinaryIndexedTree(int n) {
            super();
            this.n = n;
            this.tree = new long[n + 1];
        }

        public long sum(int idx) {
            long sum = 0;

            while (idx > 0) {
                sum += tree[idx];
                sum %= mod;
                idx -= (idx & -idx);
            }

            return sum;
        }

        public void update(int idx, int v) {
            while (idx <= n) {
                tree[idx] += v;
                tree[idx] %= mod;
                idx += (idx & -idx);
            }
        }

        public long sum(int l, int r) {
            long val = sum(r);

            if (l > 0) {
                val -= sum(l - 1);
                val %= mod;
            }

            if (val < 0)
                val += mod;

            return val;
        }

        public long value(int i) {
            return sum(i, i);
        }
    }

    int mod = 998244353;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] p = in.nextIntArray(n);

        Set<Integer> s = new HashSet<>();

        for (int i = 1; i <= n; i++)
            s.add(i);

        for (int val : p) {
            s.remove(val);
        }

        int tm = s.size();

        BinaryIndexedTree bit1 = new BinaryIndexedTree(n);
        BinaryIndexedTree bit2 = new BinaryIndexedTree(n);

        BinaryIndexedTree bit3 = new BinaryIndexedTree(n);

        for (int val : s) {
            bit2.update(val, 1);
        }


        int[] b = new int[n + 1];

        int cm = 0;
        for (int i = 1; i <= n; i++) {
            if (s.contains(i)) {
                cm++;
            } else {
                b[i] = cm;
            }
        }

        long ans = 0;

        cm = 0;

        long tmi = pow(tm, mod - 2, mod);

        for (int i = 0; i < n; i++) {
            if (p[i] != -1) {
                long a1 = bit1.sum(p[i], n);
                long a2 = bit2.sum(p[i], n);

                a2 *= cm;
                a2 %= mod;

                a2 *= tmi;
                a2 %= mod;

                ans += a1;
                ans += a2;

                ans %= mod;

                bit1.update(p[i], 1);
                bit3.update(p[i], b[p[i]]);
            } else {
                long a3 = bit3.sum(p[i], n);

                a3 *= tmi;
                a3 %= mod;

                ans += a3;
                ans %= mod;
                cm++;
            }
        }

        long ex = s.size();

        ex *= (s.size() - 1);

        ex %= mod;

        ex *= pow(4, mod - 2, mod);
        ex %= mod;

        ans += ex;

        ans %= mod;

        out.println(ans);
    }

    long pow(long a, long p, int mod) {
        if (p == 0) {
            return 1;
        }

        long t = pow(a, p / 2, mod);

        if (p % 2 != 0) {
            return (((t * t) % mod) * a) % mod;
        } else {
            return (t * t) % mod;
        }
    }
}
