package library;

public class BasicSeries {

    double eps = 1e-6;

    long sumofGeometricSeries(int firstTerm, double ratio, int numberOfTerms) {
        if (ratio == 1) {
            return firstTerm * numberOfTerms;
        } else {
            double rPowerN = Math.pow(ratio, numberOfTerms);
            long val = firstTerm;
            val *= (1 - rPowerN);
            val /= (1 - ratio);

            return (long) (val + eps);
        }
    }

    double pow(double a, long p) {
        if (p == 0) {
            return 1;
        }

        double t = pow(a, p / 2);

        if (p % 2 != 0) {
            return (t * t * a);
        } else {
            return (t * t);
        }
    }

    long sumOfArithmeticSeries(long firstTerm, long difference, long numOfTerms) {
        long nthTerm = firstTerm + (numOfTerms - 1) * difference;

        long sum = ((firstTerm + nthTerm) * numOfTerms) / 2;

        return sum;
    }
}
