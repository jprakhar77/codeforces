package library;

import java.util.Arrays;
import java.util.BitSet;

class Complex {
    private final double re;   // the real part
    private final double im;   // the imaginary part

    /**
     * Initializes a complex number from the specified real and imaginary parts.
     *
     * @param real the real part
     * @param imag the imaginary part
     */
    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    /**
     * Returns a string representation of this complex number.
     *
     * @return a string representation of this complex number,
     * of the form 34 - 56i.
     */
    public String toString() {
        if (im == 0) return re + "";
        if (re == 0) return im + "i";
        if (im < 0) return re + " - " + (-im) + "i";
        return re + " + " + im + "i";
    }

    /**
     * Returns the absolute value of this complex number.
     * This quantity is also known as the <em>modulus</em> or <em>magnitude</em>.
     *
     * @return the absolute value of this complex number
     */
    public double abs() {
        return Math.hypot(re, im);
    }

    /**
     * Returns the phase of this complex number.
     * This quantity is also known as the <em>angle</em> or <em>argument</em>.
     *
     * @return the phase of this complex number, a real number between -pi and pi
     */
    public double phase() {
        return Math.atan2(im, re);
    }

    /**
     * Returns the sum of this complex number and the specified complex number.
     *
     * @param that the other complex number
     * @return the complex number whose value is {@code (this + that)}
     */
    public Complex plus(Complex that) {
        double real = this.re + that.re;
        double imag = this.im + that.im;
        return new Complex(real, imag);
    }

    /**
     * Returns the result of subtracting the specified complex number from
     * this complex number.
     *
     * @param that the other complex number
     * @return the complex number whose value is {@code (this - that)}
     */
    public Complex minus(Complex that) {
        double real = this.re - that.re;
        double imag = this.im - that.im;
        return new Complex(real, imag);
    }

    /**
     * Returns the product of this complex number and the specified complex number.
     *
     * @param that the other complex number
     * @return the complex number whose value is {@code (this * that)}
     */
    public Complex times(Complex that) {
        double real = this.re * that.re - this.im * that.im;
        double imag = this.re * that.im + this.im * that.re;
        return new Complex(real, imag);
    }

    /**
     * Returns the product of this complex number and the specified scalar.
     *
     * @param alpha the scalar
     * @return the complex number whose value is {@code (alpha * this)}
     */
    public Complex scale(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    /**
     * Returns the product of this complex number and the specified scalar.
     *
     * @param alpha the scalar
     * @return the complex number whose value is {@code (alpha * this)}
     * @deprecated Replaced by {@link #scale(double)}.
     */
    @Deprecated
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    /**
     * Returns the complex conjugate of this complex number.
     *
     * @return the complex conjugate of this complex number
     */
    public Complex conjugate() {
        return new Complex(re, -im);
    }

    /**
     * Returns the reciprocal of this complex number.
     *
     * @return the complex number whose value is {@code (1 / this)}
     */
    public Complex reciprocal() {
        double scale = re * re + im * im;
        return new Complex(re / scale, -im / scale);
    }

    /**
     * Returns the real part of this complex number.
     *
     * @return the real part of this complex number
     */
    public double re() {
        return re;
    }

    /**
     * Returns the imaginary part of this complex number.
     *
     * @return the imaginary part of this complex number
     */
    public double im() {
        return im;
    }

    /**
     * Returns the result of dividing the specified complex number into
     * this complex number.
     *
     * @param that the other complex number
     * @return the complex number whose value is {@code (this / that)}
     */
    public Complex divides(Complex that) {
        return this.times(that.reciprocal());
    }

    /**
     * Returns the complex exponential of this complex number.
     *
     * @return the complex exponential of this complex number
     */
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    /**
     * Returns the complex sine of this complex number.
     *
     * @return the complex sine of this complex number
     */
    public Complex sin() {
        return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    /**
     * Returns the complex cosine of this complex number.
     *
     * @return the complex cosine of this complex number
     */
    public Complex cos() {
        return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    /**
     * Returns the complex tangent of this complex number.
     *
     * @return the complex tangent of this complex number
     */
    public Complex tan() {
        return sin().divides(cos());
    }
}

public class FFT {

    private static final Complex ZERO = new Complex(0, 0);

    // Do not instantiate.
    private FFT() {
    }

    /**
     * Returns the FFT of the specified complex array.
     *
     * @param x the complex array
     * @return the FFT of the complex array {@code x}
     * @throws IllegalArgumentException if the length of {@code x} is not a power of 2
     */
    public static Complex[] fft(Complex[] x) {
        int n = x.length;

        // base case
        if (n == 1) {
            return new Complex[]{x[0]};
        }

        // radix 2 Cooley-Tukey FFT
        if (n % 2 != 0) {
            throw new IllegalArgumentException("n is not a power of 2");
        }

        // fft of even terms
        Complex[] even = new Complex[n / 2];
        for (int k = 0; k < n / 2; k++) {
            even[k] = x[2 * k];
        }
        Complex[] q = fft(even);

        // fft of odd terms
        Complex[] odd = even;  // reuse the array
        for (int k = 0; k < n / 2; k++) {
            odd[k] = x[2 * k + 1];
        }
        Complex[] r = fft(odd);

        // combine
        Complex[] y = new Complex[n];
        for (int k = 0; k < n / 2; k++) {
            double kth = -2 * k * Math.PI / n;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k] = q[k].plus(wk.times(r[k]));
            y[k + n / 2] = q[k].minus(wk.times(r[k]));
        }
        return y;
    }


    /**
     * Returns the inverse FFT of the specified complex array.
     *
     * @param x the complex array
     * @return the inverse FFT of the complex array {@code x}
     * @throws IllegalArgumentException if the length of {@code x} is not a power of 2
     */
    public static Complex[] ifft(Complex[] x) {
        int n = x.length;
        Complex[] y = new Complex[n];

        // take conjugate
        for (int i = 0; i < n; i++) {
            y[i] = x[i].conjugate();
        }

        // compute forward FFT
        y = fft(y);

        // take conjugate again
        for (int i = 0; i < n; i++) {
            y[i] = y[i].conjugate();
        }

        // divide by n
        for (int i = 0; i < n; i++) {
            y[i] = y[i].scale(1.0 / n);
        }

        return y;

    }

    /**
     * Returns the circular convolution of the two specified complex arrays.
     *
     * @param x one complex array
     * @param y the other complex array
     * @return the circular convolution of {@code x} and {@code y}
     * @throws IllegalArgumentException if the length of {@code x} does not equal
     *                                  the length of {@code y} or if the length is not a power of 2
     */
    public static Complex[] cconvolve(Complex[] x, Complex[] y) {

        // should probably pad x and y with 0s so that they have same length
        // and are powers of 2
        if (x.length != y.length) {
            throw new IllegalArgumentException("Dimensions don't agree");
        }

        int n = x.length;

        // compute FFT of each sequence
        Complex[] a = fft(x);
        Complex[] b = fft(y);

        // point-wise multiply
        Complex[] c = new Complex[n];
        for (int i = 0; i < n; i++) {
            c[i] = a[i].times(b[i]);
        }

        // compute inverse FFT
        return ifft(c);
    }

    /**
     * Returns the linear convolution of the two specified complex arrays.
     *
     * @param x one complex array
     * @param y the other complex array
     * @return the linear convolution of {@code x} and {@code y}
     * @throws IllegalArgumentException if the length of {@code x} does not equal
     *                                  the length of {@code y} or if the length is not a power of 2
     */
    public static Complex[] convolve(Complex[] x, Complex[] y) {
        // padding x and y with 0s so that they have same length
        // and are powers of 2
        adjustSize(x, y);

        Complex[] a = new Complex[2 * x.length];
        for (int i = 0; i < x.length; i++)
            a[i] = x[i];
        for (int i = x.length; i < 2 * x.length; i++)
            a[i] = ZERO;

        Complex[] b = new Complex[2 * y.length];
        for (int i = 0; i < y.length; i++)
            b[i] = y[i];
        for (int i = y.length; i < 2 * y.length; i++)
            b[i] = ZERO;

        return cconvolve(a, b);
    }

    static void adjustSize(Complex[] x, Complex[] y) {
        int sizeX = x.length;
        int sizeY = y.length;

        int maxSize = Math.max(sizeX, sizeY);

        int powerOf2 = 0;

        while ((1 << powerOf2) < maxSize) {
            powerOf2++;
        }

        int newSize = 1 << powerOf2;

        Complex[] xAdjusted = new Complex[newSize];

        System.arraycopy(x, 0, xAdjusted, 0, sizeX);

        Arrays.fill(xAdjusted, sizeX, newSize, ZERO);

        Complex[] yAdjusted = new Complex[newSize];

        System.arraycopy(y, 0, yAdjusted, 0, sizeY);

        Arrays.fill(yAdjusted, sizeY, newSize, ZERO);

        x = xAdjusted;
        y = yAdjusted;
    }

    BitSet isDone = new BitSet(1 << 20);

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

}