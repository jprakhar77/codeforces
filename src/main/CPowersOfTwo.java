package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Comparator;
import java.util.PriorityQueue;

public class CPowersOfTwo {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        if (k > n) {
            out.print("NO");
            return;
        }
        int min = 0;

        int cn = n;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        while (cn > 0) {
            int cp = 0;

            while (cn >= (1 << cp)) {
                cp++;
            }

            cp--;

            pq.add(1 << cp);

            cn -= (1 << cp);

            min++;
        }


        if (k < min) {
            out.print("NO");
            return;
        }


        int ck = min;

        while (ck < k) {
            int ce = pq.poll();

            pq.add(ce / 2);
            pq.add(ce / 2);

            ck++;
        }


        out.println("YES");

        while (!pq.isEmpty()) {
            out.print(pq.poll() + " ");
        }


    }
}
