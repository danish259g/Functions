/**
 * Represents a polynomial function.
 */
public class Polynomial extends Function {
    private final double[] coefficients;
    int numberOfCoefficients;

    /**
     * Constructs a new Polynomial object with the given coefficients.
     * @param coefficients the coefficients of the polynomial function
     */
    public Polynomial(double... coefficients){
        this.numberOfCoefficients = coefficients.length;
        this.coefficients = new double[numberOfCoefficients];
        for (int i = 0; i < numberOfCoefficients; i++){
            this.coefficients[i] = coefficients[i];
        }
    }

    /**
     * Computes the value of the polynomial function at the given input.
     * @param x the input value
     * @return the result of evaluating the polynomial function at the given input
     */
    @Override
    public double valueAt(double x) {
        double sum = 0.0;
        for (int i = 0; i < numberOfCoefficients; i++ ){
            sum += coefficients[i] * Math.pow(x,i);
        }
        return  sum;
    }

    /**
     * Returns a string representation of the polynomial function.
     * @return a string representation of the polynomial function in the format "(a0 + a1x + a2x^2 + ...)"
     */
    @Override
    public String toString() {
        String result = "(";
        boolean firstCoefficientPrinted = false;
        for (int i = 0; i < numberOfCoefficients; i++ ){
            if ( this.coefficients[i] != 0.0 ){
                if ( coefficients[i] > 0.0 ) {
                    if ( firstCoefficientPrinted )
                        result += " + ";
                }
                else if ( firstCoefficientPrinted )
                    result += " - ";
                else
                    result += "-";

                if ( i != 0 ) {
                    if ((coefficients[i] == 1.0 || coefficients[i] == -1.0)) {
                        result += "x";
                        firstCoefficientPrinted = true;
                    } else if (coefficients[i] - (int) coefficients[i] == 0.0) {
                        result += Math.abs((int) coefficients[i]) + "x";
                        firstCoefficientPrinted = true;
                    } else{
                        result += Math.abs(coefficients[i]) + "x";
                        firstCoefficientPrinted = true;
                    }
                }
                else {
                    if (coefficients[i] - (int) coefficients[i] == 0.0) {
                        result += Math.abs((int) coefficients[i]);
                        firstCoefficientPrinted = true;
                    } else {
                        result += Math.abs(coefficients[i]);
                        firstCoefficientPrinted = true;
                    }
                }


                if ( i != 0 && i != 1 )
                    result += "^"+i;
                }
            }
        result += ")";
        if ( result.length() == 2 )
            return "(0)";
        return result;
    }

    /**
     * Computes the derivative of the polynomial function.
     * @return the derivative of the polynomial function as a new Function object
     */
    @Override
    public Function derivative() {
        double[] derivativedCoefficients = new double[numberOfCoefficients];
        for ( int i = 0 ; i < numberOfCoefficients ; i++ ){
            derivativedCoefficients[i] = coefficients[i];
        }
        for (int i = 0; i < numberOfCoefficients -1 ; i++ ){
            derivativedCoefficients[i] = derivativedCoefficients[i+1];
            derivativedCoefficients[i] *= (i+1);
        }
        derivativedCoefficients[numberOfCoefficients -1] = 0;
        return new Polynomial(derivativedCoefficients);
    }
}
