package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _961E {
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
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n + 1];

        for (int i = 0; i < n; i++) {
            a[i + 1] = in.nextInt();
        }

        List[] bl = new List[n + 1];

        for (int i = 0; i <= n; i++) {
            bl[i] = new ArrayList();
        }

        for (int i = 1; i <= n; i++) {
            bl[Math.min(a[i], i - 1)].add(i - 1);
        }

        BinaryIndexedTree bit = new BinaryIndexedTree(n + 1);

        long ans = 0;
        for (int i = 0; i <= n; i++) {
            bit.update(Math.min(n, a[i]), 1);
            for (int j = 0; j < bl[i].size(); j++) {
                ans += bit.sum(n) - bit.sum((int) bl[i].get(j));
            }
        }

        out.println(ans);
    }
}
