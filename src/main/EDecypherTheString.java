package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class EDecypherTheString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();

        out.println("? " + s);
        out.flush();

        String a = in.next();

        out.println("? " + a);
        out.flush();

        String b = in.next();

        out.println("? " + b);
        out.flush();

        String c = in.next();

        if (c.equals(s)) {
            out.println("! " + b);
            out.flush();
        } else {//if (b.equals(s)) {
            out.println(a);
        out.flush();
    }
//        } else {
//            out.println(s);
//            out.flush();
//        }
    }
}
