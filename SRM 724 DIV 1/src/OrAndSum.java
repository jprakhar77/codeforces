public class OrAndSum {

    public String isPossible(long[] pairOr, long[] pairSum) {
        int n = pairOr.length;

        long[] pairAnd = new long[n];

        for (int i = 0; i < n; i++) {
            pairAnd[i] = pairSum[i] - pairOr[i];
        }

        for (int i = 0; i <= 60; i++) {
            //0
            int pv = 0;
            boolean poss1 = true;
            for (int j = 0; j < n; j++) {
                long av = (pairAnd[j] & (1l << i));
                long ov = (pairOr[j] & (1l << i));

                if (av != 0 && pv == 0) {
                    poss1 = false;
                    break;
                }
                if (av > ov) {
                    poss1 = false;
                    break;
                }
                if (ov == 0 && pv != 0) {
                    poss1 = false;
                    break;
                }

                if (pv != 0) {
                    if (av != 0) {

                    } else {
                        pv = 0;
                    }
                } else {
                    if (ov != 0) {
                        pv = 1;
                    }
                }
            }

            if (poss1)
                continue;

            pv = 1;
            poss1 = true;
            for (int j = 0; j < n; j++) {
                long av = (pairAnd[j] & (1l << i));
                long ov = (pairOr[j] & (1l << i));

                if (av != 0 && pv == 0) {
                    poss1 = false;
                    break;
                }
                if (av > ov) {
                    poss1 = false;
                    break;
                }
                if (ov == 0 && pv != 0) {
                    poss1 = false;
                    break;
                }

                if (pv != 0) {
                    if (av != 0) {

                    } else {
                        pv = 0;
                    }
                } else {
                    if (ov != 0) {
                        pv = 1;
                    }
                }
            }

            if (!poss1) {
                return "Impossible";
            }
        }
        return "Possible";
    }
}
