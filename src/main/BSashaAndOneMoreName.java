package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class BSashaAndOneMoreName {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        String s = in.next();

        int n = s.length();

        int[] h = new int[26];
        Set<Character> sc = new HashSet<>();
        for (int i = 0; i < n; i++) {
            sc.add(s.charAt(i));
            h[s.charAt(i) - 'a']++;
        }

        if (sc.size() == 1) {
            out.println("Impossible");
            return;
        }

        if (n % 2 == 1 && sc.size() == 2) {
            boolean fuck = false;
            if (h[s.charAt(n / 2) - 'a'] == 1) {
                for (int i = 0; i < 26; i++) {
                    if (h[i] == n - 1) {
                        fuck = true;
                    }
                }
            }
            if (fuck) {
                out.println("Impossible");
                return;
            }
        }

        int ans = 2;

        for (int i = 1; i < n; i++) {
            String ns = s.substring(i);
            ns += s.substring(0, i);

            if (ispalin(ns) && !s.equals(ns)) {
                ans = 1;
                break;
            }
        }

        out.println(ans);
    }

    boolean ispalin(String s) {
        int n = s.length();
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }

        return true;
    }
}
