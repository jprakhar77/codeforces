public class DigitRotation {

    int mod = 998244353;

    public int sumRotations(String s) {

        int n = s.length();

        long[] pow = new long[n];

        pow[n - 1] = 1;

        for (int i = n - 2; i >= 0; i--) {
            pow[i] = pow[i + 1] * 10;
            pow[i] %= mod;
        }

        long fans = 0;
        for (int i = 0; i < n; i++) {
            fans += pow[i] * (s.charAt(i) - '0');
            fans %= mod;
        }

        long[][] allpow = new long[n][10];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                allpow[i][j] = pow[i] * j;
                allpow[i][j] %= mod;
            }
        }

        long ffans = 0;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (i == 0 && s.charAt(k) == '0')
                        continue;

                    int iv = s.charAt(i) - '0';
                    int jv = s.charAt(j) - '0';
                    int kv = s.charAt(k) - '0';

                    long ans = fans;

                    ans -= allpow[i][iv];
                    ans -= allpow[j][jv];
                    ans -= allpow[k][kv];

                    ans += allpow[i][kv];
                    ans += allpow[j][iv];
                    ans += allpow[k][jv];

                    ans %= mod;
                    ans += mod;
                    ans %= mod;

                    ffans += ans;
                }
            }
        }

        return (int) (ffans % mod);
    }
}
