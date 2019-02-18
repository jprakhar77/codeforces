package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CCreativeSnap {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int k = in.nextInt();

        a = in.nextInt();
        b = in.nextInt();

        int base = 1 << n;

        int[] av = in.nextIntArray(k);

        for (int val : av) {
            tm.merge(val - 1, 1, (x, y) -> x + y);
        }

        bps = new bp[tm.size()];

        int i = 0;
        for (int key : tm.keySet()) {
            bps[i] = new bp(key, tm.get(key));
            i++;
        }

        Arrays.sort(bps, (b1, b2) -> b1.x - b2.x);

        int rs = 0;

        for (bp bp : bps) {
            int cv = bp.a;

            bp.a = rs + cv;

            rs += cv;
        }

        long ans = dac(0, base - 1);

        out.println(ans);
    }

    long a;
    long b;
    Map<Integer, Integer> tm = new HashMap<>();
    bp[] bps;

    class bp {
        int x;
        int a;

        public bp(int x, int a) {
            this.x = x;
            this.a = a;
        }
    }

    int inf = (1 << 30) + 100;

    int ceil(int x) {
        int lo = 0;
        int hi = bps.length - 1;

        int ans = inf;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (bps[mid].x >= x) {
                ans = Math.min(ans, mid);
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    int floor(int x) {
        int lo = 0;
        int hi = bps.length - 1;

        int ans = -1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (bps[mid].x <= x) {
                ans = Math.max(ans, mid);
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    long dac(int s, int e) {
        if (s > e)
            return 0;

        int ceil = ceil(s);

        if (ceil == inf || bps[ceil].x > e) {
            return a;
        } else {
            if (s == e) {
                long av = bps[ceil].a;
                int lk = floor(s - 1);

                if (lk != -1) {
                    av -= bps[lk].a;
                }
                return b * av;
            } else {
                int floor = floor(e);
                long av = bps[floor].a;

                int lk = floor(s - 1);

                if (lk != -1) {
                    av -= bps[lk].a;
                }

                long pa = b * (e - s + 1) * av;

                return Math.min(dac(s, (e + s) / 2) + dac((e + s) / 2 + 1, e), pa);
            }
        }
    }
}
