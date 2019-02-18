package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _992C {
    static int mod = (int) 1e9 + 7;

    static public class Matrix {


        static int N;    // size of the matrix

        /**
         * compute pow(base, pow)
         * O(N^3) * logN
         **/

        static long[][] id = new long[][]{{1, 0}, {0, 1}};

        static long[][] matrixPower(long[][] mat, long[][] base, long pow) {
            if (pow == 0) {
                return id;
            }
            if (pow == 1) {
                return base;
            }

            long[][] t = matrixPower(mat, base, pow / 2);

            t = multiplyMatrix(t, t);
            if (pow % 2 == 1) {
                t = multiplyMatrix(t, base);
            }

            return t;
        }

        /**
         * compute m * m2
         * O(N^3)
         **/
        static long[][] multiplyMatrix(long[][] m, long[][] m2) {
            long[][] ans = new long[N][N];

            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++) {
                    ans[i][j] = 0;
                    for (int k = 0; k < N; k++) {
                        ans[i][j] += m[i][k] * m2[k][j];
                        ans[i][j] %= mod;
                    }
                }

            return ans;
        }


    }


    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long x = in.nextLong();
        long k = in.nextLong();

        if (x == 0) {
            out.println(0);
            return;
        }

        long[][] base = new long[][]{{2, 0}, {1, 1}};

        Matrix.N = 2;

        base = Matrix.matrixPower(base, base, k);

        x %= mod;
        long ans = 2 * base[0][0] * x - base[1][0];

        ans %= mod;

        if (ans < 0)
            ans += mod;

        out.println(ans);
    }
}
