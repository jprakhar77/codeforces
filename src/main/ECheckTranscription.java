package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ECheckTranscription {
    class SegmentRollingHash {
        int prime = 1 << 30;

        long[] pow;
        int n;

        public SegmentRollingHash(int n) {
            this.n = n;
            this.hash = new long[n];
            this.pow = new long[n + 1];
        }

        long[] hash;

        void initForNumber(int[] a, int r) {
            hash[0] = a[0];

            for (int i = 1; i < n; i++) {
                hash[i] = ((hash[i - 1] * r) + a[i]) % prime;
            }

            pow[0] = 1;

            for (int i = 1; i <= n; i++) {
                pow[i] = (pow[i - 1] * r) % prime;
            }
        }

        void initForString(String s, char baseChar, int r) {
            hash[0] = s.charAt(0) - baseChar;

            for (int i = 1; i < n; i++) {
                hash[i] = ((hash[i - 1] * r) + (s.charAt(i) - baseChar)) % prime;
            }

            pow[0] = 1;

            for (int i = 1; i <= n; i++) {
                pow[i] = (pow[i - 1] * r) % prime;
            }
        }

        long segmentHash(int l, int r) {
            if (l == 0) {
                return hash[r];
            }
            int size = r - l + 1;

            long leftHash = hash[l - 1] * pow[size];
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
        String s = in.next();
        String t = in.next();

        int sn = s.length();
        int tn = t.length();

        SegmentRollingHash srh = new SegmentRollingHash(tn);

        srh.initForString(t, 'a', 30);

        int z = 0;
        int o = 0;
        int oin = -1;
        int zin = -1;
        for (int i = 0; i < sn; i++) {
            if (s.charAt(i) == '0') {
                if (zin == -1)
                    zin = i;
                z++;
            } else {
                if (oin == -1)
                    oin = i;
                o++;
            }
        }

        long ans = 0;
        o:
        for (int sz = 1; ; sz++) {
            int osz, zsz;

            if (z >= o) {
                int nsz = z * sz;
                int rem = tn - nsz;

                if (rem > 0 && rem % o == 0) {
                    osz = rem / o;
                    zsz = sz;
                } else {
                    if (rem >= o)
                        continue;
                    else break;
                }
            } else {
                int nsz = o * sz;
                int rem = tn - nsz;

                if (rem > 0 && rem % z == 0) {
                    zsz = rem / z;
                    osz = sz;
                } else {
                    if (rem >= z)
                        continue;
                    else break;
                }
            }

            long ohash = srh.segmentHash(oin * zsz, oin * zsz + osz - 1);
            long zhash = srh.segmentHash(zin * osz, zin * osz + zsz - 1);

            if (osz == zsz && ohash == zhash)
                continue;

            for (int i = 0, j = 0; i < sn; i++) {
                if (s.charAt(i) == '1') {
                    long hash = srh.segmentHash(j, j + osz - 1);

                    if (hash != ohash) {
                        continue o;
                    }

                    j += osz;
                } else {
                    long hash = srh.segmentHash(j, j + zsz - 1);

                    if (hash != zhash) {
                        continue o;
                    }

                    j += zsz;
                }
            }

            ans++;
        }

        out.println(ans);
    }
}
