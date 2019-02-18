package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BTheMonsterAndTheSquirrel {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextInt();

        long ans = n;
        ans *= (n - 3);
        ans -= (n - 3);
        ans++;
        out.println(ans);
    }
}
