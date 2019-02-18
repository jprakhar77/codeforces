package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.math.BigInteger;

public class _710D {
    class ExtendedEuclid {

        class res {
            long x;
            long y;
            long g;

            public res(long x, long y, long g) {
                this.x = x;
                this.y = y;
                this.g = g;
            }
        }

        //a*x + b*y = g

        res gcd(long a, long b, res res) {
            if (b == 0) {
                return new res(1, 0, a);
            }

            res = gcd(b, a % b, res);

            res newres = new res(1, 1, res.g);

            newres.y = res.x - (a / b) * res.y;
            newres.x = res.y;

            return newres;
        }
    }

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a1 = in.nextInt();
        long b1 = in.nextInt();

        long a2 = in.nextInt();
        long b2 = in.nextInt();

        long l = in.nextInt();
        long r = in.nextInt();

        long bdiff = (long) Math.abs(b1 - b2);

        long base = Math.min(b1, b2);
        long minb = base;
        long maxb = Math.max(b1, b2);

        long gcd = gcd(a1, a2);
        long lcm = (a1 * a2) / gcd;

        if (bdiff != 0) {
            if (bdiff % gcd != 0) {
                out.println(0);
                return;
            }

            ExtendedEuclid ee = new ExtendedEuclid();

            ExtendedEuclid.res res = ee.new res(1L, 1L, bdiff);
            res = ee.gcd(a1, a2, res);

            long mul = bdiff / gcd;

            if (res.x > 0 && b1 > b2) {
                long times = a2 / gcd;
                long rt = times - 1;
                res.x *= rt;
                res.y *= rt;
                res.y++;
            } else if (res.y > 0 && b2 > b1) {
                long times = a1 / gcd;
                long rt = times - 1;
                res.x *= rt;
                res.y *= rt;
                res.x++;
            }
            BigInteger resx = BigInteger.valueOf(res.x);
            BigInteger resy = BigInteger.valueOf(res.y);
            resx = resx.multiply(BigInteger.valueOf(mul));
            resy = resy.multiply(BigInteger.valueOf(mul));
            res.x *= mul;
            res.y *= mul;
            res.g *= mul;

            if (resx.compareTo(BigInteger.ZERO) > 0) {
                resx = resx.multiply(BigInteger.valueOf(a1));
                resx = resx.add(BigInteger.valueOf(b1));

                BigInteger bbase = new BigInteger(resx.toString());
                if (resx.compareTo(BigInteger.valueOf(maxb)) > 0) {
                    resx = resx.subtract(BigInteger.valueOf(maxb));
                    resx = resx.divide(BigInteger.valueOf(lcm));
                    bbase = bbase.subtract(resx.multiply(BigInteger.valueOf(lcm)));
                    base = bbase.longValue();
                }
                base = bbase.longValue();
            } else if (res.y > 0) {
//                base = b2 + res.y * a2;
//                if (base > maxb) {
//                    long map = maxb;
//                    long cdiff = base - map;
//                    long div = cdiff / lcm;
//                    base -= div * lcm;
//                }
                resy = resy.multiply(BigInteger.valueOf(a2));
                resy = resy.add(BigInteger.valueOf(b2));

                BigInteger bbase = new BigInteger(resy.toString());
                if (resy.compareTo(BigInteger.valueOf(maxb)) > 0) {
                    resy = resy.subtract(BigInteger.valueOf(maxb));
                    resy = resy.divide(BigInteger.valueOf(lcm));
                    bbase = bbase.subtract(resy.multiply(BigInteger.valueOf(lcm)));
                }
                base = bbase.longValue();
            }
        }

        out.println(cal(a1, a2, base, lcm, l, r));
    }

    long norm(long num, long maxb, long lcm) {
        if (num > maxb) {
            long low = maxb;
            long cdiff = num - low;
            long div = cdiff / lcm;
            num -= div * lcm;
            return num;
        }
        return num;
    }

    long cal(long a1, long a2, long base, long lcm, long l, long r) {
        if (base < l) {
            long diff = l - base;

            long ceil = (diff + lcm - 1) / lcm;

            base += lcm * ceil;
        }

        if (base > r) {
            return 0;
        }

        long diff = r - base;

        long div = diff / lcm;

        return div + 1;
    }
}
