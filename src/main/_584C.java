package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _584C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int t = in.nextInt();

        String s1 = in.next();
        String s2 = in.next();

        int diff = 0;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }

        int minDiff = (diff + 1) / 2;

        if (t < minDiff) {
            out.println(-1);
            return;
        }

        if (t <= diff) {
            int bothSize = 0;
            int oneSize = 0;

            for (int i = 0; i <= diff; i++) {
                if ((diff - i) % 2 != 0)
                    continue;

                int exans = i + (diff - i) / 2;

                if (exans == t) {
                    bothSize = i;
                    oneSize = (diff - bothSize);
                    break;
                }
            }

            StringBuilder ans = new StringBuilder(s1);

            for (int i = 0; i < n; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    if (bothSize > 0) {
                        for (int j = 0; j < 26; j++) {
                            char ch = (char) (j + 'a');

                            if (ch != s1.charAt(i) && ch != s2.charAt(i)) {
                                ans.setCharAt(i, ch);
                                bothSize--;
                                break;
                            }
                        }
                    } else {
                        if (oneSize % 2 == 0) {
                            ans.setCharAt(i, s1.charAt(i));
                            oneSize--;
                        } else {
                            ans.setCharAt(i, s2.charAt(i));
                            oneSize--;
                        }
                    }
                }
            }

            out.println(ans);
        } else {
            int sameSize = t - diff;

            StringBuilder ans = new StringBuilder(s1);

            for (int i = 0; i < n; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    for (int j = 0; j < 26; j++) {
                        char ch = (char) (j + 'a');

                        if (ch != s1.charAt(i) && ch != s2.charAt(i)) {
                            ans.setCharAt(i, ch);
                            break;
                        }
                    }
                } else {
                    if (sameSize > 0) {
                        for (int j = 0; j < 26; j++) {
                            char ch = (char) (j + 'a');

                            if (ch != s1.charAt(i) && ch != s2.charAt(i)) {
                                ans.setCharAt(i, ch);
                                sameSize--;
                                break;
                            }
                        }
                    }
                }

            }

            out.println(ans);
        }
    }
}
