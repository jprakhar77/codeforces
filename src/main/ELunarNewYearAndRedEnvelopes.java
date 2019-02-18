package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class ELunarNewYearAndRedEnvelopes {
    class enve
    {
        int s;
        int t;
        int d;
        long w;

        public enve(int s, int t, int d, long w) {
            this.s = s;
            this.t = t;
            this.d = d;
            this.w = w;
        }
    }
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int k = in.nextInt();

        List<enve> enves = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            int s = in.nextInt();
            int t = in.nextInt();
            int d = in.nextInt();
            long w = in.nextLong();

            enves.add(new enve(s, t, d, w));
        }

//        enves.sort((e1, e2) ->
//        {
//
//        });

    }
}
