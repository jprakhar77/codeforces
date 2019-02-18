package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _993E {
    class FastFourierTransform {
        double[] xreal;
        double[] xcomplex;
        double[] yreal;
        double[] ycomplex;

        int xlen;
        int ylen;

        double[] resReal;
        double[] resComplex;

        public FastFourierTransform(double[] xreal, double[] xcomplex, double[] yreal, double[] ycomplex) {
            this.xreal = xreal;
            this.xcomplex = xcomplex;
            this.yreal = yreal;
            this.ycomplex = ycomplex;
            assert xreal.length == xcomplex.length;
            assert yreal.length == ycomplex.length;
            xlen = xreal.length;
            ylen = yreal.length;
        }

        public FastFourierTransform(double[] xreal, double[] yreal) {
            this.xreal = xreal;
            this.yreal = yreal;
            this.xcomplex = new double[xreal.length];
            this.ycomplex = new double[yreal.length];
            xlen = xreal.length;
            ylen = yreal.length;
        }

        double eps = 1e-5;

        void convolute() {
            int axlen = xlen;
            int aylen = ylen;
//            for (int i = 0; i < xlen; i++) {
//                if (Math.abs(xreal[i]) > eps) {
//                    xreal[i] = 1;
//                    xcomplex[i] = 0;
//                    axlen = i + 1;
//                } else {
//                    xreal[i] = xcomplex[i] = 0;
//                }
//            }
//
//            for (int i = 0; i < ylen; i++) {
//                if (Math.abs(yreal[i]) > eps) {
//                    yreal[i] = 1;
//                    ycomplex[i] = 0;
//                    aylen = i + 1;
//                } else {
//                    yreal[i] = ycomplex[i] = 0;
//                }
//            }

            int totalLength = newSize(axlen + aylen);

            double[] xTempReal = new double[totalLength];
            double[] xTempComplex = new double[totalLength];
            double[] yTempReal = new double[totalLength];
            double[] yTempComplex = new double[totalLength];

            System.arraycopy(xreal, 0, xTempReal, 0, axlen);
            System.arraycopy(xcomplex, 0, xTempComplex, 0, axlen);
            System.arraycopy(yreal, 0, yTempReal, 0, aylen);
            System.arraycopy(ycomplex, 0, yTempComplex, 0, aylen);

            circularConvolute(xTempReal, xTempComplex, yTempReal, yTempComplex, totalLength);
        }

        void circularConvolute(double[] xr, double[] xc, double[] yr, double[] yc, int size) {
            //assert all equal sizes

            fft(xr, xc, 0, size);
            fft(yr, yc, 0, size);

            double[] real = new double[size];
            double[] complex = new double[size];

            for (int i = 0; i < size; i++) {
                times(xr, xc, yr[i], yc[i], i);
                real[i] = xr[i];
                complex[i] = xc[i];
            }

            ifft(real, complex);

            resReal = real;
            resComplex = complex;
        }

        void times(double[] xr, double[] xc, double yr, double yc, int ind) {
            double xrind = xr[ind];
            xr[ind] = xr[ind] * yr - xc[ind] * yc;
            xc[ind] = xrind * yc + xc[ind] * yr;
        }

        //Cooley-Tukey FFT
        void fft(double[] real, double[] complex, int startIndex, int size) {
            if (size == 1)
                return;

            int halfSize = size / 2;

            double[] tmpR = new double[halfSize];
            double[] tmpC = new double[halfSize];

            for (int i = startIndex + 1, j = 0; i < startIndex + size; i += 2, j++) {
                tmpR[j] = real[i];
                tmpC[j] = complex[i];
            }

            for (int i = startIndex, j = startIndex; i < startIndex + size; i += 2, j++) {
                real[j] = real[i];
                complex[j] = complex[i];
            }

            System.arraycopy(tmpR, 0, real, startIndex + halfSize, halfSize);
            System.arraycopy(tmpC, 0, complex, startIndex + halfSize, halfSize);

            fft(real, complex, startIndex, halfSize);
            fft(real, complex, startIndex + halfSize, halfSize);

            double currentW = 0;
            double nthRootOfUnity = -2 * Math.PI / size;
            for (int i = startIndex; i < startIndex + halfSize; i++) {
                double wReal = Math.cos(currentW);
                double wComplex = Math.sin(currentW);

                times(real, complex, wReal, wComplex, halfSize + i);

                double evenReal = real[i];
                double evenComplex = complex[i];

                real[i] = real[i] + real[halfSize + i];
                complex[i] = complex[i] + complex[halfSize + i];

                real[halfSize + i] = evenReal - real[halfSize + i];
                complex[halfSize + i] = evenComplex - complex[halfSize + i];

                currentW += nthRootOfUnity;
            }
        }

        void ifft(double[] real, double[] complex) {

            int size = real.length;

            for (int i = 0; i < size; i++) {
                complex[i] = -complex[i];
            }

            fft(real, complex, 0, size);

            for (int i = 0; i < size; i++) {
                complex[i] = -complex[i] / size;
                real[i] /= size;
            }
        }

        int newSize(int size) {
            int powerOf2 = 0;

            while ((1 << powerOf2) < size) {
                powerOf2++;
            }

            int newSize = 1 << powerOf2;

            return newSize;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int x = in.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int[] b = new int[n];

        long ans = 0;

        if (a[0] < x) {
            b[0] = 1;
        }
        for (int i = 1; i < n; i++) {
            b[i] = b[i - 1] + (a[i] < x ? 1 : 0);
        }

        double[] c = new double[n + 1];

        c[0] = 1;
        for (int i = 0; i < n; i++) {
            c[b[i]]++;
        }

        double[] d = new double[n + 1];

        for (int i = 0; i <= n; i++) {
            ans += ((long)c[i]  * (c[i] - 1)) / 2;
            d[i] = c[n - i];
        }

        FastFourierTransform fft = new FastFourierTransform(c, d);

        fft.convolute();

        out.print(ans + " ");
        for (int i = n + 1; i <= 2 * n; i++)
        {
            out.print((long) (fft.resReal[i] +eps) + " ");
        }
    }

    double eps = 1e-1;
}
