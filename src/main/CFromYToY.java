package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class CFromYToY {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        if (n == 0) {
            out.println('a');
            return;
        }

        int ck = 0;

        int lck = 0;
        int lf = 1;

        List<Integer> l = new ArrayList<>();
        for (int i = 1; ck <= 100000; i++) {
            ck += i;
            l.add(i);
//            if (n % ck == 0) {
//                lf = i;
//                lck = ck;
//            } else {
//                break;
//            }
        }

//        for (int i = 1; i <= 100000; i++) {
//            int num = i;
//
//            for (int j = l.size() - 1; j >= 0; j--) {
//                if (num >= l.get(j)) {
//                    num -= l.get(j);
//                }
//            }
//
//            if (num != 0)
//            {
//                out.println("ouch");
//            }
//        }

        StringBuilder ans = new StringBuilder();


        int cn = n;
        int j = 0;
        for (int i = l.size() - 1; i >= 0; i--) {
            int num = l.get(i);

            int val = (num * (num + 1)) / 2;
            while (cn >= val) {
                for (int k = 0; k < num + 1; k++) {
                    ans.append((char) (j + 'a'));
                }
                j++;
                cn -= val;
            }

        }

        if (cn != 0)
        {
            out.println("hu");
        }

        out.println(ans);
    }
}
