package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class GZeroXORSubsetLess {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

//        Integer[] a = new Integer[n];
//
//        for (int i = 0; i < n; i++) {
//            a[i] = in.nextInt();
//        }

        int[] a = in.nextIntArray(n);

        //Arrays.sort(a, Comparator.reverseOrder());

        int xor = 0;

        for (int val : a) {
            xor ^= val;
        }

        if (xor == 0) {
            out.println(-1);
            return;
        }

        int ans = 0;
//        for (int i = 0; i < n; i++) {
//            Integer[] b = new Integer[n];
//
//            System.arraycopy(a, 0, b, 0, i + 1);
//
//            int num = a[i];
//
//            if (num == 0)
//                break;
//            int maxd = Integer.numberOfLeadingZeros(num);
//
//            ans++;
//
//            for (int j = i + 1; j < n; j++) {
//                if (Integer.numberOfLeadingZeros(a[j]) == maxd) {
//                    b[j] = a[j] ^ num;
//                } else {
//                    b[j] = a[j];
//                }
//            }
//
//            Arrays.sort(b, Comparator.reverseOrder());
//
//            a = b;
//        }

        for (int i = 29; i >= 0; i--) {
            int num = -1;

            for (int j = 0; j < n; j++) {
                if (((1 << i) & a[j]) != 0 && a[j] <= (1 << (i + 1)) - 1) {
                    if (num == -1) {
                        num = a[j];
                        ans++;
                    } else {
                        a[j] ^= num;
                    }
                }
            }
        }

        out.println(ans);

    }
}
