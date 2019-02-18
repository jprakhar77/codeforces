public class ExpectedSum {

    public double solve(int[] sequence, int[] probMinus) {
        int n = sequence.length;

        double[] prob = new double[n];
        double[] revprob = new double[n];

        for (int i = 0; i < n; i++) {
            prob[i] = ((double) probMinus[i]) / 100;
            revprob[i] = 1 - prob[i];
        }

        double[][][] dp = new double[2][2501][2501];

        dp[0][sequence[0]][sequence[0]] = revprob[0];
        dp[0][0][0] = prob[0];

        for (int i = 1; i < n; i++) {
            for (int psum = 0; psum <= 2500; psum++) {
                for (int pmax = 0; pmax <= 2500; pmax++) {
                    if (dp[0][psum][pmax] != 0) {
                        //pos
                        dp[1][psum + sequence[i]][Math.max(pmax, psum + sequence[i])]
                                += dp[0][psum][pmax] * revprob[i];

                        //neg
                        dp[1][Math.max(0, psum - sequence[i])][pmax] += dp[0][psum][pmax] * prob[i];
                    }
                }
            }

            for (int psum = 0; psum <= 2500; psum++) {
                for (int pmax = 0; pmax <= 2500; pmax++) {
                    dp[0][psum][pmax] = dp[1][psum][pmax];
                    dp[1][psum][pmax] = 0;
                }
            }
        }

        double ans = 0;

        for (int psum = 0; psum <= 2500; psum++) {
            for (int pmax = 0; pmax <= 2500; pmax++) {
                if (dp[0][psum][pmax] != 0)
                    ans += pmax * dp[0][psum][pmax];
            }
        }

        return ans;
    }
}
