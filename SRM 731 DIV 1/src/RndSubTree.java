public class RndSubTree {

    public int count(int k) {

        long[][] dp = new long[k][k + 1];

        for (int j = 1; j <= k; j++) {
            dp[0][j] = 1;
        }

        long[] ipow = new long[k + 1];
        long[] pow = new long[k + 1];

        long inv2 = pow(2, mod - 2, mod);

        ipow[0] = 1;
        pow[0] = 1;

        for (int i = 1; i <= k; i++) {
            ipow[i] = ipow[i - 1] * inv2;
            ipow[i] %= mod;
            pow[i] = pow[i - 1] * 2;
            pow[i] %= mod;
        }

        long[] prob = new long[k];
        prob[0] = 1;

//        for (int i = 1; i < k; i++) {
//            for (int j = i; j <= k - 1; j++) {
//                prob[i] += ipow[j];
//                prob[i] %= mod;
//            }
//        }

        prob[1] = (3 * ipow[2]) % mod;
        prob[2] = (ipow[3]);

//        for (int i = 1; i < k; i++) {
//            long rem = 1;
//            for (int j = 1; j < k; j++) {
//                long cr = 1 - prob[j];
//                cr %= mod;
//                if (cr < 0)
//                    cr += mod;
//
//                long part = rem * cr;
//                part %= mod;
//                rem -= part % mod;
//                rem %= mod;
//
//                if (rem < 0)
//                    rem += mod;
//
//                prob[j] += (part * ipow[i]) % mod;
//                prob[j] %= mod;
//
//                if (rem == 0)
//                    break;
//            }
//        }

        long[] pathprob = new long[k];
        long[] sumprob = new long[k];

        for (int i = k - 1; i >= 0; i--) {
            sumprob[i] = prob[i];
            for (int j = i + 1; j < k; j++) {
                long val = j - i;
                val *= prob[i];
                val %= mod;
                val *= prob[j];
                val %= mod;
                val *= pow[j - i];
                val %= mod;
                pathprob[i] += val;
                pathprob[i] %= mod;

                sumprob[i] += ((prob[j] * pow[j - i - 1]) % mod);
                sumprob[i] %= mod;
            }
        }

        long[] ansprob = new long[k];

        ansprob[0] = pathprob[0];

        long ans = ansprob[0];
        for (int i = 1; i < k; i++) {
            ansprob[i] = pathprob[i];
            for (int j = i - 1; j >= 0; j--) {
                long val = (i - j) * sumprob[j];
                val %= mod;
                val *= prob[i];
                val %= mod;
                ansprob[i] += val;
                ansprob[i] %= mod;
                val = pathprob[j];
                val *= inv2;
                val %= mod;
                val *= pow(prob[j], mod - 2, mod);
                val %= mod;
                val *= prob[i];
                val %= mod;
                ansprob[i] += val;
                ansprob[i] %= mod;
            }
            ansprob[i] *= pow[i];
            ansprob[i] %= mod;

            ans += ansprob[i];
            ans %= mod;
        }

        ans *= pow((pow[k] - 1) % mod, mod - 2, mod);
        ans %= mod;

        ans *= k;
        ans %= mod;
        ans *= (k - 1);
        ans %= mod;
        ans *= ipow[1];
        ans %= mod;

        if (ans < 0) {
            ans += mod;
        }

        return (int) ans;
    }

//    long[] calpre(long[] a, int[][] ncr)
//    {
//        long[] pre = new long[a.length];
//        for (int i = 1; i < a.length; i++)
//        {
//            pre[i] = a[i];
//            pre[i] *= ncr[i - 1]
//        }
//    }

    int[][] nCr(int n, int r, int mod) {
        int[][] ncr = new int[n + 1][r + 1];

        ncr[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            ncr[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= r; j++) {
                ncr[i][j] = ncr[i - 1][j - 1] + ncr[i - 1][j];
                ncr[i][j] %= mod;
            }
        }

        return ncr;
    }

    int mod = 1000000007;

    long pow(long a, long p, int mod) {
        if (p == 0) {
            return 1;
        }

        long t = pow(a, p / 2, mod);

        if (p % 2 != 0) {
            return (((t * t) % mod) * a) % mod;
        } else {
            return (t * t) % mod;
        }
    }
}
