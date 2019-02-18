import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoprimeMatrix {

    String no = "Impossible";
    String yes = "Possible";

    public String isPossible(String[] coprime) {
        int n = coprime.length;


        char[][] co = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                co[i][j] = (i == j) ? 'N' : 'Y';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (coprime[i].charAt(j) != coprime[j].charAt(i))
                    return no;
            }
        }

        if (coprime[0].charAt(0) == 'Y') {
            return ispos(coprime, 1);
        }

        List<Integer> primes = prime(n);

        for (int z = 0; z < primes.size(); z++) {
            int i = primes.get(z);
            boolean f = false;
            for (int j = 0; j < n; j++) {
                if (j + i < n && coprime[j].charAt(j + i) == 'N') {
                    f = true;
                    for (int x = 0; x < n; x++) {
                        if ((x - j) % i == 0)
                            for (int y = x; y < n; y += i) {
                                if (coprime[x].charAt(y) != 'N')
                                    return no;
                                co[x][y] = 'N';

                                co[y][x] = 'N';
                            }
                    }
                    break;
                }
            }
            if (!f && i <= n / 2)
                return no;
        }

        return issame(coprime, co, n);
    }

    List<Integer> prime(int n) {
        boolean[] isp = new boolean[n + 1];

        Arrays.fill(isp, true);

        isp[1] = false;

        for (int i = 2; i <= n; i++) {
            if (isp[i]) {
                for (int j = 2; j * i <= n; j++) {
                    isp[i * j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (isp[i])
                primes.add(i);
        }

        return primes;
    }

    String issame(String[] co1, char[][] co2, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (co1[i].charAt(j) != co2[i][j])
                    return no;
            }
        }
        return yes;
    }

    String ispos(String[] coprime, int start) {
        int n = coprime.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (gcd(i + start, j + start) == 1 && coprime[i].charAt(j) == 'N')
                    return no;
                if (gcd(i + start, j + start) > 1 && coprime[i].charAt(j) == 'Y')
                    return no;
            }
        }

        return yes;
    }

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
