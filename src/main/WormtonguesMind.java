package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class WormtonguesMind {
    class Polynomial {
        Map<Integer, BigDecimal> degreeCoeff = new HashMap<>();

        public Polynomial() {
        }

        public Polynomial(Map<Integer, BigDecimal> degreeCoeff) {
            this.degreeCoeff = new HashMap<>(degreeCoeff);
        }


        void putTerm(int degree, BigDecimal coeff) {
            degreeCoeff.put(degree, coeff);
        }

        public Map<Integer, BigDecimal> getDegreeCoeff() {
            return degreeCoeff;
        }

        Polynomial multiply(Polynomial multiplier) {
            Polynomial product = new Polynomial();

            for (Integer deg1 : this.degreeCoeff.keySet()) {
                for (Integer deg2 : multiplier.getDegreeCoeff().keySet()) {
                    BigDecimal coeff1 = this.degreeCoeff.get(deg1);
                    BigDecimal coeff2 = multiplier.getDegreeCoeff().get(deg2);

                    product.getDegreeCoeff().merge(deg1 + deg2,
                            coeff1.multiply(coeff2), (x, y) -> x.add(y));
                }
            }

            return product;
        }

        Polynomial add(Polynomial adder) {
            Polynomial result = new Polynomial(this.degreeCoeff);

            for (Integer deg : adder.getDegreeCoeff().keySet()) {
                BigDecimal coeff = adder.getDegreeCoeff().get(deg);

                result.getDegreeCoeff().merge(deg,
                        coeff, (x, y) -> x.add(y));
            }

            return result;
        }

        Polynomial subtract(Polynomial subtract) {
            Polynomial result = new Polynomial(this.degreeCoeff);

            for (Integer deg : subtract.getDegreeCoeff().keySet()) {
                BigDecimal coeff = subtract.getDegreeCoeff().get(deg);

                result.getDegreeCoeff().merge(deg,
                        coeff.negate(), (x, y) -> x.add(y));
            }

            return result;
        }

        Polynomial differentiate() {
            Polynomial result = new Polynomial();

            this.degreeCoeff.keySet().stream().sorted().forEach(deg -> {
                if (deg > 0) {
                    result.getDegreeCoeff().put(deg - 1, this.degreeCoeff.get(deg).multiply(new BigDecimal(deg)));
                }
            });

            return result;
        }

        Polynomial integrate() {
            Polynomial result = new Polynomial();

            this.degreeCoeff.keySet().stream().sorted(Comparator.reverseOrder()).forEach(deg -> {
                result.getDegreeCoeff().put(deg + 1, this.degreeCoeff.get(deg).divide(new BigDecimal(deg + 1), MathContext.DECIMAL128));
            });

            return result;
        }

    }

    enum NodeType {
        M,
        x,
        m
    }

    class vertex {
        NodeType nt;
        Polynomial poly;
        vertex left;
        vertex right;

        public vertex(NodeType nt) {
            this.nt = nt;
        }
    }

    Polynomial x = new Polynomial();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        ci = 0;
        x.putTerm(1, BigDecimal.ONE);
        vertex root = buildPrefixTree(s);

        dfs(root);

        Polynomial fx = root.poly.differentiate();


        Polynomial integral = fx.multiply(x);

        Polynomial expectation = integral.integrate();

        BigDecimal ans = new BigDecimal(0);
        for (Integer deg : expectation.degreeCoeff.keySet()) {
            BigDecimal coeff = expectation.degreeCoeff.get(deg);

            ans = ans.add(coeff);
        }

        ans = ans.subtract(expectation.degreeCoeff.getOrDefault(0, BigDecimal.ZERO));

        out.println(ans);
    }

    int ci = 0;

    vertex buildPrefixTree(String s) {
        vertex vertex = new vertex(NodeType.valueOf(s.substring(ci, ci + 1)));
        ci++;

        if (vertex.nt == NodeType.x)
            return vertex;

        vertex.left = buildPrefixTree(s);
        vertex.right = buildPrefixTree(s);

        return vertex;
    }

    void dfs(vertex root) {
        if (root.nt == NodeType.x) {
            root.poly = x;
            return;
        }

        dfs(root.left);
        dfs(root.right);

        Polynomial p1 = root.left.poly;
        Polynomial p2 = root.right.poly;
        if (root.nt == NodeType.M)
            root.poly = p1.multiply(p2);
        else {
            root.poly = p1.add(p2);
            Polynomial subtract = p1.multiply(p2);
            root.poly = root.poly.subtract(subtract);
        }
    }
}
