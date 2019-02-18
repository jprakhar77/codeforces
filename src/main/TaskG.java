package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class TaskG {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();

        int m = in.nextInt();

        int[] spos = new int[n];


        for (int i = 0; i < m; i++) {
            int pos = in.nextInt();
            String st = in.next();

            int bit = 0;

            for (int j = 0; j < st.length(); j++) {
                int bi = st.charAt(j) - 'a';
                bit += (1 << bi);
            }

            spos[pos] = bit;
        }

        int[] sh = new int[6];

        for (int i = 0; i < n; i++) {
            sh[s.charAt(i) - 'a']++;
        }



    }
}
