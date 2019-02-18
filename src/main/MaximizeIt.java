package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class MaximizeIt {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();

            int[] a = in.nextIntArray(n);

            int lo = 0;
            int hi = n - 2;

            int ans = 0;
            boolean iss = false;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                int[] numl = new int[100002];
                int[] numr = new int[100002];

                for (int i = 0; i <= mid; i++) {
                    numl[a[i]]++;
                }

                for (int i = mid + 1; i < n; i++) {
                    numr[a[i]]++;
                }

                int car = 0;
                for (int i = 0; i <= 100001; i++) {
                    int val = numl[i] + car;

                    car = val / k;
                    numl[i] = val % k;
                }

                car = 0;
                for (int i = 0; i <= 100001; i++) {
                    int val = numr[i] + car;

                    car = val / k;
                    numr[i] = val % k;
                }

                int com = 0;
                for (int i = 100001; i >= 0; i--) {
                    if (numl[i] != numr[i]) {
                        com = (int) Math.signum(numl[i] - numr[i]);
                        break;
                    }
                }

                if (com <= 0) {
                    ans = mid;
                    if (com == 0) {
                        iss = true;
                    } else {
                        iss = false;
                    }
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            if (iss || ans == n - 2) {
                out.println(ans + 1);
            } else {
                int[] numl = new int[100002];
                int[] numr = new int[100002];

                for (int i = 0; i <= ans; i++) {
                    numl[a[i]]++;
                }

                for (int i = ans + 1; i < n; i++) {
                    numr[a[i]]++;
                }

                int car = 0;
                for (int i = 0; i <= 100001; i++) {
                    int val = numl[i] + car;

                    car = val / k;
                    numl[i] = val % k;
                }

                car = 0;
                for (int i = 0; i <= 100001; i++) {
                    int val = numr[i] + car;

                    car = val / k;
                    numr[i] = val % k;
                }

                int[] d1 = new int[100002];

                for (int i = 100001; i >= 0; i--) {
                    d1[i] = numr[i] - numl[i];
                }

                numl = new int[100002];
                numr = new int[100002];

                for (int i = 0; i <= ans + 1; i++) {
                    numl[a[i]]++;
                }

                for (int i = ans + 2; i < n; i++) {
                    numr[a[i]]++;
                }

                car = 0;
                for (int i = 0; i <= 100001; i++) {
                    int val = numl[i] + car;

                    car = val / k;
                    numl[i] = val % k;
                }

                car = 0;
                for (int i = 0; i <= 100001; i++) {
                    int val = numr[i] + car;

                    car = val / k;
                    numr[i] = val % k;
                }

                int[] d2 = new int[100002];

                for (int i = 100001; i >= 0; i--) {
                    d2[i] = numl[i] - numr[i];
                }

                for (int i = 100001; i >= 0; i--) {
                    if (d1[i] != d2[i]) {
                        if (d2[i] < d1[i]) {
                            ans++;
                            break;
                        } else {
                            break;
                        }
                    }
                }

                out.println(ans + 1);
            }
        }
    }
}
