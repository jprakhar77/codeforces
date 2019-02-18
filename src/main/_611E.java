package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.TreeMap;

public class _611E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] p = new int[3];

        in.readArray(p, 3, 0);

        Arrays.sort(p);

        int[] t = new int[n];

        in.readArray(t, n, 0);

        ts = new TreeMap<>();

        for (int val : t) {
            ts.merge(val, 1, (x, y) -> x + y);
        }

        int ans = 0;

        if (ts.lastKey() > p[0] + p[1] + p[2]) {
            out.println(-1);
            return;
        }

        while (ts.size() > 0) {
            Integer last = ts.lastKey();

            if (last > p[2] + p[1]) {
                ans++;
                remove(last);
            } else {
                break;
            }
        }

        while (ts.size() > 0) {
            Integer last = ts.lastKey();

            if (last > p[2]) {
                remove(last);
                Integer best = best(last, p);

                if (best != null) {
                    remove(best);
                }

                ans++;
            } else {
                break;
            }
        }

        int[] rema = new int[3];

        for (Integer val : ts.keySet()) {
            if (val <= p[0]) {
                rema[0] += ts.get(val);
            } else if (val <= p[1]) {
                rema[1] += ts.get(val);
            } else {
                rema[2] += ts.get(val);
            }
        }

        int remans = cal(rema);

        int rem1 = remans;
        int rem2 = 0;

        while (ts.size() > 0) {
            Integer last = ts.lastKey();

            remove(last);
            Integer best = best(last, p);

            if (best != null) {
                remove(best);
            }

            rem2++;

            sub(rema, last, p);
            if (best != null)
                sub(rema, best, p);

            remans = Math.min(remans, rem2 + cal(rema));
        }

        out.println(ans + remans);
    }

    void sub(int[] rem, int num, int[] p) {
        if (num <= p[0]) {
            rem[0]--;
        } else if (num <= p[1]) {
            rem[1]--;
        } else {
            rem[2]--;
        }
    }

    int cal(int[] rem) {
        int lo = 0;
        int hi = 0;

        for (int i = 0; i < 3; i++) {
            hi = Math.max(hi, rem[i]);
        }

        int ans = hi;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            int[] nrem = rem.clone();

            if (nrem[0] > mid) {
                nrem[1] += nrem[0] - mid;
            }

            if (nrem[1] > mid) {
                nrem[2] += nrem[1] - mid;
            }

            if (nrem[2] <= mid) {
                ans = Math.min(ans, mid);
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }


        }

        return ans;
    }

    Integer best(int num, int[] p) {
        Integer best = null;


        for (int j = 1; j < 8; j++) {
            Integer cur = 0;
            Integer rem = 0;
            for (int i = 0; i < 3; i++) {
                if (((1 << i) & j) != 0) {
                    cur += p[i];
                } else {
                    rem += p[i];
                }
            }

            if (cur >= num) {
                Integer cb = ts.floorKey(rem);

                if (cb != null) {
                    if (best == null || best < cb) {
                        best = cb;
                    }
                }
            }
        }

        return best;
    }


    boolean isposs(int t1, int t2, int[] p) {
        if (t1 <= p[0] && t2 <= p[1] + p[2]) {
            return true;
        }

        if (t1 <= p[1] && t2 <= p[0] + p[2]) {
            return true;
        }

        if (t1 <= p[2] && t2 <= p[0] + p[1]) {
            return true;
        }

        if (t1 <= p[0] + p[1] && t2 <= p[2]) {
            return true;
        }

        return false;
    }

    TreeMap<Integer, Integer> ts;

    void remove(int num) {
        ts.merge(num, -1, (x, y) -> x + y);

        if (ts.get(num) <= 0) {
            ts.remove(num);
        }
    }
}
