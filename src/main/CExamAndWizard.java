package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.PriorityQueue;

public class CExamAndWizard {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long[] a = in.nextLongArray(n);
        long[] b = in.nextLongArray(n);

        PriorityQueue<Long> pq = new PriorityQueue<>((x, y) -> (int) Math.signum(y - x));

        long req = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] < b[i]) {
                req += b[i] - a[i];
                ans++;
            } else if (a[i] > b[i]) {
                pq.add(a[i] - b[i]);
            }
        }

        while (req > 0 && !pq.isEmpty()) {
            long ce = pq.poll();

            req -= ce;
            ans++;
        }

        if (req > 0) {
            out.println(-1);
        } else {
            out.println(ans);
        }

    }
}
