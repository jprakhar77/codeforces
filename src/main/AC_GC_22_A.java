package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class AC_GC_22_A {

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int[] h = new int[26];

        int n = s.length();
        for (int i = 0; i < n; i++) {
            h[s.charAt(i) - 'a']++;
        }

        if (n == 26) {
            List<String> ss = new ArrayList<>();
            int[] ch = new int[26];
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);

                for (int j = c - 'a' + 1; j < 26; j++) {
                    if (ch[j] == 0) {
                        String cs = s.substring(0, i);
                        String ncs = new String(cs);
                        ncs += (char) (j + 'a');
                        ss.add(ncs);
                        break;
                    }
                }
                ch[c - 'a']++;
            }

            if (ss.isEmpty()) {
                out.println(-1);
            } else {
                ss.sort((s1, s2) -> s1.compareTo(s2));
                out.println(ss.get(0));
            }
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (h[i] == 0) {
                s += (char) (i + 'a');
                break;
            }
        }

        out.println(s);
    }
}
