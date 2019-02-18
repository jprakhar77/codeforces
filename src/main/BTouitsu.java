package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class BTouitsu {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        String a = in.next();
        String b = in.next();
        String c = in.next();

        int ans = 0;
        for (int i = 0; i < n; i++) {
            Set<Character> cs = new HashSet<>();

            cs.add(a.charAt(i));
            cs.add(b.charAt(i));
            cs.add(c.charAt(i));

            if (cs.size() == 2) {
                ans++;
            } else if (cs.size() == 3) {
                ans += 2;
            }
        }

        out.println(ans);
    }
}
