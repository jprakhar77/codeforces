package library;

public class ComplexNumber {
    private final double re;   // the real part
    private final double im;   // the imaginary part

    /**
     * Initializes a complex number from the specified real and imaginary parts.
     *
     * @param real the real part
     * @param imag the imaginary part
     */
    public ComplexNumber(double real, double imag) {
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
    public ComplexNumber plus(ComplexNumber that) {
        double real = this.re + that.re;
        double imag = this.im + that.im;
        return new ComplexNumber(real, imag);
    }

    /**
     * Returns the result of subtracting the specified complex number from
     * this complex number.
     *
     * @param that the other complex number
     * @return the complex number whose value is {@code (this - that)}
     */
    public ComplexNumber minus(ComplexNumber that) {
        double real = this.re - that.re;
        double imag = this.im - that.im;
        return new ComplexNumber(real, imag);
    }

    /**
     * Returns the product of this complex number and the specified complex number.
     *
     * @param that the other complex number
     * @return the complex number whose value is {@code (this * that)}
     */
    public ComplexNumber times(ComplexNumber that) {
        double real = this.re * that.re - this.im * that.im;
        double imag = this.re * that.im + this.im * that.re;
        return new ComplexNumber(real, imag);
    }

    /**
     * Returns the product of this complex number and the specified scalar.
     *
     * @param alpha the scalar
     * @return the complex number whose value is {@code (alpha * this)}
     */
    public ComplexNumber scale(double alpha) {
        return new ComplexNumber(alpha * re, alpha * im);
    }

    /**
     * Returns the product of this complex number and the specified scalar.
     *
     * @param alpha the scalar
     * @return the complex number whose value is {@code (alpha * this)}
     * @deprecated Replaced by {@link #scale(double)}.
     */
    @Deprecated
    public ComplexNumber times(double alpha) {
        return new ComplexNumber(alpha * re, alpha * im);
    }

    /**
     * Returns the complex conjugate of this complex number.
     *
     * @return the complex conjugate of this complex number
     */
    public ComplexNumber conjugate() {
        return new ComplexNumber(re, -im);
    }

    /**
     * Returns the reciprocal of this complex number.
     *
     * @return the complex number whose value is {@code (1 / this)}
     */
    public ComplexNumber reciprocal() {
        double scale = re * re + im * im;
        return new ComplexNumber(re / scale, -im / scale);
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
    public ComplexNumber divides(ComplexNumber that) {
        return this.times(that.reciprocal());
    }

    /**
     * Returns the complex exponential of this complex number.
     *
     * @return the complex exponential of this complex number
     */
    public ComplexNumber exp() {
        return new ComplexNumber(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    /**
     * Returns the complex sine of this complex number.
     *
     * @return the complex sine of this complex number
     */
    public ComplexNumber sin() {
        return new ComplexNumber(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    /**
     * Returns the complex cosine of this complex number.
     *
     * @return the complex cosine of this complex number
     */
    public ComplexNumber cos() {
        return new ComplexNumber(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    /**
     * Returns the complex tangent of this complex number.
     *
     * @return the complex tangent of this complex number
     */
    public ComplexNumber tan() {
        return sin().divides(cos());
    }
}
