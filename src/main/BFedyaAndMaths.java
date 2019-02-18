package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.math.BigInteger;

public class BFedyaAndMaths {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        String n = in.next();

        int sz = n.length();

        BigInteger bg = new BigInteger(n);

        BigInteger mod5 = bg.mod(BigInteger.valueOf(4));

        BigInteger mod2 = bg.mod(BigInteger.valueOf(2));

        int m5 = mod5.intValue();
        int m2 = mod2.intValue();

        if (sz == 1 && n.charAt(0) == '0') {
            out.println(4);
            return;
        } else {
            int[] _3 = {1, 3, 4, 2};
            int[] _2 = {1, 2, 4, 3};

            int[] _4 = {1, 4};

            int ans = 0;

            ans++;

            ans += _3[m5];
            ans += _2[m5];
            ans += _4[m2];

            out.println(ans % 5);
        }
    }
}
