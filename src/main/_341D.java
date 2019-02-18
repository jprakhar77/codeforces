package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _341D {
    class Bit2dRangeSum {
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

        public long sum2ndD(int[][][] tree, int r1, int c1) {
            int cc1 = c1;

            long add = 0;
            long mul = 0;

            while (cc1 > 0) {
                add ^= tree[r1][cc1][0];
                mul ^= tree[r1][cc1][1];
                cc1 -= (cc1 & -cc1);
            }

            long ans = (mul * mod2(c1)) ^ add;

            return ans;
        }

        long sum1stD(int x, int y) {
            long mul = 0;
            long sum = 0;

            int cx = x;

            while (cx > 0) {
                mul ^= sum2ndD(treeMul, cx, y);
                sum ^= sum2ndD(treeAdd, cx, y);
                cx -= (cx & -cx);
            }

            return (mul * mod2(x)) ^ sum;
        }

        long sumSub(int x0, int y0, int x1, int y1) {
            return sum1stD(x1, y1) ^ sum1stD(x0 - 1, y1) ^ sum1stD(x1, y0 - 1) ^ sum1stD(x0 - 1, y0 - 1);
        }

        public void update2ndD(int[][][] tree, int r1, int c1, long mul, long add) {
            while (r1 <= r) {
                int cc1 = c1;
                while (cc1 <= c) {
                    tree[r1][cc1][0] ^= add;
                    tree[r1][cc1][1] ^= mul;
                    cc1 += (cc1 & -cc1);
                }
                r1 += (r1 & -r1);
            }
        }

        public void update1stD(int r, int c1, int c2, long mul, long add) {
            update2ndD(treeMul, r, c1, mul, mod2(c1 - 1) * mul);
            update2ndD(treeMul, r, c2, mul, mod2(c2) * mul);

            update2ndD(treeAdd, r, c1, add, mod2(c1 - 1) * add);
            update2ndD(treeAdd, r, c2, add, mod2(c2) * add);
        }


        public void updateSub(int r0, int c0, int r1, int c1, long val) {
            update1stD(r0, c0, c1, val, mod2(r0 - 1) * val);
            update1stD(r1, c0, c1, val, mod2(r1) * val);
        }

        int mod2(int x) {
            return x & 1;
        }
    }


    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        Bit2dRangeSum bit = new Bit2dRangeSum(n, n);

        StringBuilder ans = new StringBuilder();

        while (m-- > 0) {
            int t = in.nextInt();

            if (t == 1) {
                ans.append(bit.sumSub(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()));
                ans.append(System.lineSeparator());
            } else {
                bit.updateSub(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextLong());
            }
        }

        out.println(ans);
    }
}
