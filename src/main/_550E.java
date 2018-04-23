package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _550E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        if (a[n - 1] != 0) {
            out.println("NO");
            return;
        }

        int fz = -1;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                fz = i;
                break;
            }
        }

        if (fz == n - 2) {
            out.println("NO");
            return;
        }

        StringBuilder sb = new StringBuilder();
        if (fz == n - 1) {
            for (int i = 0; i < n - 1; i++) {
                sb.append(a[i]);
                sb.append("->");
            }
            sb.append(a[n - 1]);
        } else {
            sb.append("(");
            for (int i = 0; i < fz; i++) {
                sb.append(a[i]);
                sb.append("->");
            }
            sb.append("(0->(");
            for (int i = fz + 1; i < n - 1; i++) {
                sb.append(a[i]);
                sb.append("->");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append(")))->0");
        }
        out.println("YES");
        out.println(sb.toString());
    }
}
