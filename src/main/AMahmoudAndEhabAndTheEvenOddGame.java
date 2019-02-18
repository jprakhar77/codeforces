package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AMahmoudAndEhabAndTheEvenOddGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        if (n % 2 == 0) {
            out.println("Mahmoud");
        } else {
            out.println("Ehab");
        }
    }
}
