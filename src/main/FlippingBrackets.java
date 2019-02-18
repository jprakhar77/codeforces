package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class FlippingBrackets {
    class bucket {
        int l;
        int r;

        int size;


        public bucket(int l, int r) {
            this.l = l;
            this.r = r;
            this.ans = new int[bucketSize + 1];
            this.size = r - l + 1;
        }

        int[] ans;
        int min = 0;
        int fo = 0;

        void update(int ind) {
            if (c[ind] == '(') {
                c[ind] = ')';
            } else {
                c[ind] = '(';
            }

            solve();
        }

        void solve() {
            Arrays.fill(ans, 0);

            int o = 0;
            int cmin = 0;
            for (int i = l; i <= r; i++) {
                if (c[i] == '(') {
                    o++;
                } else {
                    o--;
                }

                if (o <= 0) {
                    if (cmin >= o)
                        ans[-o] = Math.max(ans[-o], i - l + 1);
                    cmin = Math.min(cmin, o);
                }
            }
            fo = o;
            min = cmin;
        }
    }

    String s;
    char[] c;
    int n;

    int bucketCount;

    int bucketSize;

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        s = in.next();

        c = s.toCharArray();

        n = s.length();

        int sqrt = (int) Math.sqrt(n);

        bucketCount = sqrt;

        bucketSize = (n + bucketCount - 1) / bucketCount;

        bucket[] buckets = new bucket[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            buckets[i] =
                    new bucket(i * bucketSize,
                            Math.min(i * bucketSize + bucketSize - 1, n - 1));
            buckets[i].solve();
        }


        int m = in.nextInt();

        t:
        while (m-- > 0) {
            char ch = in.nextCharacter();
            int ind = in.nextInt();
            ind--;
            int b = ind / bucketSize;

            bucket cb = buckets[b];

            if (ch == 'C') {
                buckets[b].update(ind);
            } else {

                int ans = 0;

                int o = 0;
                for (int i = ind; i <= cb.r; i++) {
                    if (c[i] == '(') {
                        o++;
                    } else {
                        o--;
                    }

                    if (o < 0) {
                        out.println(ans);
                        continue t;
                    } else if (o == 0) {
                        ans = i - ind + 1;
                    }
                }

                int size = cb.r - ind + 1;

                for (int i = b + 1; i < bucketCount; i++) {
                    bucket curb = buckets[i];

                    if (o <= bucketSize && curb.ans[o] > 0) {
                        ans = size + curb.ans[o];
                    }
                    if (-curb.min <= o) {
                        size += curb.size;
                        o += curb.fo;
                    } else {
                        break;
                    }
                }

                out.println(ans);
            }
        }
    }
}
