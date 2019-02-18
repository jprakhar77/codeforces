package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ADuffAndMeat {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];
        int[] p = new int[n];

        long ans = 0;
        long minp = 1000000;
        for (int i = 0; i < n; i++)
        {
            int ca = in.nextInt();
            int cp  =in.nextInt();

            minp = Math.min(minp, cp);

            ans += minp * ca;
        }

        out.println(ans);
    }
}
