public class Sum extends Function {
    private Function f;
    private Function g;

    public Sum(Function f, Function g){
        this.f = f;
        this.g = g;
    }
    @Override
    public double valueAt(double x){
        return f.valueAt(x) + g.valueAt(x);
    }
    @Override
    public String toString() {
        return "(" + f.toString() + " + " + g.toString() + ")";
    }
    @Override
    public Function derivative() {
        return new Sum( f.derivative(), g.derivative() );
    }
}
