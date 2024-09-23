/**
 * The Constant class represents a constant function that always returns the same value.
 * It extends the Function class.
 */
public class Constant extends Function {
    final double y;

    /**
     * Constructs a Constant object with the specified constant value
     * @param constant the constant value of the function
     */
    public Constant(double constant) {
        this.y = constant;
    }
    /**
     * Returns the value of the constant function at a given input
     * @param x the input value
     * @return the constant value
     */
    @Override
    public double valueAt(double x) {
        return this.y;
    }
    /**
     * Returns a string representation of the constant function.
     * If the constant value is an integer, it is enclosed in parentheses.
     * Otherwise, the value is represented as is, enclosed in parentheses
     * @return a string representation of the constant function
     */
    @Override
    public String toString() {
        if ( y - (int)y == 0.0)
            return "("+(int)y+")";
        return "(" + y + ")";
    }
    /**
     * Returns the derivative of the constant function, which is always 0
     * @return the derivative of the constant function
     */
    @Override
    public Function derivative() {
        return new Constant(0.0);
    }
}
