public class Gangsters {

    int mod = (int) 1e9 + 7;

    public int countOrderings(int p, int a) {

        if (a == 0 || a > p / 2) {
            return 0;
        }

        long[][] dp = new long[p + 1][a + 1];

        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[2][0] = 1;
        dp[2][1] = 2;

        for (int i = 3; i <= p; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(i / 2, a); j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += dp[i - 2][j - 1];
                dp[i][j] %= mod;
            }
        }

        for (int i = 1; i <= a; i++) {
            dp[p][a] *= i;
            dp[p][a] %= mod;
        }

        return (int) dp[p][a];
    }
}
