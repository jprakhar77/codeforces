package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class CMagicShip {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        long x1 = in.ni();
        long y1 = in.ni();

        long x2 = in.ni();
        long y2 = in.ni();

        if (x1 == x2 && y1 == y2) {
            out.println(0);
            return;
        }

        int n = in.nextInt();

        String s = in.next();


//        Set<Character> dr = new HashSet<>();
//
//        if (x2 > x1) {
//            dr.add('R');
//        } else if (x2 < x1) {
//            dr.add('L');
//        }
//
//        if (y2 > y1) {
//            dr.add('U');
//        } else if (y2 < y1) {
//            dr.add('D');
//        }

//        boolean poss = false;
//        for (int i = 0; i < n; i++) {
//            if (dr.contains(s.charAt(i))) {
//                poss = true;
//                break;
//            }
//        }
//
//        if (!poss) {
//            out.println(-1);
//            return;
//        }

        long px = x1;
        long py = y1;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == 'U') {
                py++;
            } else if (ch == 'D') {
                py--;
            } else if (ch == 'L') {
                px--;
            } else if (ch == 'R') {
                px++;
            }


        }

        long dx = px - x1;
        long dy = py - y1;

        long cx = x1;
        long cy = y1;

        long ans = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == 'U') {
                cy++;
            } else if (ch == 'D') {
                cy--;
            } else if (ch == 'L') {
                cx--;
            } else if (ch == 'R') {
                cx++;
            }

            long md = Math.abs(x2 - cx) + Math.abs(y2 - cy);

            if (md <= i + 1) {
                ans = Math.min(ans, i + 1);
                continue;
            }

            long max = (long) 2e9;

            long lo = 1;
            long hi = (long) 2e9;

            long ca = max + 1;

            while (lo <= hi) {
                long mid = lo + (hi - lo) / 2;

                long nx = cx + mid * dx;
                long ny = cy + mid * dy;

                long nmd = Math.abs(x2 - nx) + Math.abs(y2 - ny);

                long time = i + 1 + n * mid;

                if (nmd <= time) {
                    ca = Math.min(ca, mid);
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            if (ca < max + 1)
                ans = Math.min(ans, i + 1 + ca * n);
        }

        if (ans == Long.MAX_VALUE) {
            ans = -1;
        }
        out.println(ans);
    }


}
