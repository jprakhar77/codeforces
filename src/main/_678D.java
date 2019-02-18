package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _678D {
    static public class MatrixExpo {
        int n;

        public MatrixExpo(int n) {
            this.n = n;
        }

        long[][] id = new long[][]{{1, 0}, {0, 1}};

        long[][] matrixPower(long[][] base, long pow, long mod) {
            if (pow == 0) {
                return id;
            }
            if (pow == 1) {
                return base;
            }

            long[][] t = matrixPower(base, pow / 2, mod);

            t = multiplyMatrix(t, t, mod);
            if (pow % 2 == 1) {
                t = multiplyMatrix(t, base, mod);
            }

            return t;
        }

        long[][] multiplyMatrix(long[][] m, long[][] m2, long mod) {
            long[][] ans = new long[n][n];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    ans[i][j] = 0;
                    for (int k = 0; k < n; k++) {
                        ans[i][j] += m[i][k] * m2[k][j];
                        ans[i][j] %= mod;
                    }
                }

            return ans;
        }
    }

    int mod = (int) 1e9 + 7;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        long n = in.nextLong();
        int x = in.nextInt();

        MatrixExpo mat = new MatrixExpo(2);

        long[][] base = {{a, b}, {0, 1}};

        base = mat.matrixPower(base, n, mod);

        out.println((base[0][0] * x + base[0][1]) % mod);
    }
}
