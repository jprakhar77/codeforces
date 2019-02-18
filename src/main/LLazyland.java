package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class LLazyland {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] a = new int[n];
        int[] b = new int[n];

        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            s.add(a[i]);
        }

        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }

        List[] al = new List[k];

        for (int i = 0; i < k; i++) {
            al[i] = new ArrayList();
        }
        for (int i = 0; i < n; i++) {
            al[a[i] - 1].add(b[i]);
        }

        for (int i = 0; i < k; i++) {
            al[i].sort(Comparator.naturalOrder());
        }

        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < al[i].size() - 1; j++) {
                l.add((int) al[i].get(j));
            }
        }

        l.sort(Comparator.naturalOrder());

        int rem = k - s.size();
        long sum = 0;

        for (int i = 0; i < rem; i++) {
            sum += l.get(i);
        }

        out.println(sum);
    }
}
