package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class AC_GC_22_B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        if (n == 3) {
            out.println("2 5 63");
            return;
        } else if (n == 4) {
            out.println("2 5 20 63");
            return;
        }

        List<Integer> ans = new ArrayList<>();
        long _2 = 0;
        long _3 = 0;
        int _23 = 0;
        int i;
        for (i = 2; ans.size() < n - 2; i++) {
            int num = i;
            if (num % 2 == 0 && num % 3 == 0) {
                _23++;
            } else if (num % 2 == 0) {
                _2 += num;
            } else if (num % 3 == 0) {
                _3 += num;
            }

            if (num % 2 == 0 || num % 3 == 0) {
                ans.add(num);
            }
        }

        int k = i;
        int sn = -1;
        for (; ; i++) {
            int num = i;
            if (num % 2 == 0 && (_2 % 3 == 0 || num % 3 != 0) && (_2 + num) % 3 == 0) {
                sn = num;
                ans.add(num);
                break;
            }
        }

        i = k;
        for (; ; i++) {
            int num = i;
            if (num % 3 == 0 && (_3 % 2 == 0 || num % 2 != 0) && num != sn && (_3 + num) % 2 == 0) {
                ans.add(num);
                break;
            }
        }

        for (int j = 0; j < ans.size(); j++) {
            out.print(ans.get(j));
            out.print(' ');
        }
    }

    int req2(int _2) {
        int div = _2 % 3;

        if (div == 1 || div == 2)
            return 3 - div;
        else
            return 0;
    }

    int req3(int _3) {
        int div = _3 % 2;

        if (div == 1)
            return 1;
        else
            return 0;
    }
}
