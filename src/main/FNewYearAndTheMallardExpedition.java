package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class FNewYearAndTheMallardExpedition {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long[] a = in.nextLongArray(n);

        String s = in.next();

        if (s.charAt(n - 1) != 'L') {
            s = s + 'L';
            long[] b = new long[n + 1];
            System.arraycopy(a, 0, b, 0, n);
            n++;
            a = b;
        }

        long[] g = new long[n];
        long[] w = new long[n];
        long[] l = new long[n];

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == 'G') {
                g[i] = a[i];
            } else if (ch == 'W') {
                w[i] = a[i];
            } else {
                l[i] = a[i];
            }
        }

        long[] preG = in.calculatePrefixSum(g);
        long[] preW = in.calculatePrefixSum(w);

        long[] rem = new long[n];

        long cur = 0;
        long curW = 0;
        long curG = 0;

        long ans = 0;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == 'G' || ch == 'W') {
                cur += a[i];
                if (ch == 'G') {
                    curG += a[i];
                    ans += 5 * a[i];
                } else {
                    curW += a[i];
                    ans += 3 * a[i];
                }
            } else {
                long req = l[i];

                ans += a[i];
                if (cur >= req) {
                    cur -= req;
                } else {
                    long stam = req - cur;
                    if (curW > 0) {
                        ans += 3 * stam;
                    } else {
                        ans += 5 * stam;
                    }
                    cur = 0;
                }

                rem[i] = cur;
            }
        }

        int[] minrem = new int[n];

        long curmin = Long.MAX_VALUE;
        int curminind = -1;

        int lil = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'L') {
                if (lil == -1) {
                    lil = i;
                }
                minrem[i] = curminind;
                if (rem[i] < curmin) {
                    curmin = rem[i];
                    curminind = i;
                }
            }
        }

        List<Integer> ll = new ArrayList<>();

        while (curminind != -1) {
            ll.add(curminind);
            curminind = minrem[curminind];
        }

        long totstam = rem[ll.get(ll.size() - 1)];

        long[] remd = rem.clone();

        for (int i = ll.size() - 1; i > 0; i--) {
            remd[ll.get(i)] -= remd[ll.get(i - 1)];
        }

        for (int i = 0; i < ll.size(); i++) {
            curminind = ll.get(i);
            int prev = -1;
            long base = 0;
            if (i > 0) {
                prev = ll.get(i - 1);
                base = rem[prev];
            }

            long req = base + remd[curminind];
            rem[curminind] = req;
            long minus = 0;
            for (int j = prev + 1; j < curminind; j++) {
                if (s.charAt(j) == 'G') {
                    long stam = preG[j] + preW[j];

                    if (prev >= 0) {
                        stam -= preG[prev];
                        stam -= preW[prev];

                        stam += base;
                    }

                    long cl = Math.min(2 * a[j], stam - minus);

                    if (cl >= req) {
                        ans -= 2 * req;
                        minus += req;
                        req = 0;
                        rem[curminind] = 0;
                        break;
                    } else {
                        ans -= 2 * cl;
                        req -= cl;
                        minus += cl;
                        rem[curminind] -= cl;
                    }
                }
            }
        }

        if (rem[n - 1] > 0) {
            ans -= rem[n - 1];
        }
//        boolean gnm = false;
//
//        o:
//        for (int i = ll.size() - 1; i >= 0; i--) {
//            curminind = ll.get(i);
//            int prev = -1;
//            if (i > 0) {
//                prev = ll.get(i - 1);
//            }
//
//            long ex = rem[curminind];
//
//            if (prev != -1) {
//                ex -= rem[prev];
//            }
//
//            for (int j = curminind - 1; j > prev && !gnm; j--) {
//                char ch = s.charAt(j);
//
//                long gl = preG[j];
//                long wl = preW[j];
//
//                //long cl = Math.min(a[j], wl + gl);
//                long cl = 2 * a[j];
//
//                long val = preW[j] + preG[j];
//                if (prev >= 0) {
//                    val -= preG[prev];
//                    val -= preW[prev];
//                    val += rem[prev];
//                }
//
//                cl = Math.min(cl, val);
//
//                if (ch == 'G') {
//                    if (cl < 2 * a[j]) {
//                        gnm = true;
//                    }
//                    if (cl >= totstam) {
//                        ans -= 2 * totstam;
//                        break o;
//                    } else {
//                        ans -= 2 * cl;
//                        totstam -= cl;
//                    }
//                }
//            }
//
//            long stam = totstam - (prev >= 0 ? rem[prev] : 0);
//
//            if (stam > 0)
//                for (int j = curminind - 1; j > prev; j--) {
//                    if (s.charAt(j) == 'W') {
//                        if (a[j] >= stam) {
//                            ans -= stam;
//                            totstam -= stam;
//                            break;
//                        } else {
//                            ans -= a[j];
//                            stam -= a[j];
//                            totstam -= a[j];
//                        }
//                    }
//                }
//        }

//        int prevind = -1;
//
//        long exhaust = 0;
//        while (curminind != -1) {
//            long wl = preW[curminind];
//            long gl = preG[curminind];
//
//            if (prevind >= 0 && prevind + 1 == curminind) {
//                continue;
//            }
//
//            if (prevind >= 0) {
//                wl -= preW[prevind];
//                gl -= preG[prevind];
//            }
//
//            curmin = rem[curminind];
//
//            curmin -= exhaust;
//
//            ans -= solve(curmin, wl, gl);
//
//            exhaust = rem[curminind];
//
//            prevind = curminind;
//            curminind = minrem[curminind];
//        }
//
//
//        if (lil < n - 1) {
//            long wl = preW[n - 1];
//            long gl = preG[n - 1];
//
//            if (prevind >= 0) {
//                wl -= preW[prevind];
//                gl -= preG[prevind];
//            }
//
//            ans -= solve(wl + gl, wl, gl);
//        }

        out.println((long) ans);
    }

    double solve(long stam, long wat, long gra) {
        double stam2 = (double) stam / 2;

        double ca = 0;

        if (gra >= stam2) {
            ca += 4 * stam2;
        } else {
            ca += 4 * gra;
            stam2 -= gra;
        }

        if (wat >= stam2) {
            ca += 2 * stam2;
        } else {
            ca += 2 * wat;
            stam2 -= wat;
        }

        return ca;
    }
}
