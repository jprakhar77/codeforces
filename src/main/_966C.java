package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _966C {
    class num {
        int i;
        long num;
        num next;

        public num(int i, long num, num next) {
            this.i = i;
            this.num = num;
            this.next = next;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }

        List[] hbl = new List[60];

        for (int i = 0; i < 60; i++) {
            hbl[i] = new ArrayList<num>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 59; j >= 0; j--) {
                if ((a[i] & (1L << j)) != 0) {
                    hbl[j].add(new num(i, a[i], null));
                    break;
                }
            }
        }

        List<num> ll = new ArrayList<>();

        if (hbl[59].size() > 1) {
            out.println("No");
            return;
        }

        num head = null;
        for (int i = 59; i >= 0; i--) {
            if (hbl[i].size() == 0)
                continue;
            num cn = (num) hbl[i].get(0);

            num nhead = new num(cn.i, cn.num, head);

            head = nhead;
            ll.add(head);

            if (hbl[i].size() == 1)
                continue;

            int j = 1;
            long xor = ((num) hbl[i].get(0)).num;

            num cur = head.next;
            for (; cur != null && j < hbl[i].size(); ) {
                num cln = cur;
                xor = (xor ^ cln.num);

                if (((1L << i) & xor) == 0) {
                    cn = (num) hbl[i].get(j);

                    num nn = new num(cn.i, cn.num, cln.next);

                    cln.next = nn;

                    j++;

                    ll.add(nn);

                    xor = (xor ^ cn.num);

                    cur = nn.next;
                } else {
                    cur = cur.next;
                }
            }

            if (j < hbl[i].size()) {
                out.println("No");
                return;
            }
        }

        num cur = head;

        out.println("Yes");
        while (cur != null) {
            out.print((cur.num) + " ");
            cur = cur.next;
        }
    }
}
