package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1051E {
    class RollingHash {
        int prime2 = 999900079;
        int prime = 1000099519;

        int[] a;
        long[] _10pow;
        int n;

        public RollingHash(int[] a, int n) {
            this.a = a;
            this.n = n;
            this.hash = new long[n];
            this._10pow = new long[n + 1];
            init();
        }

        long[] hash;

        void init() {
            hash[0] = a[0];

            for (int i = 1; i < n; i++) {
                hash[i] = ((hash[i - 1] * 10) + a[i]) % prime;
            }

            _10pow[0] = 1;

            for (int i = 1; i <= n; i++) {
                _10pow[i] = (_10pow[i - 1] * 10) % prime;
            }
        }

        long segmentHash(int l, int r) {
            if (l == 0) {
                return hash[r];
            }
            int size = r - l + 1;

            long leftHash = hash[l - 1] * _10pow[size];
            leftHash %= prime;

            long rightHash = hash[r];

            long segHash = rightHash - leftHash;

            if (segHash >= prime)
                segHash %= prime;

            if (segHash < 0) {
                segHash += prime;
            }

            return segHash;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        String a = in.next();
        String l = in.next();
        String r = in.next();

        int n = a.length();

        int ln = l.length();
        int rn = r.length();

        int[] rmin = new int[n];
        int[] rmax = new int[n];

        int[] aa = new int[n];
        int[] la = new int[ln];
        int[] ra = new int[rn];

        for (int i = 0; i < n; i++) {
            aa[i] = a.charAt(i) - '0';
        }

        for (int i = 0; i < ln; i++) {
            la[i] = l.charAt(i) - '0';
        }

        for (int i = 0; i < rn; i++) {
            ra[i] = r.charAt(i) - '0';
        }

        RollingHash ha = new RollingHash(aa, n);
        RollingHash hl = new RollingHash(la, ln);
        RollingHash hr = new RollingHash(ra, rn);

        for (int i = n - 1; i >= 0; i--) {
            if (aa[i] == 0)
                continue;

            int sz = n - i;

            if (sz >= ln) {

                int lo = i;
                int hi = i + ln - 1;

                int cp1 = n;
                while (lo <= hi) {
                    int mid = (lo + hi) / 2;

                    long ah = ha.segmentHash(i, mid);
                    long lh = hl.segmentHash(0, mid - i);

                    if (ah == lh) {
                        lo = mid + 1;
                    } else {
                        cp1 = Math.min(cp1, mid);
                        hi = mid - 1;
                    }
                }

                if (cp1 == n) {
                    rmin[i] = i + ln - 1;
                } else {
                    if (aa[cp1] < la[cp1 - i]) {
                        rmin[i] = i + ln;
                    } else {
                        rmin[i] = i + ln - 1;
                    }
                }

                if (sz >= rn) {
                    lo = i;
                    hi = i + rn - 1;

                    cp1 = n;
                    while (lo <= hi) {
                        int mid = (lo + hi) / 2;

                        long ah = ha.segmentHash(i, mid);
                        long lh = hr.segmentHash(0, mid - i);

                        if (ah == lh) {
                            lo = mid + 1;
                        } else {
                            cp1 = Math.min(cp1, mid);
                            hi = mid - 1;
                        }
                    }

                    if (cp1 == n) {
                        rmax[i] = i + rn - 1;
                    } else {
                        if (aa[cp1] < ra[cp1 - i]) {
                            rmax[i] = i + rn - 1;
                        } else {
                            rmax[i] = i + rn - 2;
                        }
                    }
                } else {
                    rmax[i] = n;
                }
            } else {
                rmin[i] = rmax[i] = n;
            }
        }

        long[] dp = new long[n];

        long[] cdp = new long[n];

        boolean islz = false;

        if (l.length() == 1 && la[0] == 0) {
            islz = true;
        }

        if (aa[n - 1] == 0 && islz) {
            dp[n - 1] = cdp[n - 1] = 1;
        }
        if (rmin[n - 1] < n && rmax[n - 1] >= n - 1 && aa[n - 1] != 0) {
            dp[n - 1] = cdp[n - 1] = 1;
        }


        for (int i = n - 2; i >= 0; i--) {
            if (aa[i] == 0 && islz) {
                dp[i] = dp[i + 1];
            }
            if (rmin[i] < n && rmin[i] <= rmax[i] && aa[i] != 0) {
                long ans = 0;
                if (rmin[i] < n - 1)
                    ans += cdp[rmin[i] + 1];
                if (rmax[i] < n - 2) {
                    ans -= cdp[rmax[i] + 2];
                }
                if (rmax[i] >= n - 1) {
                    ans++;
                }
                dp[i] = ans;
            }

            cdp[i] = cdp[i + 1] + dp[i];
            cdp[i] %= mod;
        }

        out.println(((dp[0] % mod) + mod) % mod);

    }

    int mod = 998244353;
}
