package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _949D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long d = in.nextInt();
        int b = in.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        long left = 0, right = 0;

        int midp = (n - 1) / 2;

        for (int i = 0; i <= midp; i++)
            left += a[i];

        for (int i = midp + 1; i < n; i++)
            right += a[i];

        long tot = left + right;

        long lo = -right;
        long hi = left;

        long ans = n;
        while (lo <= hi) {
            long mid = (lo + hi) / 2;

            long[] prefix = new long[n];
            long[] suffix = new long[n];

            if (mid <= 0) {

                prefix[0] = a[0];

                long rem = left - mid;
                rem -= prefix[0];

                for (int i = 1; i < n; i++) {
                    long ele = -1;
                    if (a[i] <= rem) {
                        prefix[i] = a[i] + prefix[i - 1];
                        rem -= a[i];
                    } else {
                        prefix[i] = rem + prefix[i - 1];
                        rem = 0;
                    }
                }

                rem = right + mid;
                if (rem >= a[n - 1]) {
                    suffix[n - 1] = a[n - 1];
                    rem -= a[n - 1];
                } else {
                    suffix[n - 1] = rem;
                    rem = 0;
                }
                for (int i = n - 2; i >= 0; i--) {
                    if (a[i] <= rem) {
                        suffix[i] = suffix[i + 1] + a[i];
                        rem -= a[i];
                    } else {
                        suffix[i] = suffix[i + 1] + rem;
                        rem = 0;
                    }
                }
            } else {
                suffix[n - 1] = a[n - 1];

                long rem = right + mid;
                rem -= suffix[n - 1];

                for (int i = n - 2; i >= 0; i--) {
                    long ele = -1;
                    if (a[i] <= rem) {
                        suffix[i] = a[i] + suffix[i + 1];
                        rem -= a[i];
                    } else {
                        suffix[i] = rem + suffix[i + 1];
                        rem = 0;
                    }
                }

                rem = left - mid;
                if (rem >= a[0]) {
                    prefix[0] = a[0];
                    rem -= a[0];
                } else {
                    prefix[0] = rem;
                    rem = 0;
                }
                for (int i = 1; i < n; i++) {
                    if (a[i] <= rem) {
                        prefix[i] = prefix[i - 1] + a[i];
                        rem -= a[i];
                    } else {
                        prefix[i] = prefix[i - 1] + rem;
                        rem = 0;
                    }
                }
            }

            long prem = 0;
            long x1 = 0, x2 = 0;
            long x1c = 0, x2c = 0;
            for (int i = 0; i <= midp; i++) {
                long ft = prefix[(int) Math.min(i + d * (i + 1), n - 1)];
                ft -= x1c * b;
                if (ft >= b) {
                    x1c++;
                } else {
                    x1++;
                }
            }

            for (int i = n - 1; i > midp; i--) {
                long ft = suffix[(int) Math.max(i - d * (n - i), 0)];
                ft -= x2c * b;
                if (ft >= b) {
                    x2c++;
                } else {
                    x2++;
                }
            }

            ans = Math.min(ans, Math.max(x1, x2));

            if (x1 < x2) {
                lo = mid + 1;
            } else if (x1 > x2) {
                hi = mid - 1;
            } else {
                break;
            }
        }

        out.println(ans);
    }
}
