package library;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Polynomial {
    Map<Integer, Double> degreeCoeff = new HashMap<>();

    public Polynomial() {
    }

    public Polynomial(Map<Integer, Double> degreeCoeff) {
        this.degreeCoeff = new HashMap<>(degreeCoeff);
    }


    void putTerm(int degree, double coeff) {
        degreeCoeff.put(degree, coeff);
    }

    public Map<Integer, Double> getDegreeCoeff() {
        return degreeCoeff;
    }

    Polynomial multiply(Polynomial multiplier) {
        Polynomial product = new Polynomial();

        for (Integer deg1 : this.degreeCoeff.keySet()) {
            for (Integer deg2 : multiplier.getDegreeCoeff().keySet()) {
                double coeff1 = this.degreeCoeff.get(deg1);
                double coeff2 = multiplier.getDegreeCoeff().get(deg2);

                product.getDegreeCoeff().merge(deg1 + deg2,
                        coeff1 * coeff2, (x, y) -> x + y);
            }
        }

        return product;
    }

    Polynomial add(Polynomial adder) {
        Polynomial result = new Polynomial(this.degreeCoeff);

        for (Integer deg : adder.getDegreeCoeff().keySet()) {
            double coeff = adder.getDegreeCoeff().get(deg);

            result.getDegreeCoeff().merge(deg,
                    coeff, (x, y) -> x + y);
        }

        return result;
    }

    Polynomial subtract(Polynomial subtract) {
        Polynomial result = new Polynomial(this.degreeCoeff);

        for (Integer deg : subtract.getDegreeCoeff().keySet()) {
            double coeff = subtract.getDegreeCoeff().get(deg);

            result.getDegreeCoeff().merge(deg,
                    -coeff, (x, y) -> x + y);
        }

        return result;
    }

    Polynomial differentiate() {
        Polynomial result = new Polynomial();

        this.degreeCoeff.keySet().stream().sorted().forEach(deg -> {
            if (deg > 0) {
                result.getDegreeCoeff().put(deg - 1, this.degreeCoeff.get(deg) * deg);
            }
        });

        return result;
    }

    Polynomial integrate() {
        Polynomial result = new Polynomial();

        this.degreeCoeff.keySet().stream().sorted(Comparator.reverseOrder()).forEach(deg -> {
            result.getDegreeCoeff().put(deg + 1, this.degreeCoeff.get(deg) / (deg + 1));
        });

        return result;
    }
}
