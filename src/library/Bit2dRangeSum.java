package library;

/**
 * Call with 1-based indices
 */
public class Bit2dRangeSum {
    int r;
    int c;
    /**
     * 3rd Dimension: 0 -> add, 1 -> mul
     */
    int[][][] treeAdd;
    int[][][] treeMul;

    public Bit2dRangeSum(int r, int c) {
        super();
        this.r = r;
        this.c = c;
        this.treeAdd = new int[r + 1][c + 1][2];
        this.treeMul = new int[r + 1][c + 1][2];
    }

    public long sum2ndD(int tam, int r1, int c1) {

        int cc1 = c1;

        long add = 0;
        long mul = 0;

        while (cc1 > 0) {
            add += treeAdd[r1][cc1][tam];
            mul += treeMul[r1][cc1][tam];
            cc1 -= (cc1 & -cc1);
        }

        long ans = mul * c1 + add;

        return ans;
    }

    long sum1stD(int x, int y) {
        long mul = 0;
        long sum = 0;

        int cx = x;

        while (cx > 0) {
            mul += sum2ndD(1, cx, y);
            sum += sum2ndD(0, cx, y);
            cx -= (cx & -cx);
        }

        return mul * x + sum;
    }

    long sumSub(int x0, int y0, int x1, int y1) {
        return sum1stD(x1, y1) - sum1stD(x0 - 1, y0) - sum1stD(x1, y0 - 1) + sum1stD(x0 - 1, y0 - 1);
    }

    public void update2ndD(int tam, int r1, int c1, long mul, long add) {
        while (c1 <= c) {
            treeAdd[r1][c1][tam] += add;
            treeMul[r1][c1][tam] += mul;
            c1 += (c1 & -c1);
        }
    }

    public void update1stD(int r, int c1, int c2, long mul, long add) {
        update2ndD(1, r, c1, mul, -(c1 - 1) * mul);
        update2ndD(1, r, c2, -mul, c2 * mul);

        update2ndD(0, r, c1, add, -(c1 - 1) * add);
        update2ndD(0, r, c2, -add, c2 * add);
    }


    public void updateSub(int r0, int c0, int r1, int c1, long val) {
        update1stD(r0, c0, c1, val, -(r0 - 1) * val);
        update1stD(r1, c0, c1, -val, r1 * val);
    }
}
