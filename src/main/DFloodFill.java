package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class DFloodFill {
    int[][][] dp = new int[2][5000][5000];
    //int[][] dp2 = new int[5000][5000];
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] c = in.nextIntArray(n);

        List<Integer> l = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i == 0 || c[i] != c[i - 1]) {
                l.add(c[i]);
            }
        }

        int an = l.size();

        if (an == 1) {
            out.println(0);
            return;
        }

//        for (int i = 0; i < an; i++) {
//            for (int j = 0; j < an; j++) {
//                dp[i][j][0] = dp[i][j][1] = inf;
//            }
//        }
//
//        for (int sz = 1; sz <= an; sz++) {
//            for (int i = 0; i < an - sz + 1; i++) {
//                int j = i + sz - 1;
//
//                int cl = l.get(i);
//                int cr = l.get(j);
//                if (sz == 1) {
//                    dp[i][j][0] = dp[i][j][1] = 0;
//                } else if (sz == 2) {
//                    if (cl == cr)
//                        dp[i][j][0] = dp[i][j][1] = 0;
//                    else
//                        dp[i][j][0] = dp[i][j][1] = 1;
//                } else {
//                    //ch l
//
//                    if (cl == l.get(i + 1)) {
//                        dp[i][j][0] = dp[i + 1][j][0];
//                    } else {
//                        dp[i][j][0] = (short) (dp[i + 1][j][0] + 1);
//                    }
//
//                    if (cl == cr) {
//                        dp[i][j][0] = (short) Math.min(dp[i][j][0], dp[i + 1][j][1]);
//                    } else {
//                        dp[i][j][0] = (short) Math.min(dp[i][j][0], dp[i + 1][j][1] + 1);
//                    }
//
//                    if (cr == l.get(j - 1)) {
//                        dp[i][j][1] = dp[i][j - 1][1];
//                    } else {
//                        dp[i][j][1] = (short) (dp[i][j - 1][1] + 1);
//                    }
//
//                    if (cl == cr) {
//                        dp[i][j][1] = (short) Math.min(dp[i][j][1], dp[i][j - 1][0]);
//                    } else {
//                        dp[i][j][1] = (short) Math.min(dp[i][j][1], dp[i][j - 1][0] + 1);
//                    }
//
////                    if (cl == cr) {
////                        int min = Math.min(dp[i][j][0], dp[i][j][1]);
////                        if (cl == l.get(i + 1)) {
////                            dp[i][j][0] = dp[i][j][1] = (short) Math.min(min, dp[i + 1][j - 1][0]);
////                        } else {
////                            dp[i][j][0] = dp[i][j][1] = (short) Math.min(min, dp[i + 1][j - 1][0] + 1);
////                        }
////
////                        min = dp[i][j][0];
////                        if (cr == l.get(j - 1)) {
////                            dp[i][j][0] = dp[i][j][1] = (short) Math.min(min, dp[i + 1][j - 1][1]);
////                        } else {
////                            dp[i][j][0] = dp[i][j][1] = (short) Math.min(min, dp[i + 1][j - 1][1] + 1);
////                        }
////                    }
//                }
//            }
//        }
//
//        int ans = inf;
//
//        ans = Math.min(dp[0][an - 1][0], dp[0][an - 1][1]);
//
//        out.println(ans);


    }

    short inf = Short.MAX_VALUE;
}
