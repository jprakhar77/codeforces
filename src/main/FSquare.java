package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FSquare {
    class pair {
        int x;
        int y;

        public pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            pair pair = (pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextInt();

        int ni = (int) n;

        int m = in.nextInt();

        long extra = (n * n - n - 2 * (n - 1) - 2 * (n - 2)) / 2;

        Map<pair, Integer> pairs = new HashMap<>();
        Map<pair, Integer> expairs = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            int c = in.nextInt();

            if (Math.abs(x - y) < 3) {
                pairs.put(new pair(x, y), c);
            } else {
                pair cp = new pair(x, y);
                pair rp = new pair(y, x);
                if (expairs.containsKey(rp)) {
                    int cr = expairs.get(rp);

                    if (cr != c) {
                        out.println(0);
                        return;
                    }
                } else
                    extra--;
                expairs.put(cp, c);
            }
        }

        long ans = pow(2, extra, mod);

        long[][] dp = new long[ni][8];

        for (int i = 0; i < 8; i++) {
            if (isp(i, ni - 1, pairs)) {
                dp[ni - 1][i] += 1;
            }
        }

        for (int j = ni - 2; j >= 1; j--) {
            pair pair0 = new pair(j - 1, j + 1);
            pair pair1 = new pair(j + 1, j - 1);
            for (int k = 0; k < 8; k++) {
                int popCnt = Integer.bitCount(k);
                long pa = 1;
                if (pairs.containsKey(pair0) && pairs.containsKey(pair1)) {
                    int val1 = pairs.get(pair0);
                    int val2 = pairs.get(pair1);

                    if ((val1 + val2) % 2 == 0 && popCnt % 2 == 1) {
                        continue;
                    } else if ((val1 + val2) % 2 == 0 && popCnt % 2 == 0) {

                    } else if ((val1 + val2) % 2 == 1 && popCnt % 2 == 1) {

                    } else if ((val1 + val2) % 2 == 1 && popCnt % 2 == 0) {
                        continue;
                    }
                } else if (pairs.containsKey(pair0)) {

                } else if (pairs.containsKey(pair1)) {

                } else {
                    pa = 2;
                }
                if (dp[j + 1][k] > 0) {
                    for (int l = 0; l < 8; l++) {

                        boolean isSetCenter = ((l & 2) != 0);
                        if (isp(l, j, pairs)) {
                            if ((isSetCenter && popCnt % 2 == 1) || (!isSetCenter && popCnt % 2 == 0)) {
                                long ca = pa;
                                dp[j][l] += (ca * dp[j + 1][k]) % mod;
                                dp[j][l] %= mod;
                            }
                        }
                    }
                }
            }
        }

        long fans = 0;

        pair pair0 = new pair(0, 0);

        int val = -1;

        if (pairs.containsKey(pair0)) {
            val = pairs.get(pair0);
        }

        for (int i = 0; i < 8; i++) {
            int pop = Integer.bitCount(i);

            pop %= 2;

            if (val != -1) {
                if ((pop ^ val) == 0) {
                    fans += (ans * dp[1][i]) % mod;
                    fans %= mod;
                }
            } else {
                fans += (ans * dp[1][i]) % mod;
                fans %= mod;
            }
        }

        out.println(fans);
    }

    boolean isp(int mask, int i, Map<pair, Integer> pairs) {
        pair pair0 = new pair(i, i - 1);
        pair pair1 = new pair(i, i);
        pair pair2 = new pair(i - 1, i);
        //j == 0
        if (pairs.containsKey(pair0)) {
            int val = pairs.get(pair0);
            if (val == 0 && (mask & 1) != 0)
                return false;
            if (val != 0 && (mask & 1) == 0)
                return false;
        }

        if (pairs.containsKey(pair1)) {
            int val = pairs.get(pair1);
            if (val == 0 && (mask & 2) != 0)
                return false;
            if (val != 0 && (mask & 2) == 0)
                return false;
        }

        if (pairs.containsKey(pair2)) {
            int val = pairs.get(pair2);
            if (val == 0 && (mask & 4) != 0)
                return false;
            if (val != 0 && (mask & 4) == 0)
                return false;
        }

        return true;
    }

    int mod = 998244353;

    long pow(long a, long p, int mod) {
        if (p == 0) {
            return 1;
        }

        long t = pow(a, p / 2, mod);

        if (p % 2 != 0) {
            return (((t * t) % mod) * a) % mod;
        } else {
            return (t * t) % mod;
        }
    }
}
