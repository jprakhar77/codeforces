package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] a = in.nextIntArray(n);

        List<Long> al = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long csum = 0;
            for (int j = i; j < n; j++) {
                csum += a[j];

                al.add(csum);
            }
        }

        long ans = 0;

        for (int i = 43; i >= 0; i--) {
            int cnt = 0;
            for (long num : al) {
                if (((1l << i) & num) != 0) {
                    cnt++;
                }
            }

            if (cnt >= k) {
                ans |= (1l << i);
                List<Long> nal = new ArrayList<>();

                for (long num : al) {
                    if (((1l << i) & num) != 0) {
                        nal.add(num);
                    }
                }

                al = nal;
            }
        }

        out.println(ans);
    }
}
