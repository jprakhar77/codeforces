package library;

public class ZFunction {
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