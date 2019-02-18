package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ASashaAndABitOfRelax {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        int[] xor = new int[1 << 20];
        int[] xor2 = new int[1 << 20];

        xor[0]++;
        int cx = 0;

        long ans = 0;
        for (int i = 0; i < n; i++) {
            cx ^= a[i];

            if (i % 2 == 1)
                ans += xor[cx];
            else ans += xor2[cx];

            if (i % 2 == 1)
                xor[cx]++;
            else xor2[cx]++;
        }

        out.println(ans);
    }
}
