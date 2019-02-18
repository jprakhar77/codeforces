package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1063E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        out.println("0 1");
        out.flush();

        String lc = in.next();
        int rs = 1;
        int re = 1000000000;

        for (int i = 0; i < n - 1; i++) {
            int mid = (rs + re) / 2;

            out.println(mid + " " + 1);
            out.flush();

            String cc = in.next();

            if (cc.equals(lc)) {
                rs = mid + 1;
            } else {
                re = mid - 1;
            }
        }

        out.println((rs - 1) + " 2");
        out.println((rs) + " 0");
        out.flush();
    }
}
