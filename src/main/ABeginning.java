package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class ABeginning {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int[] a = new int[4];

        for (int i = 0; i < 4; i++) {
            a[i] = in.nextInt();
        }

        Arrays.sort(a);

        if (a[0] == 1 && a[1] == 4 && a[2] == 7 && a[3] == 9) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
