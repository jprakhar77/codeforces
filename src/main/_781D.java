package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.BitSet;

public class _781D {
    class BitSetMatrix implements Cloneable {
        int n;
        int m;

        BitSet[] rows;
        BitSet[] columns;

        public BitSetMatrix(int n, int m) {
            this.n = n;
            this.m = m;
            this.rows = new BitSet[n];
            this.columns = new BitSet[m];
            for (int i = 0; i < n; i++) {
                this.rows[i] = new BitSet(m);
            }
            for (int i = 0; i < m; i++) {
                this.columns[i] = new BitSet(n);
            }
        }

        void setBit(int i, int j) {
            rows[i].set(j);
            columns[j].set(i);
        }

        void clearBit(int i, int j) {
            rows[i].clear(j);
            columns[j].clear(i);
        }

        BitSetMatrix leftMultiply(BitSetMatrix multiplier) {
            if (multiplier.m != n)
                throw new RuntimeException();

            int rn = multiplier.n;
            int rm = m;
            BitSetMatrix result = new BitSetMatrix(rn, rm);

            for (int i = 0; i < rn; i++) {
                for (int j = 0; j < rm; j++) {
                    BitSet row = (BitSet) multiplier.rows[i].clone();
                    row.and(columns[j]);
                    int curVal = row.isEmpty() ? 0 : 1;
                    if (curVal > 0)
                        result.setBit(i, j);
                }
            }

            return result;
        }

        BitSetMatrix leftMultiplyInplace(BitSetMatrix multiplier) {
            if (multiplier.m != n)
                throw new RuntimeException();

            int rn = multiplier.n;
            int rm = m;
            BitSetMatrix result = new BitSetMatrix(rn, rm);

            for (int i = 0; i < rn; i++) {
                for (int j = 0; j < rm; j++) {
                    columns[j].and(multiplier.rows[i]);
                    int curVal = columns[j].isEmpty() ? 0 : 1;
                    if (curVal > 0)
                        result.setBit(i, j);
                }
            }

            return result;
        }

        boolean isZero() {
            for (int i = 0; i < n; i++) {
                if (!rows[i].isEmpty())
                    return false;
            }
            return true;
        }

        protected BitSetMatrix clone() {
            BitSetMatrix clone = new BitSetMatrix(n, m);

            for (int i = 0; i < n; i++) {
                clone.rows[i] = (BitSet) rows[i].clone();
            }
            for (int i = 0; i < m; i++) {
                clone.columns[i] = (BitSet) columns[i].clone();
            }
            return clone;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        BitSetMatrix a = new BitSetMatrix(n, n);
        BitSetMatrix b = new BitSetMatrix(n, n);

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int t = in.nextInt();

            if (t == 0) {
                a.setBit(u, v);
            } else {
                b.setBit(u, v);
            }
        }

        if (a.isZero()) {
            out.println(0);
            return;
        }

        BitSetMatrix[] ba = new BitSetMatrix[61];
        BitSetMatrix[] aa = new BitSetMatrix[61];

        ba[0] = b;
        aa[0] = a;

        int i;
        for (i = 1; i <= 60; i++) {
            aa[i] = ba[i - 1].leftMultiply(aa[i - 1]);
            if (aa[i].isZero())
                break;
            ba[i] = aa[i - 1].leftMultiply(ba[i - 1]);
        }


        long ans = 1l << (i - 1);
        BitSetMatrix cm = aa[i - 1];
        boolean isb = true;
        for (int j = i - 2; j >= 0; j--) {
            if (isb) {
                BitSetMatrix nm = ba[j].leftMultiplyInplace(cm);
                if (!nm.isZero()) {
                    cm = nm;
                    ans += (1l << j);
                    isb = !isb;
                }
            } else {
                BitSetMatrix nm = aa[j].leftMultiplyInplace(cm);
                if (!nm.isZero()) {
                    cm = nm;
                    ans += (1l << j);
                    isb = !isb;
                }
            }
            if (ans > 1e18)
                break;
        }

        if (ans > 1e18) {
            out.println(-1);
            return;
        }

        out.println(ans);
    }
}
