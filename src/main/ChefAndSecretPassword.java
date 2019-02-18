package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ChefAndSecretPassword {
    class ZFunction {
        String s;
        int n;

        public ZFunction(String s) {
            this.s = s;
            this.n = s.length();
            this.z = new int[n];
            calculate();
        }

        int[] z;

        int[] calculate() {
            int leftMatch = 0;
            int rightMatch = 0;

            for (int i = 1; i < n; i++) {
                if (rightMatch >= i) {
                    int z0 = z[i - leftMatch];
                    if (i + z0 - 1 >= rightMatch) {
                        int prevRightMatch = rightMatch;
                        for (int j = rightMatch + 1; j < n; j++) {
                            if (s.charAt(j) == s.charAt(j - i)) {
                                rightMatch++;
                            } else {
                                break;
                            }
                        }
                        if (rightMatch > prevRightMatch) {
                            leftMatch = i;
                        }
                        z[i] = rightMatch - i + 1;
                    } else {
                        z[i] = z0;
                    }
                } else {
                    if (s.charAt(0) == s.charAt(i)) {
                        leftMatch = rightMatch = i;
                        for (int j = i + 1; j < n; j++) {
                            if (s.charAt(j) == s.charAt(j - i)) {
                                rightMatch++;
                            } else {
                                break;
                            }
                        }
                        z[i] = rightMatch - i + 1;
                    }
                }
            }

            return z;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();

            int[] l = new int[n + 1];

            String s = in.next();

            ZFunction zFunction = new ZFunction(s);

            int[] z = zFunction.calculate();

            for (int i = 0; i < n; i++) {
                l[z[i]]++;
            }

            int ans = n;
            for (int i = n; i >= 1; i--) {
                if (l[i] > 0) {
                    ans = i;
                }
            }

            out.println(s.substring(0, ans));
        }
    }
}
