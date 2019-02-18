package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _980D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int cp = 0;
        Map<Integer, Integer> pm = new HashMap<>();

        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            int j = 2;

            int f = 1;
            int cn = (int) Math.abs(a[i]);

            for (; j * j <= cn; j++) {
                while (cn % j == 0) {
                    if (f % j == 0) {
                        f /= j;
                    } else {
                        f *= j;
                    }

                    cn /= j;
                }
            }

            if (cn > 1) {
                if (f % cn == 0)
                    f /= cn;
                else
                    f *= cn;
            }

            int key = (f << 1) + ((a[i] < 0) ? 1 : 0);

            if (!pm.containsKey(key)) {
                pm.put(key, cp);
                b[i] = cp;
                cp++;
            } else {
                b[i] = pm.get(key);
            }

        }

        int[][] dis = new int[n][n];

        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                if (a[j] != 0)
                    set.add(b[j]);
                dis[i][j] = Math.max(1, set.size());
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {

            long ans = 0;
            int sp = 0;
            int fp = 0;

            int j = 0;
            for (; j < n; j++) {

                if (dis[0][j] == i)
                    break;

            }

            if (j == n) {
                append(0, sb);
                continue;
            }

            while (fp < j && dis[fp][j] == i)
                fp++;

            if (fp > 0 && dis[fp][j] < i)
                fp--;


            if (dis[sp][j] == i && dis[fp][j] == i)
                ans += (fp + 1);

            j++;
            if (j == n) {
                append(ans, sb);
                continue;
            }

            for (; j < n; j++) {
                while (sp < j && dis[sp][j] > i)
                    sp++;

                while (fp < j && dis[fp][j] >= i)
                    fp++;

                if (fp > 0 && dis[fp][j] < i)
                    fp--;

                if (dis[sp][j] != i || dis[fp][j] != i)
                    continue;

                ans += (fp - sp + 1);
            }


            append(ans, sb);
        }

        out.println(sb.toString());
    }

    void append(long ans, StringBuilder sb) {
        sb.append(ans);
        sb.append(" ");
    }
}
