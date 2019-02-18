package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CPrimeNumber {
    class ele {
        long times;
        long mul;

        public ele(long times, long mul) {
            this.times = times;
            this.mul = mul;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long x = in.nextInt();

        int[] a = in.nextIntArray(n);


        int max = a[n - 1];

        Map<Integer, Integer> map = new HashMap<>();

        long tot = 0;
        for (int val : a) {
            tot += val;
            map.merge(val, 1, (x1, y) -> x1 + y);
        }

        PriorityQueue<ele> eles = new PriorityQueue<>((p1, p2) -> (int) Math.signum(p1.times - p2.times));


        for (int key : map.keySet()) {
            int val = map.get(key);

            int t = 0;
            while (val % x == 0) {
                val /= x;
                t++;
            }
            eles.add(new ele(tot - key + t, val));
        }

        long ans = eles.peek().times;

        long sum = 0;
        while (!eles.isEmpty()) {
            ele pe = eles.poll();

            if (pe.times == ans) {
                sum += pe.mul;
            } else {
                int t = 0;
                while (sum % x == 0) {
                    sum /= x;
                    t++;
                }

                if (t == 0) {
                    //out.println(Math.min(ans, tot));
                    //return;
                    break;
                }

                eles.add(pe);
                eles.add(new ele(ans + t, sum));
                ans = eles.peek().times;
                sum = 0;
            }
        }

        if (sum > 0) {
            int t = 0;
            while (sum % x == 0) {
                sum /= x;
                t++;
            }

            ans += t;
        }
        out.println(pow(x, Math.min(ans, tot), mod));

//        long sum = 0;
//
//        long max = -1;
//
//        int maxt = 0;
//
//        Map<Integer, Integer> map = new HashMap<>();
//
//        for (int val : a) {
//            sum += val;
//
//
//            map.merge(val, 1, (x1, y) -> x1 + y);
//            if (val > max) {
//                max = val;
//                maxt = 1;
//            } else if (val == max) {
//                maxt++;
//            }
//        }
//
//        sum -= max;
//
//        long ans = pow(x, sum, mod);
//
//        long gcd = map.get((int) max);
//
//        for (int key : map.keySet()) {
//            gcd = gcd(gcd, map.get(key));
//        }
//
//        long t = 0;
//
//        long tgcd = gcd;
//        while (gcd % x == 0) {
//            gcd /= x;
//            t++;
//        }
//
//        t = Math.min(t, max);
//
//        ans *= pow(x, t, mod);
//
//        ans %= mod;
//
//        //long mint = map.get(a[0]);
//
//        long cmax = maxt / tgcd;
//
//        long xt = 0;
//
//        long tcmax = cmax;
//        while (tcmax % x == 0) {
//            tcmax /= x;
//            xt++;
//        }
//
//        for (int key : map.keySet()) {
//            int val = map.get(key);
//
//            long min = val / tgcd;
//
//            long time = max - key;
//
////            for (int i = 0; i < Math.min(xt, time); i++) {
////                if (min < cmax) {
////                    min *= x;
////                } else break;
////            }
//
//            long cxt = 0;
//
//            while (min % x == 0) {
//                min /= x;
//                cxt++;
//            }
//
//            cxt += time;
//
//            xt = Math.min(xt, cxt);
//
//            //ngcd = gcd(ngcd, min);
//
//        }
//
////        long nt = 0;
////
////        while (ngcd % x == 0) {
////            ngcd /= x;
////            nt++;
////        }
////
//        xt = Math.max(0, Math.min(xt, max - t));
//
//        ans *= pow(x, xt, mod);
//
//        ans %= mod;
//
//        out.println(ans);
    }

    int mod = 1000000007;

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

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
