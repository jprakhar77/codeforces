import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResistorFactory {

    public int[] construct(long nanoOhms) {
        long den = (long) 1e9;

        long g = gcd(nanoOhms, den);

        long num = nanoOhms / g;
        den = den / g;

        if (den == 1) {
            ser(num, 0);
        } else {
            par(den, 0);
            maxs = maxp;
            ser(num, maxp - 1);
        }

        int[] ans = new int[pros.size() * 3];

        for (int i = 0; i < pros.size(); i++) {
            pro cp = pros.get(i);
            ans[i * 3] = cp.p1;
            ans[i * 3 + 1] = cp.p2;
            ans[i * 3 + 2] = cp.o;
        }

        return ans;
    }

    class pro {
        int p1;
        int p2;
        int o;

        public pro(int p1, int p2, int o) {
            this.p1 = p1;
            this.p2 = p2;
            this.o = o;
        }
    }

    List<pro> pros = new ArrayList<>();

    Map<Long, Integer> parmap = new HashMap<>();
    Map<Long, Integer> sermap = new HashMap<>();

    int maxp = 1;
    int maxs = 1;

    int par(long num, int base) {
        if (num == 1) {
            return base;
        }

        if (parmap.containsKey(num)) {
            return parmap.get(num);
        }

        int p1 = par(num / 2, base);
        pros.add(new pro(p1, p1, 1));

        parmap.put(2 * (num / 2), maxp);
        maxp++;

        if (num % 2 != 0) {
            pros.add(new pro(maxp - 1, base, 1));
            parmap.put(num, maxp);
            maxp++;
        }

        return parmap.get(num);
    }

    int ser(long num, int base) {
        if (num == 1) {
            return base;
        }

        if (sermap.containsKey(num)) {
            return sermap.get(num);
        }

        int p1 = ser(num / 2, base);
        pros.add(new pro(p1, p1, 0));

        sermap.put(2 * (num / 2), maxs);
        maxs++;

        if (num % 2 != 0) {
            pros.add(new pro(maxs - 1, base, 0));
            sermap.put(num, maxs);
            maxs++;
        }

        return sermap.get(num);
    }

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
