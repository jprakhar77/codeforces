public class RainForecast {

    public double predictRain(int ilkoProb, int[] deliverProbs) {

        int n = deliverProbs.length;

        double[][] dp = new double[n][2];

        for (int i = 0; i < n; i++) {
            dp[i][0] = dp[i][1] = -1;
        }

        double[] ip = new double[n];

        for (int i = 0; i < n; i++) {
            ip[i] = ((double) deliverProbs[i]) / 100;
        }

        double in = ((double) ilkoProb) / 100;
        double ans1 = in * rec(dp, 0, 0, 0, n, ip);
        ans1 += (1 - in) * rec(dp, 0, 0, 1, n, ip);

        double ans2 = (1 - in) * rec(dp, 0, 0, 0, n, ip);
        ans2 += in * rec(dp, 0, 0, 1, n, ip);

        return Math.max(ans1, ans2);
    }

    double rec(double[][] dp, int i, int em, int mod, int n, double[] ip) {
        if (i == n) {
            if (mod == em)
                return 1;
            else return 0;
        }

        if (dp[i][mod] != -1)
            return dp[i][mod];

        dp[i][mod] = ip[i] * rec(dp, i + 1, em, mod, n, ip);
        dp[i][mod] += (1 - ip[i]) * rec(dp, i + 1, em, 1 - mod, n, ip);

        return dp[i][mod];
    }
}
