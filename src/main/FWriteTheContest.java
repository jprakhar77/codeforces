package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class FWriteTheContest {
    class ap {
        int a;
        int p;

        public ap(int a, int p) {
            this.a = a;
            this.p = p;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int tc = in.nextInt();

        double[] coef = new double[101];

        coef[0] = 1;

        for (int i = 1; i <= 100; i++) {
            coef[i] = 0.9 * coef[i - 1];
        }

        for (int i = 1; i <= 100; i++) {
            coef[i] = 1 / coef[i];
        }

        double[][][] dp = new double[2][1001][101];

        ap[] aps = new ap[100];

        for (int i = 0; i < 100; i++) {
            aps[i] = new ap(0, 0);
        }

        while (tc-- > 0) {
            int n = in.nextInt();

            double c = in.nextDouble();
            double t = in.nextDouble();

            for (int i = 0; i < n; i++) {
                aps[i].a = in.nextInt();
                aps[i].p = in.nextInt();
            }

            for (int i = n; i < 100; i++) {
                aps[i].a = 0;
                aps[i].p = 0;
            }

            Arrays.sort(aps, (a1, a2) -> a2.a - a1.a);

            int totp = 0;

            for (int i = 0; i < n; i++) {
                totp += aps[i].p;
            }

            for (int i = 0; i <= totp; i++) {
                for (int j = 0; j <= n; j++) {
                    dp[0][i][j] = dp[1][i][j] = inf;
                }
            }

            dp[0][0][0] = 0;

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= totp; j++) {
                    for (int k = 0; k <= n; k++) {
                        if (j == 0) {
                            dp[1][j][k] = 0;
                        } else if (k == 0) {
                            dp[1][j][k] = inf;
                        } else {
                            dp[1][j][k] = dp[0][j][k];

                            if (j >= aps[i - 1].p) {
                                dp[1][j][k] = Math.min(dp[1][j][k], dp[0][j - aps[i - 1].p][k - 1] + coef[k] * aps[i - 1].a);
                            }
                        }
                    }
                }
                for (int k = 0; k <= n; k++) {
                    dp[0][totp][k] = dp[1][totp][k];
                    for (int j = totp - 1; j >= 0; j--) {
                        dp[1][j][k] = Math.min(dp[1][j][k], dp[1][j + 1][k]);
                        dp[0][j][k] = dp[1][j][k];
                    }
                }
            }


            int lo = 0;
            int hi = totp;

            int ans = 0;

            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                boolean poss = false;
                o:
                for (int i = 1; i <= n; i++) {
                    if (t <= i * 10)
                        break;
                    if (dp[1][mid][i] <= t - i * 10) {
                        poss = true;
                        break;
                    } else if (dp[1][mid][i] < inf) {
                        double clo = 0;
                        double chi = t - i * 10;

                        for (int j = 1; j <= 20; j++) {
                            double cmid = clo + (chi - clo) / 3;

                            double ns = 1 + c * cmid;

                            double remt = t - cmid - i * 10;

                            double nt = remt * ns;

                            double cmid2 = chi - (chi - clo) / 3;

                            double ns2 = 1 + c * cmid2;

                            double remt2 = t - cmid2 - i * 10;

                            double nt2 = remt2 * ns2;

                            if (nt2 > nt) {
                                if (nt2 >= dp[1][mid][i]) {
                                    poss = true;
                                    break o;
                                }
                                clo = cmid;
                            } else if (nt > nt2){
                                if (nt >= dp[1][mid][i]) {
                                    poss = true;
                                    break o;
                                }
                                chi = cmid2;
                            }else {
                                if (nt >= dp[1][mid][i]) {
                                    poss = true;
                                    break o;
                                }
                                clo = cmid;
                                chi = cmid2;
                            }
                        }
                    }
                }

                if (poss) {
                    ans = Math.max(mid, ans);
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            out.println(ans);
        }

    }

    double eps = 2e-3;
    double inf = 1e16;
}
