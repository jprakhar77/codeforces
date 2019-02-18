package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ABestSubsegment {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n
        );

        int max = 0;

        for (int i = 0; i < n; i++)
        {
            max = Math.max(max, a[i]);
        }

        int ans = 1;

        int ca = 0;
        for (int i = 0 ; i < n; i++)
        {
            if(a[i] == max)
            {
                ca++;
                ans = Math.max(ans, ca);
            }else {
                ca = 0;
            }
        }

        out.println(ans);
    }
}
