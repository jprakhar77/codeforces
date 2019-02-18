package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class DVariousSushi {
    class susshi {
        int t;
        int d;

        public susshi(int t, int d) {
            this.t = t;
            this.d = d;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        List<susshi> susshis = new ArrayList<>();

        Set<Integer> maxt = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int t = in.nextInt();
            int d = in.nextInt();
            susshis.add(new susshi(t, d));
            maxt.add(t);
        }

        int max = maxt.size();

        max = Math.min(max, k);

        susshis.sort((s1, s2) -> s2.d - s1.d);

        Set<Integer> dt = new HashSet<>();

        PriorityQueue<susshi> pq = new PriorityQueue<>((s1, s2) -> s1.d - s2.d);

        long ds = 0;
        for (int i = 0; i < k; i++) {
            susshi cs = susshis.get(i);
            if (dt.contains(cs.t)) {
                pq.add(cs);
            }
            dt.add(cs.t);
            ds += cs.d;
        }

        long ans = ds + ((long) dt.size() * dt.size());

        for (int i = k; i < n; i++) {
            susshi cs = susshis.get(i);
            if (!dt.contains(cs.t)) {
                if (!pq.isEmpty()) {
                    susshi pc = pq.poll();

                    dt.add(cs.t);

                    ds -= pc.d;
                    ds += cs.d;
                }
            }

            ans = Math.max(ans, ds + ((long) dt.size() * dt.size()));
        }

        out.println(ans);
    }
}
