package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SwappingPositions {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int tc = in.nextInt();

        o:
        while (tc-- > 0) {
            int n = in.nextInt();
            String s = in.next();
            String t = in.next();

            int c = 0;
            Set<Character> set = new HashSet<>();

            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    c++;
                    set.add(s.charAt(i));
                    set.add(t.charAt(i));
                    l.add(i);
                }
            }

            if (c > 3) {
                out.println("NO");
            } else if (c == 3) {
                for (int i = 0; i < 3; i++) {
                    for (int j = i + 1; j < 3; j++) {
                        set.clear();

                        int ind1 = l.get(i);
                        int ind2 = l.get(j);

                        set.add(s.charAt(ind1));
                        set.add(t.charAt(ind1));
                        set.add(s.charAt(ind2));
                        set.add(t.charAt(ind2));

                        if (set.size() <= 2) {
                            out.println("YES");
                            continue o;
                        }
                    }
                }

                out.println("NO");
            } else if (c == 1) {
                out.println("YES");
            } else {
                if (set.size() <= 3) {
                    out.println("YES");
                } else {
                    out.println("NO");
                }
            }
        }
    }
}
