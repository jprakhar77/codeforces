package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DALotOfGames {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int k = in.nextInt();

        String[] sa = new String[n];

        for (int i = 0; i < n; i++) {
            sa[i] = in.next();
        }

        Arrays.sort(sa, (s1, s2) -> s2.length() - s1.length());

        Set<String> m = new HashSet<>();

        Set<String> ns = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (m.contains(sa[i])) {

            } else {
                ns.add(sa[i]);

                for (int j = 0; j < sa[i].length(); j++) {
                    m.add(sa[i].substring(0, j + 1));
                }
            }
        }

        m.clear();
        for (String s : ns) {
            if (s.length() % 2 == 0) {
                for (int j = 0; j < s.length(); j++) {
                    m.add(s.substring(0, j + 1));
                }
            }
        }

        Set<String> inv = new HashSet<>();
        for (String s : ns) {
            if (s.length() % 2 == 1) {
                for (int j = s.length() - 1; j >= 0; j--) {
                    if (j % 2 != 0)
                        continue;

                    String sub = s.substring(0, j + 1);

                    if (m.contains(sub)) {
                        inv.add(sub);
                        break;
                    }
                }
            }
        }

        int e = 0;
        int o = 0;
        for (String s : ns) {
            boolean poss = true;

            for (int j = 0; j < s.length(); j++) {
                String sub = s.substring(0, j + 1);

                if (inv.contains(sub)) {
                    poss = false;
                    break;
                }
            }

            if (poss) {
                if (s.length() % 2 == 0) {
                    e++;
                } else {
                    o++;
                }
            }
        }

        if (o > 0 && e > 0) {
            out.println("First");
        } else if (o > 0) {
            if (k % 2 == 0) {
                out.println("Second");
            } else {
                out.println("First");
            }
        }else if (e > 0)
        {
            out.println("Second");
        }else {
            out.println("Second");
        }
    }
}
