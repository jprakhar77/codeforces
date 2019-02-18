package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AEhabAndAnotherConstructionProblem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x = in.nextInt();

        for (int a = 1; a<=x;a++)
        {
            for (int b = 1; b <= x; b++)
            {
                if (a % b == 0 && a * b > x && a / b < x)
                {
                    out.println(a + " " + b);
                    return;
                }
            }
        }

        out.println(-1);
    }
}
