public class EraseToGCD {

    public int countWays(int[] s, int goal) {
        int n = s.length;

        long[][] dp = new long[n][1001];

        if (s[0] % goal == 0) {
            dp[0][s[0] / goal] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= 1000; j++) {
                dp[i][j] = dp[i - 1][j];
            }

            if (s[i] % goal != 0) {
                continue;
            }

            int rem = s[i] / goal;

            dp[i][rem] += 1;
            dp[i][rem] %= mod;

            for (int j = 1; j <= 1000; j++) {
                int cg = (int) gcd(rem, j);

                dp[i][cg] += dp[i - 1][j];
                dp[i][cg] %= mod;
            }
        }

        return (int) dp[n - 1][1];
    }

    int mod = 1000000007;

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
