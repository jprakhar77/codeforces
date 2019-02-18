package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class ASuperheroTransformation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        String s = in.next();

        String t = in.next();

        int n = s.length();
        int m = t.length();

        if (n != m) {
            out.println("No");
            return;
        }

        char[] a = new char[n];
        char[] b = new char[n];

        Set<Character> vow = new HashSet<>();

        vow.add('a');
        vow.add('e');
        vow.add('i');
        vow.add('o');
        vow.add('u');
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (vow.contains(ch)) {
                a[i] = 'a';
            } else {
                a[i] = 'b';
            }
        }


        for (int i = 0; i < n; i++) {
            char ch = t.charAt(i);

            if (vow.contains(ch)) {
                b[i] = 'a';
            } else {
                b[i] = 'b';
            }
        }

        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                out.println("No");
                return;
            }
        }

        out.println("Yes");
    }
}
