package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Set;
import java.util.TreeSet;

public class EMissingNumbers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        long[] x = new long[n];

        for (int i = 1; i < n; i += 2) {
            x[i] = in.nextInt();
        }

//        long[] numa = new long[10000];
//
//        numa[0] = 0;
//        numa[1] = 3;
//
//        long csum = 0;
//        for (int i = 2; i < 10000; i++)
//        {
//            numa[i] = numa[i] + 2;
//
//            csum
//        }
        long sum = 0;
        for (int i = 0; i < n; i += 2) {
            long diff = x[i + 1];

//            if (diff % 2 == 1) {
//                long lo = diff / 2;
//                x[i] = lo * lo - sum;
//
//                if (x[i] <= 0) {
//                    out.println("No");
//                    return;
//                }
//                sum += x[i];
//                sum += x[i + 1];
//            } else {
//                diff /= 2;
//                diff--;
//                long lo = diff / 2;
//                x[i] = lo * lo - sum;
//
//                if (x[i] <= 0) {
//                    out.println("No");
//                    return;
//                }
//                sum += x[i];
//                sum += x[i + 1];
//            }

            if (diff % 2 == 1) {
                //long mreq = sum + 1;

                //long sqrt = squareRoot(mreq);

                //ts
                Set<Long> facs = factors(x[i + 1]);

                long lo = -1;
                for (Long num : facs) {
                    long parts = x[i + 1] / num;

                    if (parts % 2 == 1) {
                        long hp = parts / 2;

                        long fnum = num - 2 * hp;

                        long slo = fnum / 2;

                        if (slo > 0 && slo * slo - sum > 0) {
                            lo = slo;
                            break;
                        }
                    }
                }

                if (lo == -1) {
                    out.println("No");
                    return;
                }

                x[i] = lo * lo - sum;

                sum += x[i];
                sum += x[i + 1];
            } else {
                long mreq = sum + 1;

                long sqrt = squareRoot(mreq);

                //ts
                Set<Long> facs = factors(x[i + 1]);

                long lo = -1;
                for (Long num : facs) {
                    //if (num >= sqrt) {
                    long parts = x[i + 1] / num;

                    if (parts % 2 == 0) {
                        long hp = parts / 2;

                        long fnum = num - 2 * hp + 1;

                        long slo = fnum / 2;

                        if (fnum % 2 == 1 && slo > 0 && slo * slo - sum > 0) {
                            lo = slo;
                            break;
                        }
                    }
                    //}
                }

                if (lo == -1) {
                    out.println("No");
                    return;
                }
                x[i] = lo * lo - sum;

                sum += x[i];
                sum += x[i + 1];
            }
        }

        out.println("Yes");
        for (int i = 0; i < n; i++) {
            out.print(x[i] + " ");
        }
    }

    TreeSet<Long> factors(long n) {
        TreeSet<Long> factors = new TreeSet<>();

        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }

        return factors;
    }

    long squareRoot(long num) {
        long squareRoot = (long) Math.sqrt(num);

        while (squareRoot * squareRoot <= num) {
            if (squareRoot * squareRoot == num) {
                return squareRoot;
            } else {
                squareRoot++;
            }
        }

        return squareRoot;
    }
}
