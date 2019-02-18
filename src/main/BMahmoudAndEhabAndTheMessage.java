package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class BMahmoudAndEhabAndTheMessage {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();

        String[] sa = new String[n];

        for (int i = 0; i < n; i++) {
            sa[i] = in.next();
        }

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(sa[i], i);
        }


        int[] a = in.nextIntArray(n);

        List[] ll = new List[k];

        for (int i = 0; i < k; i++) {
            ll[i] = new ArrayList();
        }

        int[] itok = new int[n];

        for (int i = 0; i < k; i++) {
            int s = in.nextInt();

            for (int j = 0; j < s; j++) {
                int num = in.nextInt() - 1;
                ll[i].add(a[num]);
                itok[num] = i;
            }

            ll[i].sort(Comparator.naturalOrder());
        }

        int[] rq = new int[k];

        for (int i = 0; i < m; i++) {
            String s = in.next();

            int ind = map.get(s);

            int ki = itok[ind];

            rq[ki]++;
        }

        long sum = 0;

        for (int i = 0; i < k; i++) {
            List<Integer> cl = ll[i];
            sum += (long) rq[i] * cl.get(0);
        }

        out.println(sum
        );
    }
}
