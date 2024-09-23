/**
 * The Negation class represents the negation of a function.
 * It extends the Function class.
 */
public class Negation extends Function{
    private Function f;
    /**
     * Constructs a Negation object with the specified function.
     * @param f the function to be negated
     */
    public Negation(Function f){
        this.f = f;
    }
    /**
     * Returns the negation of the function at a given input.
     * @param x the input value
     * @return the negation of the function
     */
    @Override
    public double valueAt(double x){
        return -f.valueAt(x);
    }
    /**
     * Returns a string representation of the negation of the function.
     * The function is enclosed in parentheses and prefixed with a negative sign.
     * @return a string representation of the negation of the function
     */
    @Override
    public String toString(){
        return "(-" + f.toString() + ")";
    }
    /**
     * Returns the derivative of the negation of the function.
     * The derivative is computed by taking the derivative of the inner function and negating it.
     * @return the derivative of the negation of the function
     */
    @Override
    public Function derivative(){
        return new Negation(f.derivative());
    }
}
