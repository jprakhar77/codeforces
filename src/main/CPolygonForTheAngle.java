package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CPolygonForTheAngle {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        o:
        while (t-- > 0) {
            int a = in.nextInt();

//            long lo = 3;
//            long hi = 998244354;
//
//            long ans = 998244354;
//            while (lo <= hi) {
//                long mid = (lo + hi) / 2;
//
//                if ((mid * a) % 180 == 0) {
//                    ans = mid;
//                    hi = mid - 1;
//                } else {
//                    lo = mid + 1;
//                }
//            }
//
//            if (ans == 998244354) {
//                out.println(-1);
//            } else {
//                out.println(ans);
//            }

            for (int i = 3; i <= 360; i++) {
                if ((i * a) % 180 == 0) {

                    int ct = (a * i) / 180;

                    if (ct > i - 2) {
                        continue;
                    }
                    out.println(i);
                    continue o;
                    //out.println(i);
                    //continue o;
                }
            }

            out.println(-1);
        }
    }
}
