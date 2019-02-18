package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class arc102e {
    int mod = 998244353;

    long[][] cdp = new long[4001][4001];
    long[][] idp = new long[2001][1001];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.nextInt();
        int n = in.nextInt();

        cdp[0][0] = 1;
        for (int i = 1; i <= 4000; i++) {
            cdp[i][0] = 1;
            for (int j = 1; j <= 4000; j++) {
                cdp[i][j] = cdp[i - 1][j] + cdp[i - 1][j - 1];
                cdp[i][j] %= mod;
            }
        }

        idp[0][0] = 1;
        idp[2][0] = 1;
        idp[2][1] = 2;

        for (int i = 4; i <= 2000; i += 2) {
            idp[i][0] = 1;
            for (int j = 1; j <= i / 2; j++) {
                idp[i][j] = idp[i - 2][j] + idp[i - 2][j - 1] * 2;
                idp[i][j] %= mod;
            }
        }

        long[] ans = new long[2 * k + 1];

        for (int i = 2; i <= 2 * k; i++) {
            int ni = Math.min(i - 1, k) - Math.max(i - k, 1) + 1;
            if (i % 2 == 1) {
                assert ni % 2 == 0;
                for (int j = 0; j <= ni / 2; j++) {
                    int nn = n - j;
                    int nk = k - ni + j;

                    if (nn < 0) {
                        continue;
                    }

                    ans[i] += cdp[nn + nk - 1][nn] * idp[ni][j];
                    ans[i] %= mod;
                }
            } else {
                assert ni % 2 == 1;
                {
                    //Selecting middle
                    for (int j = 0; j <= (ni - 1) / 2; j++) {
                        int nn = n - j - 1;
                        int nk = k - ni + j;

                        if (nn < 0) {
                            continue;
                        }

                        ans[i] += cdp[nn + nk - 1][nn] * idp[ni - 1][j];
                        ans[i] %= mod;
                    }
                }
                {
                    //Not
                    for (int j = 0; j <= (ni - 1) / 2; j++) {
                        int nn = n - j;
                        int nk = k - ni + j;

                        if (nn < 0) {
                            continue;
                        }

                        ans[i] += cdp[nn + nk - 1][nn] * idp[ni - 1][j];
                        ans[i] %= mod;
                    }
                }
            }
        }

        for (int i = 2; i <= 2 * k; i++) {
            out.println(ans[i]);
        }
    }
}
