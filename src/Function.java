/**
 * The abstract base class for mathematical functions.
 * Subclasses of Function must implement the abstract methods to provide specific function behavior.
 */
abstract class Function {
    /**
     * Calculates the value of the function at a given point.
     * @param x the input value
     * @return the calculated value of the function
     */
    public abstract double valueAt(double x);
    /**
     * Returns a string representation of the functions
     * @return a string representation of the function
     */
    public abstract String toString();
    /**
     * Returns the derivative of the function.
     * @return the derivative of the function as a new Function object
     */
    public abstract Function derivative();
    /**
     * Applies the bisection method to find the root of the function within a given interval.
     * @param a the left endpoint of the interval
     * @param b the right endpoint of the interval
     * @param epsilon the desired precision of the root
     * @return the approximated root of the function within the specified interval
     */
    public double bisectionMethod(double a, double b, double epsilon){
        double right = b;
        double left = a;
        while ( right - left > epsilon ){
            double mid = (left + right) / 2;
            if ( valueAt(left) * valueAt(mid) > 0 )
                left = mid;
            else
                right = mid;
        }
        return (left + right) / 2;
    }
    /**
     * Applies the bisection method to find the root of the function within a given interval.
     * Uses a default epsilon value of 10^-5.
     * @param a the left endpoint of the interval
     * @param b the right endpoint of the interval
     * @return the approximated root of the function within the specified interval
     */
    public double bisectionMethod(double a, double b) {
        double right = b;
        double left = a;
        while (right - left > Math.pow(10.0,-5.0) ) {
            double mid = (left + right) / 2;
            if (valueAt(left) * valueAt(mid) > 0)
                left = mid;
            else
                right = mid;
        }
        return (left + right) / 2;
    }
    /**
     * Applies the Newton Raphson method to find the root of the function starting from a given initial guess.
     * @param a the initial guess for the root
     * @param epsilon the desired precision of the root
     * @return the approximated root of the function
     */
    public double newtonRaphsonMethod(double a, double epsilon){
        double currentX = a;
        double nextX = a - ( valueAt(a) / derivative().valueAt(a) );
        double temp;
        while ( Math.abs( valueAt(currentX)) >= epsilon ){
            temp = nextX;
            nextX = currentX - ( valueAt(currentX) / derivative().valueAt(currentX) );
            currentX = temp;
        }
        return currentX;
    }
    /**
     * Applies the Newton Raphson method to find the root of the function starting from a given initial guess.
     * Uses a default epsilon value of 10^-5.
     * @param a the initial guess for the root
     * @return the approximated root of the function
     */
    public double newtonRaphsonMethod(double a){
        double currentX = a;
        double nextX = a - ( valueAt(a) / derivative().valueAt(a) );
        double temp;
        while ( Math.abs( valueAt(currentX)) >= Math.pow(10.0,-5.0) ){
            temp = nextX;
            nextX = currentX - ( valueAt(currentX) / derivative().valueAt(currentX) );
            currentX = temp;
        }
        return currentX;
    }
    /**
     * Calculates the Taylor polynomial of the function up to the specified degree.
     * @param n the degree of the Taylor polynomial
     * @return the Taylor polynomial of the function as a new Function object
     */
    public Function taylorPolynomial(int n){
        double []coefficients = new double[n+1];
        double factorial = 1;
        Function temp = this;
        coefficients[0] = valueAt(0.0);
        for ( int i = 1 ; i <= n ; i++ ){
            coefficients[i] = temp.derivative().valueAt(0.0)  / factorial;
            factorial = factorial * (i+1);
            temp = temp.derivative();
        }
        return new Polynomial(coefficients);
    }
}
