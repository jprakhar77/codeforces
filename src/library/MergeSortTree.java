package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MergeSortTree
{
    List[] st;
    int n;

    public MergeSortTree(int n)
    {
        st = new List[4 * n];
        this.n = n;
    }

    void build(int i, List<Integer> a, int r1, int r2)
    {
        if (r1 == r2)
        {
            st[i] = Collections.singletonList(a.get(r1));
        }
        else
        {
            build(i * 2 + 1, a, r1, (r1 + r2) / 2);
            build(i * 2 + 2, a, (r1 + r2) / 2 + 1, r2);

            st[i] = new ArrayList<Integer>();

            mergeSortedLists(st[i * 2 + 1], st[i * 2 + 2], st[i]);
        }
    }

    private List<Integer> mergeSortedLists(List<Integer> l1,
                                           List<Integer> l2, List<Integer> lm)
    {
        int j = 0, k = 0;
        for (; j < l1.size() && k < l2.size();)
        {
            if ((int) l1.get(j) <= (int) l2.get(k))
            {
                lm.add(l1.get(j));
                j++;
            }
            else
            {
                lm.add(l2.get(k));
                k++;
            }
        }

        while (j < l1.size())
        {
            lm.add(l1.get(j));
            j++;
        }

        while (k < l2.size())
        {
            lm.add(l2.get(k));
            k++;
        }

        return lm;
    }

    int query(int i, int ra, int rb, int r1, int r2)
    {
        if (ra > r2 || rb < r1)
        {
            return 0;
        }

        if (ra >= r1 && rb <= r2)
        {
            return lessthan_BS(r1, st[i]);
        }

        int p1 = query(i * 2 + 1, ra, (ra + rb) / 2, r1, r2);
        int p2 = query(i * 2 + 2, (ra + rb) / 2 + 1, rb, r1, r2);

        return p1 + p2;
    }

    int lessthan_BS(int num, List<Integer> l)
    {
        int lo = 0;
        int hi = l.size() - 1;

        int maxi = -1;

        while (lo <= hi)
        {
            int mid = (lo + hi) / 2;

            if (l.get(mid) < num)
            {
                maxi = Math.max(maxi, mid);
                lo = mid + 1;
            }
            else
            {
                hi = mid - 1;
            }
        }

        return maxi + 1;
    }

}
