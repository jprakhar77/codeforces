import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GroupTheNumbers {

    public String calculate(int[] a) {
        int n = a.length;

        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();
        boolean isz = false;
        int ones = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                isz = true;
            } else if (a[i] > 0) {
                pos.add(a[i]);
                if (a[i] == 1) {
                    ones++;
                }
            } else {
                neg.add(a[i]);
            }
        }

        pos.sort((x, y) -> x - y);
        neg.sort((x, y) -> x - y);

        BigInteger bi = BigInteger.valueOf(0);

        for (int i = ones; i < pos.size(); i++) {
            if (bi.equals(BigInteger.ZERO)) {
                bi = BigInteger.ONE;
            }
            bi = bi.multiply(BigInteger.valueOf(pos.get(i)));
        }

        if (neg.size() % 2 == 1) {
            for (int i = 0; i < neg.size() - 1; i++) {
                if (bi.equals(BigInteger.ZERO)) {
                    bi = BigInteger.ONE;
                }
                bi = bi.multiply(BigInteger.valueOf(neg.get(i)));
            }

            if (!isz) {
                bi = bi.add(BigInteger.valueOf(neg.get(neg.size() - 1)));
            }
        } else {
            for (int i = 0; i < neg.size(); i++) {
                if (bi.equals(BigInteger.ZERO)) {
                    bi = BigInteger.ONE;
                }
                bi = bi.multiply(BigInteger.valueOf(neg.get(i)));
            }
        }

        bi = bi.add(BigInteger.valueOf(ones));

        String ans = bi.toString();

        if (ans.length() <= 203) {
            return ans;
        } else {
            StringBuilder sans = new StringBuilder(ans.substring(0, 100));
            sans.append("...");
            sans.append(ans.substring(ans.length() - 100, ans.length()));

            return sans.toString();
        }
    }
}
