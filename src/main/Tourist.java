package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class Tourist {
    class attraction {
        String s;
        int p;

        public attraction(String s, int p) {
            this.s = s;
            this.p = p;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long v = in.nextLong();

        attraction[] attractions = new attraction[n];

        for (int i = 0; i < n; i++) {
            attractions[i] = new attraction(in.next(), i);
        }

        List<attraction> attractionList = new ArrayList<>();

        int startIndex = (int) (((v - 1) * k) % n);

        for (int i = startIndex, j = 0; j < k; i = (i + 1) % n, j++) {
            attractionList.add(attractions[i]);
        }

        attractionList.sort((a1, a2) -> a1.p - a2.p);

        out.print("Case #");
        out.print(testNumber);
        out.print(": ");
        for (int i = 0; i < attractionList.size(); i++) {
            out.print(attractionList.get(i).s);
            out.print(" ");
        }
        out.println();
    }
}
