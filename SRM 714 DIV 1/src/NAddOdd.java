import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NAddOdd {

    long[] base = new long[60];

    public long solve(long l, long r, int k) {

        dp = new long[60][k + 1];

        this.k = k;

        minodd = k + 2;
        mineven = k + 1;

        for (int i = 0; i < 60; i++) {
            Arrays.fill(dp[i], -1);
            base[i] = (r >> i);
        }

        long rans = solve(r, 0);

        for (int i = 0; i < 60; i++) {
            Arrays.fill(dp[i], -1);
            base[i] = ((l - 1) >> i);
        }

        long lans = solve(l - 1, 0);

        System.out.println(called);

        return rans - lans;
    }

    int k;


//    long ans = 0;
//
//    void solveit(long s, long e, int k) {
//        if (s <= k) {
//            s = k + 1;
//        }
//
//        if (s > e)
//            return;
//
//        //odd
//        long minodd = -1;
//        long maxodd = -1;
//        long mineven = -1;
//        long maxeven = -1;
//        if (s % 2 == 0) {
//            minodd = s + 1;
//            mineven = s;
//        } else {
//            minodd = s;
//            mineven = s + 1;
//        }
//
//        if (e % 2 == 0) {
//            maxodd = e - 1;
//            maxeven = e;
//        } else {
//            maxodd = e;
//            maxeven = e - 1;
//        }
//
//        if (maxeven >= mineven) {
//            ans += (maxeven / 2 - mineven / 2 + 1);
//            solveit(mineven / 2, maxeven / 2, k);
//        }
//
//        if (maxodd >= minodd) {
//            ans += 2 * ((maxodd + k) / 2 - (minodd + k) / 2 + 1);
//            solveit((minodd + k) / 2, (maxodd + k) / 2, k);
//        }
//    }

    Map<Long, Long> map = new HashMap<>();
    long minodd = -1;
    long mineven = -1;

    long[][] dp;

    int called = 0;

    long solve(long e, int cd) {
        if (e <= k) {
            return 0;
        }

        int an = (int) (e - base[cd]);

        if (dp[cd][an] != -1) {
            return dp[cd][an];
        }

        //odd

        long maxodd = -1;
        long maxeven = -1;

        if ((e & 1) == 0) {
            maxodd = e - 1;
            maxeven = e;
        } else {
            maxodd = e;
            maxeven = e - 1;
        }

        long ans = 0;
        if (maxeven >= mineven) {
            ans += (((maxeven - mineven) >> 1) + 1);
            ans += solve(maxeven >> 1, cd + 1);
        }

        if (maxodd >= minodd) {
            ans += ((((maxodd - minodd) >> 1) + 1) << 1);
            ans += solve((maxodd + k) >> 1, cd + 1);
        }

        dp[cd][an] = ans;

        return ans;
    }
}
