package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CMeaninglessOperations {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
//        for (int j = 1; j <= 26; j++) {
//            long ans = 0;
//            int num = (1 << j) - 1;
//            for (int i = 1; i < num; i++) {
//                int x = (num ^ i);
//                int y = (num & i);
//
//                ans = Math.max(ans, gcd(x, y));
//            }
//            out.println("a[" + j + "]=" + ans + ";");
//        }

        int q = in.nextInt();

        int[] a = new int[30];

        a[1] = 0;
        a[2] = 1;
        a[3] = 1;
        a[4] = 5;
        a[5] = 1;
        a[6] = 21;
        a[7] = 1;
        a[8] = 85;
        a[9] = 73;
        a[10] = 341;
        a[11] = 89;
        a[12] = 1365;
        a[13] = 1;
        a[14] = 5461;
        a[15] = 4681;
        a[16] = 21845;
        a[17] = 1;
        a[18] = 87381;
        a[19] = 1;
        a[20] = 349525;
        a[21] = 299593;
        a[22] = 1398101;
        a[23] = 178481;
        a[24] = 5592405;
        a[25] = 1082401;
        a[26] = 22369621;

        while (q-- > 0) {
            int num = in.nextInt();

            int num1 = num + 1;

            if ((num1 & (num1 - 1)) == 0) {
                int d = 0;

                int cnum = num;
                for (int i = 0; i < 27; i++) {
                    cnum &= (~(1 << i));
                    if (cnum == 0) {
                        d = i + 1;
                        break;
                    }
                }

                out.println(a[d]);
            } else {

                boolean f = false;

                int ans = num;
                for (int i = 26; i >= 0; i--) {
                    if ((num & (1 << i)) == 0) {
                        if (f) {
                            ans |= (1 << i);
                        }
                    } else {
                        f = true;
                    }
                }

                out.println(ans);
            }
        }

    }

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
