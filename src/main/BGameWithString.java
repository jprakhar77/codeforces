package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;

public class BGameWithString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();

        ArrayDeque<Character> dq = new ArrayDeque<>();

        dq.addLast(s.charAt(0));

        int ans = 0;
        for (int i = 1; i < n; i++) {
            dq.addLast(s.charAt(i));

            while (dq.size() > 1) {
                char le = dq.removeLast();
                char sle = dq.getLast();

                if (le == sle) {
                    dq.removeLast();
                    ans++;
                } else {
                    dq.addLast(le);
                    break;
                }
            }
        }

        if (ans % 2 == 0) {
            out.println("No");
        } else {
            out.println("Yes");
        }
    }
}
