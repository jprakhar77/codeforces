package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BPerfectNumber {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int k = in.nextInt();

        int[] ans = new int[k];

        int ind = 0;
        for (int i = 1; ind < k; i++) {
            int sum = cal(i);

            if (sum == 10) {
                ans[ind] = i;
                ind++;
            }
        }

        out.println(ans[k - 1]);
    }

    private int cal(int i) {

        int sum = 0;

        while (i > 0) {
            sum += (i % 10);
            i /= 10;
        }

        return sum;
    }
}
