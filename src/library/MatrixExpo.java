package library;

public class MatrixExpo {
    int n;

    public MatrixExpo(int n) {
        this.n = n;
    }

    long[][] matrixPower(long[][] base, long pow, long mod) {
        long[][] ans = new long[n][n];

        for (int i = 0; i < n; i++)
            ans[i][i] = 1;

        while (pow != 0) {
            if ((pow & 1) != 0) ans = multiplyMatrix(ans, base, mod);

            base = multiplyMatrix(base, base, mod);

            pow >>= 1;
        }

        return ans;
    }

    long[][] multiplyMatrix(long[][] m, long[][] m2, long mod) {
        long[][] ans = new long[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                ans[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    ans[i][j] += (m[i][k] * m2[k][j]) % mod;
                    ans[i][j] %= mod;
                }
            }

        return ans;
    }
}