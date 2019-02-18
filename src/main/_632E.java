package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.BitSet;

public class _632E {
//    static class Complex {
//        private double re;   // the real part
//        private double im;   // the imaginary part
//
//        /**
//         * Initializes a complex number from the specified real and imaginary parts.
//         *
//         * @param real the real part
//         * @param imag the imaginary part
//         */
//        public Complex(double real, double imag) {
//            re = real;
//            im = imag;
//        }
//
//        public Complex(Complex complex) {
//            re = complex.re;
//            im = complex.im;
//        }
//
//        /**
//         * Returns a string representation of this complex number.
//         *
//         * @return a string representation of this complex number,
//         * of the form 34 - 56i.
//         */
//        public String toString() {
//            if (im == 0) return re + "";
//            if (re == 0) return im + "i";
//            if (im < 0) return re + " - " + (-im) + "i";
//            return re + " + " + im + "i";
//        }
//
//        /**
//         * Returns the absolute value of this complex number.
//         * This quantity is also known as the <em>modulus</em> or <em>magnitude</em>.
//         *
//         * @return the absolute value of this complex number
//         */
//        public double abs() {
//            return Math.hypot(re, im);
//        }
//
//        /**
//         * Returns the phase of this complex number.
//         * This quantity is also known as the <em>angle</em> or <em>argument</em>.
//         *
//         * @return the phase of this complex number, a real number between -pi and pi
//         */
//        public double phase() {
//            return Math.atan2(im, re);
//        }
//
//        /**
//         * Returns the sum of this complex number and the specified complex number.
//         *
//         * @param that the other complex number
//         * @return the complex number whose value is {@code (this + that)}
//         */
//        public Complex plus(Complex that) {
//            double real = this.re + that.re;
//            double imag = this.im + that.im;
//            return new Complex(real, imag);
//        }
//
//        /**
//         * Returns the result of subtracting the specified complex number from
//         * this complex number.
//         *
//         * @param that the other complex number
//         * @return the complex number whose value is {@code (this - that)}
//         */
//        public Complex minus(Complex that) {
//            double real = this.re - that.re;
//            double imag = this.im - that.im;
//            return new Complex(real, imag);
//        }
//
//        /**
//         * Returns the product of this complex number and the specified complex number.
//         *
//         * @param that the other complex number
//         * @return the complex number whose value is {@code (this * that)}
//         */
//        public Complex times(Complex that) {
//            double real = this.re * that.re - this.im * that.im;
//            double imag = this.re * that.im + this.im * that.re;
//            return new Complex(real, imag);
//        }
//
//        /**
//         * Returns the product of this complex number and the specified scalar.
//         *
//         * @param alpha the scalar
//         * @return the complex number whose value is {@code (alpha * this)}
//         */
//        public Complex scale(double alpha) {
//            return new Complex(alpha * re, alpha * im);
//        }
//
//        /**
//         * Returns the product of this complex number and the specified scalar.
//         *
//         * @param alpha the scalar
//         * @return the complex number whose value is {@code (alpha * this)}
//         * @deprecated Replaced by {@link #scale(double)}.
//         */
//        @Deprecated
//        public Complex times(double alpha) {
//            return new Complex(alpha * re, alpha * im);
//        }
//
//        /**
//         * Returns the complex conjugate of this complex number.
//         *
//         * @return the complex conjugate of this complex number
//         */
//        public Complex conjugate() {
//            return new Complex(re, -im);
//        }
//
//        /**
//         * Returns the reciprocal of this complex number.
//         *
//         * @return the complex number whose value is {@code (1 / this)}
//         */
//        public Complex reciprocal() {
//            double scale = re * re + im * im;
//            return new Complex(re / scale, -im / scale);
//        }
//
//        /**
//         * Returns the real part of this complex number.
//         *
//         * @return the real part of this complex number
//         */
//        public double re() {
//            return re;
//        }
//
//        /**
//         * Returns the imaginary part of this complex number.
//         *
//         * @return the imaginary part of this complex number
//         */
//        public double im() {
//            return im;
//        }
//
//        /**
//         * Returns the result of dividing the specified complex number into
//         * this complex number.
//         *
//         * @param that the other complex number
//         * @return the complex number whose value is {@code (this / that)}
//         */
//        public Complex divides(Complex that) {
//            return this.times(that.reciprocal());
//        }
//
//        /**
//         * Returns the complex exponential of this complex number.
//         *
//         * @return the complex exponential of this complex number
//         */
//        public Complex exp() {
//            return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
//        }
//
//        /**
//         * Returns the complex sine of this complex number.
//         *
//         * @return the complex sine of this complex number
//         */
//        public Complex sin() {
//            return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
//        }
//
//        /**
//         * Returns the complex cosine of this complex number.
//         *
//         * @return the complex cosine of this complex number
//         */
//        public Complex cos() {
//            return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
//        }
//
//        /**
//         * Returns the complex tangent of this complex number.
//         *
//         * @return the complex tangent of this complex number
//         */
//        public Complex tan() {
//            return sin().divides(cos());
//        }
//    }
//
//    static class FFT {
//
//        private static final Complex ZERO = new Complex(0, 0);
//        private static final Complex ONE = new Complex(1, 0);
//
//        private static BitSet isdone;
//
//        // Do not instantiate.
//        private FFT() {
//        }
//
//        /**
//         * Returns the FFT of the specified complex array.
//         *
//         * @param x the complex array
//         * @return the FFT of the complex array {@code x}
//         * @throws IllegalArgumentException if the length of {@code x} is not a power of 2
//         */
//        public static Complex[] bfft(Complex[] x, int startIndex, int size) {
//            // base case
//            if (size == 1) {
//                return x;
//            }
//
//            // radix 2 Cooley-Tukey FFT
//            //assert size % 2 == 0;
//
//            int evenTerms = size / 2;
//
//            isdone.set(startIndex, startIndex + size, false);
//            for (int k = 0; k < size; k++) {
//                if (isdone.get(startIndex + k))
//                    continue;
//                int nk = -1;
//                if (k % 2 == 0) {
//                    nk = k / 2;
//                } else {
//                    nk = evenTerms + k / 2;
//                }
//                int ank = nk;
//                Complex prev = x[startIndex + k];
//
//                do {
//                    Complex temp = x[startIndex + nk];
//                    x[startIndex + nk] = prev;
//                    isdone.set(startIndex + nk);
//                    prev = temp;
//                    if (nk % 2 == 0) {
//                        nk = nk / 2;
//                    } else {
//                        nk = evenTerms + nk / 2;
//                    }
//                } while (nk != ank);
//            }
//
//            bfft(x, startIndex, evenTerms);
//            bfft(x, startIndex + evenTerms, evenTerms);
//
//            for (int k = startIndex; k < startIndex + evenTerms; k++) {
//                double kth = -2 * k * Math.PI / size;
//                Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
//                Complex evenTerm = x[k];
//                Complex oddTerm = x[evenTerms + k];
//                x[k] = evenTerm.plus(wk.times(oddTerm));
//                x[k + evenTerms] = evenTerm.minus(wk.times(oddTerm));
//            }
//
//            return x;
//        }
//
//
//        /**
//         * Returns the inverse FFT of the specified complex array.
//         *
//         * @param x the complex array
//         * @return the inverse FFT of the complex array {@code x}
//         * @throws IllegalArgumentException if the length of {@code x} is not a power of 2
//         */
//        public static Complex[] ifft(Complex[] y) {
//            int n = y.length;
//            // take conjugate
//            for (int i = 0; i < n; i++) {
//                y[i].im = -y[i].im;
//            }
//
//            // compute forward FFT
//            y = bfft(y, 0, y.length);
//
//            // take conjugate again
//            // and divide by n
//            for (int i = 0; i < n; i++) {
//                y[i].im = -y[i].im;
//                y[i].re /= n;
//                y[i].im /= n;
//            }
//
//            return y;
//
//        }
//
//        /**
//         * Returns the circular convolution of the two specified complex arrays.
//         *
//         * @param x one complex array
//         * @param y the other complex array
//         * @return the circular convolution of {@code x} and {@code y}
//         * @throws IllegalArgumentException if the length of {@code x} does not equal
//         *                                  the length of {@code y} or if the length is not a power of 2
//         */
//        public static Complex[] cconvolve(Complex[] x, Complex[] y) {
//
//            // should probably pad x and y with 0s so that they have same length
//            // and are powers of 2
//            if (x.length != y.length) {
//                throw new IllegalArgumentException("Dimensions don't agree");
//            }
//
//            int n = x.length;
//
//            // compute FFT of each sequence
//            Complex[] a = bfft(x, 0, x.length);
//            Complex[] b = bfft(y, 0, y.length);
//
//            // point-wise multiply to a
//            //Complex[] c = new Complex[n];
//            for (int i = 0; i < n; i++) {
//                a[i] = a[i].times(b[i]);
//            }
//
//            // compute inverse FFT
//            return ifft(a);
//        }
//
//        /**
//         * Returns the linear convolution of the two specified complex arrays.
//         *
//         * @param x one complex array
//         * @param y the other complex array
//         * @return the linear convolution of {@code x} and {@code y}
//         * @throws IllegalArgumentException if the length of {@code x} does not equal
//         *                                  the length of {@code y} or if the length is not a power of 2
//         */
//        public static Complex[] convolve(Complex[] x, Complex[] y) {
//            // padding x and y with 0s so that they have same length
//            // and are powers of 2
//            int totalLength = newSize(x.length + y.length);
//
//            Complex[] a = new Complex[totalLength];
//            for (int i = 0; i < x.length; i++)
//                a[i] = x[i];
//            for (int i = x.length; i < totalLength; i++)
//                a[i] = ZERO;
//
//            Complex[] b = new Complex[totalLength];
//            for (int i = 0; i < y.length; i++)
//                b[i] = y[i];
//            for (int i = y.length; i < totalLength; i++)
//                b[i] = ZERO;
//
//            if (isdone == null || isdone.size() < totalLength)
//                isdone = new BitSet(totalLength);
//
//            return cconvolve(a, b);
//        }
//
//        static int newSize(int size) {
//            int powerOf2 = 0;
//
//            while ((1 << powerOf2) < size) {
//                powerOf2++;
//            }
//
//            int newSize = 1 << powerOf2;
//
//            return newSize;
//        }
//    }

    static class FastFourierTransform {
//        class complex {
//            double real;
//            double complex;
//
//            public complex(double real, double complex) {
//                this.real = real;
//                this.complex = complex;
//            }
//        }

        double[] xreal;
        double[] xcomplex;
        double[] yreal;
        double[] ycomplex;

        int xlen;
        int ylen;

        double[] resReal;
        double[] resComplex;

        static BitSet isDone = new BitSet(1048576);

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

        double eps = 1e-1;

        void convolute() {
            int axlen = 0;
            int aylen = 0;
            for (int i = 0; i < xlen; i++) {
                if (Math.abs(xreal[i]) > eps) {
                    xreal[i] = 1;
                    xcomplex[i] = 0;
                    axlen = i + 1;
                } else {
                    xreal[i] = xcomplex[i] = 0;
                }
            }

            for (int i = 0; i < ylen; i++) {
                if (Math.abs(yreal[i]) > eps) {
                    yreal[i] = 1;
                    ycomplex[i] = 0;
                    aylen = i + 1;
                } else {
                    yreal[i] = ycomplex[i] = 0;
                }
            }

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

            //rearrageEvenAndOddIndices(real, complex, startIndex, size);

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

        void rearrageEvenAndOddIndices(double[] real, double[] complex, int startIndex, int size) {
            int halfSize = size / 2;

            isDone.set(startIndex, startIndex + size, false);
            for (int k = 0, kPlus = startIndex; k < size; k++, kPlus++) {
                if (isDone.get(kPlus))
                    continue;

                int nk = -1;
                if (k % 2 == 0) {
                    nk = k / 2;
                } else {
                    nk = halfSize + k / 2;
                }
                int nkPlus = nk + startIndex;

                int ank = nk;
                double prevR = real[kPlus];
                double prevC = complex[kPlus];

                do {
                    double tempR = real[nkPlus];
                    double tempC = complex[nkPlus];

                    real[nkPlus] = prevR;
                    complex[nkPlus] = prevC;

                    isDone.set(nkPlus);

                    prevR = tempR;
                    prevC = tempC;

                    if (nk % 2 == 0) {
                        nk = nk / 2;
                    } else {
                        nk = halfSize + nk / 2;
                    }
                    nkPlus = nk + startIndex;
                } while (nk != ank);
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


    double[] b = new double[1001];
    fft bfft = new fft(b);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            bfft.real[a[i]] = 1;
        }

        double[] ans = dac(k).real;

        StringBuilder sans = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            if (Math.abs(ans[i]) > eps) {
                sans.append(i);
                sans.append(" ");
            }
        }

        out.println(sans.toString());
    }

    double eps = 1e-1;

    class fft {
        double[] real;
        double[] complex;

        public fft(double[] real, double[] complex) {
            this.real = real;
            this.complex = complex;
        }

        public fft(double[] real) {
            this.real = real;
            this.complex = new double[real.length];
        }
    }

    //
    fft dac(int k) {
        if (k == 1) {
            return bfft;
        }

        fft fft = dac(k / 2);
        FastFourierTransform fft2 = new FastFourierTransform(fft.real, fft.complex, fft.real, fft.complex);
        fft2.convolute();

        fft res = new fft(fft2.resReal, fft2.resComplex);

        if (k % 2 == 1) {
            FastFourierTransform fft3 = new FastFourierTransform(res.real, res.complex, bfft.real, bfft.complex);
            fft3.convolute();
            res = new fft(fft3.resReal, fft3.resComplex);
        }

        return res;
    }
}
