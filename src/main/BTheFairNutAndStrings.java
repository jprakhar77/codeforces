package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BTheFairNutAndStrings {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        if (k == 1) {
            out.println(n);
            return;
        }

        String s = in.next();
        String t = in.next();

        long dis = 0;
        long ans = 0;
        boolean diff = false;
        for (int i = 0; i < n; i++) {
            if (dis == 0) {
                if (!diff) {
                    if (s.charAt(i) == t.charAt(i)) {
                        ans++;
                    } else {
                        ans += 2;
                        diff = true;
                    }
                } else {
                    if (s.charAt(i) == 'b' && t.charAt(i) == 'a') {
                        ans += 2;
                    } else if (s.charAt(i) == 'a' && t.charAt(i) == 'a') {
                        if (k >= 3) {
                            ans += 3;
                            dis = 1;
                        } else {
                            ans += k;
                        }
                    } else if (s.charAt(i) == 'b' && t.charAt(i) == 'b') {
                        if (k >= 3) {
                            ans += 3;
                            dis = 1;
                        } else {
                            ans += k;
                        }

                    } else {
                        if (k >= 4) {
                            ans += 4;
                            dis = 2;
                        } else {
                            ans += k;
                            dis = k - 2;
                        }
                    }
                }
            } else {
                long ndis = dis;
                if (s.charAt(i) == 'b' && t.charAt(i) == 'a') {
                    ndis *= 2;
                } else if (s.charAt(i) == 'a' && t.charAt(i) == 'a') {
                    ndis *= 2;
                    ndis++;
                } else if (s.charAt(i) == 'b' && t.charAt(i) == 'b') {
                    ndis *= 2;
                    ndis++;
                } else {
                    ndis *= 2;
                    ndis += 2;
                }

                if (ndis + 2 <= k) {
                    dis = ndis;
                    ans += ndis + 2;
                } else {
                    dis = k - 2;
                    ans += k;
                }
            }
        }

        out.println(ans);
    }
}
