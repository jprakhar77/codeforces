public class Chains1 {

    public long countMaximalChains(int n) {
        long[][] dp = new long[n + 1][n + 1];

        for (int j = 0; j <= n; j++)
            dp[1][j] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j + 1];
            }
        }

        return dp[n][0];
    }
}
