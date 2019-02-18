package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CMahmoudAndEhabAndTheWrongAlgorithm {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        if (n <= 5) {
            out.println(-1);
        } else {
            if (n % 2 == 0) {
                for (int i = 2; i <= n / 2 + 1; i++) {
                    out.println("1 " + i);
                }

                for (int i = n / 2 + 2; i <= n; i++) {
                    out.println("2 " + i);
                }
            } else {
                for (int i = 2; i <= n / 2 + 1; i++) {
                    out.println("1 " + i);
                }

                for (int i = n / 2 + 2; i <= n; i++) {
                    out.println("2 " + i);
                }
            }
        }

        for (int i = 1; i < n; i++) {
            out.println(i + " " + (i + 1));
        }

    }
}
