import java.util.Arrays;

public class DieDesign {

    public int[] design(int[] pips) {

        int n = pips.length;

        Arrays.sort(pips);

        int sum = 0;

        for (int val : pips) {
            sum += val;
        }

        int[][] dp = new int[n][sum + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        ans = new int[n];

        direc = new int[n][sum + 1];

        rec(0, sum, dp, pips, n);

        int csum = sum;

        for (int i = 0; i < n; i++) {
            ans[i] = direc[i][csum];
            csum -= ans[i];
        }

        int nsum = 0;

        for (int val : ans) {
            nsum += val;
        }

        if (nsum < sum) {
            ans[n - 1] += sum - nsum;
        }

        return ans;
    }

    int[] ans;

    int[][] direc;

    int rec(int i, int csum, int[][] dp, int[] pips, int n) {
        if (i == n) {
            return 0;
        }

        if (dp[i][csum] != -1) {
            return dp[i][csum];
        }

        dp[i][csum] = rec(i + 1, csum, dp, pips, n);
        int av = 0;
        for (int j = 0; j < n; j++) {
            if (pips[j] + 1 <= csum) {

                int ca = j + 1 + rec(i + 1, csum - pips[j] - 1, dp, pips, n);

                if (ca > dp[i][csum]) {
                    dp[i][csum] = ca;
                    direc[i][csum] = pips[j] + 1;
                }
            }
        }

        return dp[i][csum];
    }
}
