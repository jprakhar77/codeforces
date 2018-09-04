package library;

import java.util.BitSet;

public class BitsetMatrix implements Cloneable {
    int n;
    int m;

    BitSet[] rows;
    BitSet[] columns;

    public BitsetMatrix(int n, int m) {
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

    BitsetMatrix leftMultiply(BitsetMatrix multiplier) {
        if (multiplier.m != n)
            throw new RuntimeException();

        int rn = multiplier.n;
        int rm = m;
        BitsetMatrix result = new BitsetMatrix(rn, rm);

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

    BitsetMatrix leftMultiplyInplace(BitsetMatrix multiplier) {
        if (multiplier.m != n)
            throw new RuntimeException();

        int rn = multiplier.n;
        int rm = m;
        BitsetMatrix result = new BitsetMatrix(rn, rm);

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

    protected BitsetMatrix clone() {
        BitsetMatrix clone = new BitsetMatrix(n, m);

        for (int i = 0; i < n; i++) {
            clone.rows[i] = (BitSet) rows[i].clone();
        }
        for (int i = 0; i < m; i++) {
            clone.columns[i] = (BitSet) columns[i].clone();
        }
        return clone;
    }
}