package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class RWalk {
    class MatrixExpo {
        int n;

        public MatrixExpo(int n) {
            this.n = n;
        }

        long[][] id = new long[][]{{1, 0}, {0, 1}};

        long[][] matrixPower(long[][] base, long pow, long mod) {
//            if (pow == 0) {
//                return ans;
//            }
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

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        long k = in.nextLong();

        long[][] a = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }

        MatrixExpo mat = new MatrixExpo(n);

        if (k > 1)
            a = mat.matrixPower(a, k, mod);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += a[i][j];
                ans %= mod;
            }
        }

        out.println(ans);
    }

    int mod = 1000000007;
}
