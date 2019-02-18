package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _166C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int x = in.nextInt();

        int[] c = new int[100001];

        for (int i = 0; i < n; i++) {
            c[in.nextInt()]++;
        }

        int ans = 0;
        if (c[x] == 0) {
            ans++;
            c[x]++;
        }

        int nb = 0;
        int na = 0;
        for (int i = 0; i < x; i++) {
            na += c[i];
        }

        for (int i = 100000; i > x; i--) {
            nb += c[i];
        }

        int mini = na + 1;
        int maxi = n - nb;

        int medi = (n + 1) / 2;

        if (medi >= mini && medi <= maxi) {
            out.println(ans);
            return;
        } else if (medi < mini) {
            ans += (na - (nb + c[x] - 1));
        } else {
            ans += (nb - (na + c[x] - 1) - 1);
        }

        out.println(ans);
    }
}
