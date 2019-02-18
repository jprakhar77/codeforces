package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CPostcard {
    String imp = "Impossible";

    boolean isspecial(char ch) {
        if (ch == '*' || ch == '?')
            return true;

        return false;
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();

        int k = in.nextInt();

        boolean cs = false;

        int min = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                cs = true;
            }
            
            if (isspecial(s.charAt(i)))
                continue;

            max++;

            if (i < n - 1 && isspecial(s.charAt(i + 1))) {

            } else {
                min++;
            }
        }

        if (min > k) {
            out.println(imp);
            return;
        }

        if (cs) {
            boolean done = false;

            StringBuilder ans = new StringBuilder();

            for (int i = 0; i < n; i++) {
                if (isspecial(s.charAt(i)))
                    continue;
                if (i < n - 1 && isspecial(s.charAt(i + 1))) {
                    if (s.charAt(i + 1) == '*' && !done) {
                        for (int j = 0; j < k - min; j++) {
                            ans.append(s.charAt(i));
                        }
                        done = true;
                    }
                } else {
                    ans.append(s.charAt(i));
                }
            }

            out.println(ans.toString());
        } else {
            if (max < k) {
                out.println(imp);
                return;
            }

            int rem = k - min;

            StringBuilder ans = new StringBuilder();

            for (int i = 0; i < n; i++) {
                if (isspecial(s.charAt(i)))
                    continue;
                if (i < n - 1 && isspecial(s.charAt(i + 1))) {
                    if (rem > 0) {
                        ans.append(s.charAt(i));
                        rem--;
                    }
                } else {
                    ans.append(s.charAt(i));
                }
            }

            out.println(ans.toString());
        }
    }
}
