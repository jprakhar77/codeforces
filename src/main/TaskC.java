package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.PriorityQueue;
import java.util.TreeSet;

public class TaskC {
    private long ans;

    class num {
        int i;
        int num;

        public num(int i, int num) {
            this.i = i;
            this.num = num;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        PriorityQueue<num> pq = new PriorityQueue<>((n1, n2) -> n1.num - n2.num);
        TreeSet<Integer> ts = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            pq.add(new num(i, a[i]));
            ts.add(i);
        }

        long ans = 0;
        int min = 0;
        while (pq.size() > 2) {
            num num = pq.poll();

            int av = num.num - min;
            ans += ((long)pq.size() -1)* av;

            min = num.num;

            ts.remove(num.i);

            Integer li = ts.floor(num.i);
            Integer ri = ts.ceiling(num.i);

            if (li != null && ri != null) {
                int lav = a[li] - min;
                int rav = a[ri] - min;

                ans += Math.min(lav, rav);
            }
        }

        out.println(ans);

    }
}
