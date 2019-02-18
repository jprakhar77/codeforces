package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class CTheFairNutAndString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();

        List<Integer> l = new ArrayList<>();

        int ac = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                ac++;
            } else if (s.charAt(i) == 'b') {
                if (ac > 0) {
                    l.add(ac);
                }
                ac = 0;
            }
        }

        if (ac > 0) {
            l.add(ac);
        }

        long ans = 1;

        for (int i = 0; i < l.size(); i++) {
            ans *= (l.get(i) + 1);
            ans %= mod;
        }

        ans--;
        ans %= mod;

        if (ans < 0) {
            ans += mod;
        }

        out.println(ans);
    }

    int mod = (int) 1e9 + 7;
}
