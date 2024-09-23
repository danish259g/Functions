/**
 * Represents the product of two functions.
 */
public class Product extends Function{
    private Function f;
    private Function g;

    /**
     * Constructs a new Product object with the given factors.
     * @param f the first factor
     * @param g the second factor
     */
    public Product(Function f, Function g){
        this.f = f;
        this.g = g;
    }

    /**
     * Computes the value of the product of the two functions at the given input.
     * @param x the input value
     * @return the result of multiplying the value of the first factor by the value of the second factor at the given input
     */
    @Override
    public double valueAt(double x){
        return f.valueAt(x) * g.valueAt(x);
    }
    /**
     * Returns a string representation of the product function.
     * @return a string representation of the product function in the format "(f * g)"
     */
    @Override
    public String toString() {
        return "(" + f.toString() + " * " + g.toString() + ")";
    }
    /**
     * Computes the derivative of the product function.
     * @return the derivative of the product function as a new Function object
     */
    @Override
    public Function derivative() {
        Product a = new Product(f.derivative(), g);
        Product b = new Product(g.derivative(), f);
        return new Sum(a,b);
    }
}