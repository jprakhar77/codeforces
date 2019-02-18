public class FightMonsterDiv1 {

    long m;

    public long fightTime(long hp, long attack, int level, long duration) {

        m = level * attack / 100;

        long lo = 0;
        long hi = (attack + hp - 1) / attack;

        long t = hi;
        while (lo <= hi
        ) {
            long mid = lo + (hi - lo) / 2;

            if (mid * attack + cal(mid) * m >= hp) {
                t = Math.min(t, mid);
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        lo = 0;
        hi = Math.max(t - duration, 0);

        long ans = t;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;

            long val = mid * attack + cal(mid) * m;

            poss = false;
            long val2 = solve(hp - val, attack + mid * m, hp, attack, level, duration);

            if (poss) {
                ans = Math.min(ans, mid + val2 + 1);
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    boolean poss = false;

    long solve(long target, long ini, long hp, long attack, int level, long duration) {
        if (target <= 0) {
            poss = true;
            return 0;
        }
        long lo = 0;
        long hi = Math.min(duration, (attack + hp - 1) / attack);

        long t = hi + 1;
        while (lo <= hi
        ) {
            long mid = lo + (hi - lo) / 2;

            if (5 * (mid * ini + cal(mid) * m) >= target) {
                poss = true;
                t = Math.min(t, mid);
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return t;
    }

    long cal(long val) {
        long ans = (val * (val - 1)) / 2;
        return ans;
    }
}
