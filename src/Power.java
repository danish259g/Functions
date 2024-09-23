/**
 * Represents a function raised to a power.
 */
public class Power extends Function{
    private Function base;
    private int exponent;

    /**
     * Constructs a new Power object with the given base and exponent.
     * @param base the base function
     * @param exponent the exponent value
     */
    public Power(Function base, int exponent){
        this.base = base;
        this.exponent = exponent;
    }
    /**
     * Computes the value of the function raised to the specified exponent at the given input.
     * @param x the input value
     * @return the result of raising the base function to the exponent power at the given input
     */
    @Override
    public double valueAt(double x){
        return Math.pow( base.valueAt(x),exponent );
    }
    /**
     * Returns a string representation of the power function.
     * @return a string representation of the power function in the format "(base^exponent)"
     */
    @Override
    public String toString(){
        return "(" + base + "^" + exponent + ")";
    }
    /**
     * Computes the derivative of the power function.
     * @return the derivative of the power function as a new Function object
     */
    @Override
    public Function derivative(){
        if ( exponent == 1)
            return base.derivative();
        return new MultiProduct( new Constant(exponent), new Power( base, exponent-1), base.derivative() );
    }
}
