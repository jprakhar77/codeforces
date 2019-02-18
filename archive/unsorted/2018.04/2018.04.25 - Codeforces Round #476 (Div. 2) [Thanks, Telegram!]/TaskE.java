package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class TaskE {


    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        String[] sa = new String[n];

        for (int i = 0; i < n; i++) {
            sa[i] = in.next();
        }

        Arrays.sort(sa);

        int ans = 0;

        int pi = -1;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ans++;
                pi = 0;
                continue;
            }

            int j = 0;
            for (; j < sa[i].length(); j++) {
                if (j > pi || sa[i].charAt(j) > sa[i - 1].charAt(j))
                    break;
            }

            pi = j;
            ans += (j + 1);
        }

        out.println(ans);
    }
}
