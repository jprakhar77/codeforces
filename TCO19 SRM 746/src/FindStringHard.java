public class FindStringHard {

    public String withAntipalindromicSubstrings(int n) {
        int[] pn = new int[51];
        for (int i = 1; i <= 50; i++) {
            pn[i] = pn[i - 1] + i;
        }

        int[] aa = new int[101];

        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                aa[i] = pn[i / 2] + pn[i / 2 - 1];
            } else {
                aa[i] = 2 * pn[i / 2];
            }
        }

        StringBuilder ans = new StringBuilder();

        int sum = n;
        char glc = 'a';
        int c = 0;
        for (int j = 99; j >= 2; j -= 2) {
            while (sum >= aa[j]) {
                StringBuilder cs = new StringBuilder();

                char lc = '\0';
                if (glc == 'a') {
                    for (int i = 0; i < j; i++) {
                        if (i % 2 == 0) {
                            cs.append('a');
                            lc = 'a';
                        } else {
                            cs.append('b');
                            lc = 'b';
                        }
                    }

                    ans.append(cs);
                    sum -= aa[j];
                    if (sum > 0) {
                        for (int k = 0; k < c; k++)
                            //ans.append(lc);
                            c++;
                        //ans.append(lc);
                    }
                    glc = lc;
                } else {
                    for (int i = 0; i < j; i++) {
                        if (i % 2 == 0) {
                            cs.append('b');
                            lc = 'b';
                        } else {
                            cs.append('a');
                            lc = 'a';
                        }
                    }

                    ans.append(cs);
                    sum -= aa[j];
                    if (sum > 0) {
                        for (int k = 0; k < c; k++)
                            //ans.append(lc);
                            //ans.append(lc);
                            c++;
                    }
                    glc = lc;
                }
            }
        }

        if (sum > 0) {
            ans.append("ab");
        }

        String s = ans.toString();

        return s;
    }

}
