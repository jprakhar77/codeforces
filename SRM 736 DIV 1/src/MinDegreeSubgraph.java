public class MinDegreeSubgraph {

    public String exists(int ni, long m, int ki) {
        long n = ni;
        long k = ki;

        if (k == 0) {
            return "ALL";
        }

        if (n == 1) {
            return "ALL";
        }

        if (k == 1) {
            if (m > 0) {
                return "ALL";
            } else {
                return "NONE";
            }
        }

        if (n < k + 1) {
            return "NONE";
        }

        long min = ((k + 1) * k) / 2;

        if (m < min) {
            return "NONE";
        }

        long max = n * k;

        if (max % 2 == 0) {
            max /= 2;
            max--;
        } else {
            max /= 2;
        }

        if (m <= max) {
            return "SOME";
        } else {
            return "ALL";
        }
    }
}
