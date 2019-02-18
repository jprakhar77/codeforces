package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _600C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();

        int[] h = new int[26];

        for (int i = 0; i < n; i++) {
            h[s.charAt(i) - 'a']++;
        }

        List<Integer> odds = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if (h[i] % 2 == 1) {
                odds.add(i);
            }
        }

        StringBuilder ans = new StringBuilder();

        int m = odds.size();

        if (odds.size() % 2 == 1) {
            for (int i = m - 1, j = 0; i > j; i--, j++) {
                h[odds.get(i)]--;
                h[odds.get(j)]++;
            }
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < h[i] / 2; j++)
                    ans.append((char) ('a' + i));
            }
            ans.append((char) ('a' + odds.get(m / 2)));
            for (int i = 25; i >= 0; i--) {
                for (int j = 0; j < h[i] / 2; j++)
                    ans.append((char) ('a' + i));
            }
        } else {
            for (int i = m - 1, j = 0; i > j; i--, j++) {
                h[odds.get(i)]--;
                h[odds.get(j)]++;
            }
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < h[i] / 2; j++)
                    ans.append((char) ('a' + i));
            }
            for (int i = 25; i >= 0; i--) {
                for (int j = 0; j < h[i] / 2; j++)
                    ans.append((char) ('a' + i));
            }
        }

        out.println(ans.toString());


    }
}
