package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class GLongestPath {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        List[] to = new List[n];
        Set[] from = new Set[n];
        List[] from2 = new List[n];

        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList();
            from[i] = new HashSet();
            from2[i] = new ArrayList();
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();

            x--;
            y--;

            from[y].add(x);
            from2[y].add(x);
            to[x].add(y);
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (from[i].isEmpty()) {
                q.addLast(i);
            }
        }

        int[] dp = new int[n];

        int ans = 0;

        while (!q.isEmpty()) {
            int pe = q.removeFirst();

            List<Integer> cl = to[pe];
            for (int i = 0; i < cl.size(); i++) {
                int ce = cl.get(i);

                from[ce].remove(pe);

                if (from[ce].isEmpty()) {
                    List<Integer> fl = from2[ce];

                    for (int j = 0; j < fl.size(); j++) {
                        dp[ce] = Math.max(dp[ce], dp[fl.get(j)] + 1);
                    }
                    ans = Math.max(ans, dp[ce]);
                    q.addLast(ce);
                }
            }
        }

        out.println(ans);
    }
}
