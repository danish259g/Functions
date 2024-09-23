public class Quotient extends Function{
    private Function f;
    private Function g;

    public Quotient(Function f, Function g){
        this.f = f;
        this.g = g;
    }
    @Override
    public double valueAt(double x){
        return f.valueAt(x) / g.valueAt(x);
    }
    @Override
    public String toString() {
        return "(" + f.toString() + " / " + g.toString() + ")";
    }
    @Override
    public Function derivative() {
        Product ftagg = new Product(f.derivative(), g);
        Product fgtag = new Product(g.derivative(), f);
        Difference numerator = new Difference(ftagg, fgtag);
        Power denominator = new Power(g,2);
        return new Quotient(numerator,denominator);
    }
}
