package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class CoinPartition {
    class coin {
        int v;
        int i;

        public coin(int v, int i) {
            this.v = v;
            this.i = i;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        coin[] coins = new coin[n];

        int sumv = 0;
        for (int i = 0; i < n; i++) {
            coins[i] = new coin(in.nextInt(), i);
            sumv += coins[i].v;
        }

        Arrays.sort(coins, (c1, c2) -> c1.v - c2.v);

        boolean[][] dp = new boolean[sumv + 1][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = true;
        }

        dp[coins[0].v][0] = true;

        sumv -= coins[n - 1].v;

        for (int sum = 1; sum <= sumv; sum++) {
            for (int i = 1; i < n - 1; i++) {
                dp[sum][i] = dp[sum][i - 1];

                if (sum >= coins[i].v) {
                    dp[sum][i] |= dp[sum - coins[i].v][i - 1];
                }
            }
        }

        int midsum = (sumv + 1) / 2;

        int larInd = -1;
        int sumPoss = -1;
        boolean isReverse = false;

        boolean isfound = false;

        for (int sumreq = midsum; sumreq <= sumv && !isfound; sumreq++) {
            for (int i = n - 2; i >= 0; i--) {
                if (dp[sumreq][i] && sumreq >= coins[i].v && (i == 0 || dp[sumreq - coins[i].v][i - 1])) {
                    int othersum = (sumv - sumreq);
                    int diff = sumreq - othersum;

                    int largestNum = coins[i].v;

                    if (diff >= largestNum) {
                        isReverse = true;
                    }

                    sumPoss = sumreq;
                    larInd = i;
                    isfound = true;
                    break;
                }
            }
        }

        int csum = sumPoss;
        int cind = larInd;
        Set<Integer> cointIndPart = new HashSet<>();

        while (csum > 0) {
            for (int i = cind; i >= 0; i--) {
                if (dp[csum][i] && coins[i].v <= csum && (i == 0 || dp[csum - coins[i].v][i - 1])) {
                    csum = csum - coins[i].v;
                    cind = i - 1;
                    cointIndPart.add(i);
                    break;
                }
            }
        }

        List<coin> tsd = new ArrayList<>();
        List<coin> tss = new ArrayList<>();


        int ssum = 0;
        int dsum = 0;
        for (int i = 0; i < n - 1; i++) {
            if (cointIndPart.contains(i)) {
                tsd.add(new coin(coins[i].v, coins[i].i));
            } else {
                tss.add(new coin(coins[i].v, coins[i].i));
            }
        }

        tsd.sort((a, b) -> a.v - b.v);
        tss.sort((a, b) -> a.v - b.v);

        int si = 0;
        int di = 0;

        List<Integer> ans = new ArrayList<>();

        while (si <= tss.size() || di < tsd.size()) {
            if (ssum <= dsum) {
                if (si < tss.size()) {
                    coin cc = tss.get(si);
                    ssum += cc.v;
                    ans.add(cc.i + 1);
                    si++;
                } else {
                    ssum += coins[n - 1].v;
                    ans.add(coins[n - 1].i + 1);
                    si++;
                }
            } else {
                coin cc = tsd.get(di);
                dsum += cc.v;
                ans.add(cc.i + 1);
                di++;
            }
        }

        for (int i = 0; i < ans.size(); i++) {
            out.print(ans.get(i) + " ");
        }

        out.println();
    }
}
