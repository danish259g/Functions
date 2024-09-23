/**
 * The Difference class represents the difference of two functions.
 * It extends the Function class.
 */
public class Difference extends Function{
    private Function f;
    private Function g;

    /**
     * Constructs a Difference object with the specified functions.
     * @param f the first function
     * @param g the second function
     */
    public Difference(Function f, Function g){
        this.f = f;
        this.g = g;
    }
    /**
     * Returns the difference of the two functions at a given input.
     * @param x the input value
     * @return the difference of the two functions
     */
    @Override
    public double valueAt(double x){
        return f.valueAt(x) - g.valueAt(x);
    }
    /**
     * Returns a string representation of the difference of the two functions.
     * The functions are enclosed in parentheses and separated by a subtraction sign.
     * @return a string representation of the difference of the two functions
     */
    @Override
    public String toString() {
        return "(" + f.toString() + " - " + g.toString() + ")";
    }
    /**
     * Returns the derivative of the difference of the two functions.
     * The derivative is computed by taking the derivative of each function separately.
     * @return the derivative of the difference of the two functions
     */
    @Override
    public Function derivative() {
        return new Difference( f.derivative(), g.derivative() );
    }
}