package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1003F {
    int mod = (int) 1e9 + 7;
    long mult = 54945977;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        String[] words = new String[n];
        String str = in.readLine();
        String[] splits = str.split(" ");

        int tot = 0;

        int[] stri = new int[n];

        for (int i = 0; i < n; i++) {
            words[i] = splits[i];
            stri[i] = tot;
            tot += words[i].length() + 1;
        }

        tot--;

        long[][] hash0 = new long[n][n];
        long[][] hash1 = new long[n][n];


        long[] wordhash = new long[n];

        for (int i = 0; i < n; i++) {
            wordhash[i] = words[i].hashCode();
        }

        for (int i = 0; i < n; i++) {
            long ch = 0;
            for (int j = i; j < n; j++) {
                ch *= mult;
                ch %= mod;
                ch += words[j].hashCode();
                ch %= mod;

                hash0[i][j] = ch;

                //hash1[i][j] = str.substring(stri[i], stri[j] + words[j].length()).hashCode();
            }
        }

        int ans = tot;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sz = j - i + 1;
                int count = 1;
                boolean[] isabb = new boolean[n];
                isabb[i] = true;
                for (int k = j + 1; k < n - sz + 1; k++) {
                    int l = k + sz - 1;
                    if (hash0[i][j] == hash0[k][l] && hash1[i][j] == hash1[k][l]) {
                        //boolean check = check(words, wordhash, i, k, sz);
                        //if (check) {
                            isabb[k] = true;
                            k += sz - 1;
                            count++;
                        //}
                    }
                }
                int csize = 0;
                if (count > 1) {
                    for (int k = 0; k < n; k++) {
                        if (!isabb[k]) {
                            csize += words[k].length();
                            csize++;
                        } else {
                            csize += sz + 1;
                            k += sz - 1;
                        }
                    }
                    csize--;

                    ans = Math.min(ans, csize);
                }
            }
        }

        out.println(ans);
    }

    boolean check(String[] words, long[] wordhash, int i, int j, int sz) {
        for (int x = i, y = j; x < i + sz; x++, y++) {
            if (wordhash[x] != wordhash[y])
                return false;
            else if (!words[x].equals(words[y]))
                return false;
        }
        return true;
    }
}
