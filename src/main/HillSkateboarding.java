package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;

public class HillSkateboarding {
    class num {
        int i;
        int num;

        public num(int i, int num) {
            this.i = i;
            this.num = num;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int[] sum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1];

            switch (a[i - 1]) {
                case 0:
                    sum[i] -= 1;
                    break;
                case 1:
                    sum[i] -= 2;
                    break;
                case -1:
                    sum[i] += 2;
                    break;
            }
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.addFirst(n);

        int[] next = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && sum[stack.getFirst()] >= sum[i]) {
                stack.removeFirst();
            }
            if (stack.isEmpty()) {
                next[i] = n;
            } else {
                next[i] = stack.getFirst();
            }
            stack.addFirst(i);
        }

        double ans = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] == -1) {
                int cv = sum[i];
                int fv = sum[next[i]];

                int fi = i;
                if (fv < cv) {
                    int sfv = sum[next[i] - 1];
                    if (sfv > cv) {
                        ans = Math.max(ans, next[i] - i - 0.5);
                    } else {
                        ans = Math.max(ans, next[i] - i - 1);
                    }
                } else {
                    ans = Math.max(ans, next[i] - i);
                }
            }
        }

        out.println(ans);

    }
}
