package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1073E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long l = in.nextLong();
        long r = in.nextLong();

        int k = in.nextInt();

        tenpow[0] = 1;

        for (int i = 1; i < 20; i++) {
            tenpow[i] = 10 * tenpow[i - 1];
            tenpow[i] %= mod;
        }

        long ans = cal(r, k);

        ans -= cal(l - 1, k);

        ans %= mod;

        if (ans < 0) {
            ans += mod;
        }

        out.println(ans);
    }

    int mod = 998244353;

    long cal(long num, int k) {
        if (num == 0) {
            return 0;
        }

        long cnum = num;

        int size = 0;
        while (cnum > 0) {
            cnum /= 10;
            size++;
        }

        int bitmax = 1 << 10;

        long[][][] dp = new long[size][bitmax][2];
        long[][][] dp2 = new long[size][bitmax][2];

        cnum = num;

        long ans = 0;
        for (int j = 0; j < bitmax; j++) {

            int cn = (int) (cnum % 10);
            int cnt = 0;
            for (int l = 0; l < 10; l++) {
                if (((1 << l) & j) != 0) {
                    cnt++;
                }
            }

            dp[size - 1][j][0] = dp[size - 1][j][1] = -1;
            if (cnt > k) {
                continue;
            }
            //0
            for (int l = 9; l >= 0; l--) {
                if (l == 0 && j == 0) {
                    if (size - 1 != 0) {
                        ans += dp[size - 1][j][0];
                        ans %= mod;
                    }
                }
                if (((1 << l) & j) == 0) {
                    if (cnt < k) {
                        update(dp, size - 1, j, 0, l);
                        dp2[size - 1][j][0]++;
                    }
                } else {
                    update(dp, size - 1, j, 0, l);
                    dp2[size - 1][j][0]++;
                }
            }
            //1
            for (int l = cn; l >= 0; l--) {
                if (l == 0 && j == 0) {
                    if (size - 1 == 0) {
                        ans += dp[size - 1][j][1];
                        ans %= mod;
                    }
                }
                if (((1 << l) & j) == 0) {
                    if (cnt < k) {
                        update(dp, size - 1, j, 1, l);
                        dp2[size - 1][j][1]++;
                    }
                } else {
                    update(dp, size - 1, j, 1, l);
                    dp2[size - 1][j][1]++;
                }
            }
        }

        cnum /= 10;

        for (int i = size - 2; i >= 0; i--) {
            int cn = (int) (cnum % 10);
            for (int j = 0; j < bitmax; j++) {

                int cnt = 0;
                for (int l = 0; l < 10; l++) {
                    if (((1 << l) & j) != 0) {
                        cnt++;
                    }
                }

                dp[i][j][0] = dp[i][j][1] = -1;
                if (cnt > k) {
                    continue;
                }

                int power = size - 1 - i;
                //0
                for (int l = 9; l >= 0; l--) {
                    if (l == 0 && j == 0) {
                        if (i != 0) {
                            ans += dp[i][j][0];
                            ans %= mod;
                        }
                    }
                    if (((1 << l) & j) == 0) {
                        if (cnt < k && dp[i + 1][j | (1 << l)][0] != -1) {
                            update(dp, i, j, 0, dp2[i + 1][j | (1 << l)][0] * l * tenpow[power] + dp[i + 1][j | (1 << l)][0]);
                            dp2[i][j][0] += dp2[i + 1][j | (1 << l)][0];
                            dp2[i][j][0] %= mod;
                        }
                    } else {
                        if (dp[i + 1][j][0] != -1) {
                            update(dp, i, j, 0, dp2[i + 1][j][0] * l * tenpow[power] + dp[i + 1][j][0]);
                            dp2[i][j][0] += dp2[i + 1][j][0];
                            dp2[i][j][0] %= mod;
                        }
                    }
                }
                //1
                if (((1 << cn) & j) == 0) {
                    if (cnt < k && dp[i + 1][j | (1 << cn)][1] != -1) {
                        update(dp, i, j, 1, cn * dp2[i + 1][j | (1 << cn)][1] * tenpow[power] + dp[i + 1][j | (1 << cn)][1]);
                        dp2[i][j][1] += dp2[i + 1][j | (1 << cn)][1];
                        dp2[i][j][1] %= mod;
                    }
                } else {
                    if (dp[i + 1][j][1] != -1) {
                        update(dp, i, j, 1, cn * dp2[i + 1][j][1] * tenpow[power] + dp[i + 1][j][1]);
                        dp2[i][j][1] += dp2[i + 1][j][1];
                        dp2[i][j][1] %= mod;
                    }
                }
                for (int l = cn - 1; l >= 0; l--) {
                    if (l == 0 && j == 0) {
                        if (i == 0) {
                            ans += dp[i][j][1];
                            ans %= mod;
                        }
                    }
                    if (((1 << l) & j) == 0) {
                        if (cnt < k && dp[i + 1][j | (1 << l)][0] != -1) {
                            update(dp, i, j, 1, l * dp2[i + 1][j | (1 << l)][0] * tenpow[power] + dp[i + 1][j | (1 << l)][0]);
                            dp2[i][j][1] += dp2[i + 1][j | (1 << l)][0];
                            dp2[i][j][1] %= mod;
                        }
                    } else {
                        if (dp[i + 1][j][0] != -1) {
                            update(dp, i, j, 1, l * dp2[i + 1][j][0] * tenpow[power] + dp[i + 1][j][0]);
                            dp2[i][j][1] += dp2[i + 1][j][0];
                            dp2[i][j][1] %= mod;
                        }
                    }
                }
            }

            cnum /= 10;
        }

//        for (int j = 0; j < bitmax; j++) {
//
//            int cnt = 0;
//            for (int l = 0; l < 10; l++) {
//                if (((1 << l) & j) != 0) {
//                    cnt++;
//                }
//            }
//
//            if (cnt > k) {
//                continue;
//            }
//
//            if (dp[0][j][1] != -1)
//                ans += dp[0][j][1];
//
//            ans %= mod;
//        }

        return ans;
    }

    long[] tenpow = new long[20];

    void update(long[][][] dp, int i, int j, int k, long val) {

        val %= mod;

        if (dp[i][j][k] == -1) {
            dp[i][j][k] = 0;
        }

        dp[i][j][k] += val;
        dp[i][j][k] %= mod;
    }
}
