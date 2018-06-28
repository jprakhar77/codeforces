package library;

public class ExtendedEuclid {

    class res {
        long x;
        long y;
        long g;

        public res(long x, long y, long g) {
            this.x = x;
            this.y = y;
            this.g = g;
        }
    }

    //a*x + b*y = g

    res gcd(long a, long b, res res) {
        if (b == 0) {
            return new res(1, 0, a);
        }

        res = gcd(b, a % b, res);

        res newres = new res(1, 1, res.g);

        newres.y = res.x - (a / b) * res.y;
        newres.x = res.y;

        return newres;
    }
}
