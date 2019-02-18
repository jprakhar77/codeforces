package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.PriorityQueue;

public class _767E {
    class ele {
        int count;
        int weigth;

        public ele(int count, int weigth) {
            this.count = count;
            this.weigth = weigth;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        long[] c = new long[n];

        for (int i = 0; i < n; i++) {
            c[i] = in.nextInt();
        }

        long[] w = new long[n];

        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
        }

        int cm = m;

        PriorityQueue<ele> pq = new PriorityQueue<>((e1, e2) ->

                e1.weigth - e2.weigth
        );

        long[] ans = new long[n];

        for (int i = 0; i < n; i++) {
            long val = c[i] % 100;
            if (cm < val) {
                int w1 = inf;
                if (pq.size() > 0) {
                    //w1 =
                }
            } else {
                ans[i] = c[i];
            }
        }
    }

    int inf = 10000000;
}
