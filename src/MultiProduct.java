/**
 * The MultiProduct class represents the product of multiple functions.
 * It extends the Function class.
 */
public class MultiProduct extends Function{
    Function[] multiProductFunctions;
    int numberOfFunctions;

    /**
     * Constructs a MultiProduct object with the specified functions.
     * @param multiProductFunctions the functions to be multiplied together
     */
    public MultiProduct(Function... multiProductFunctions){
        this.numberOfFunctions = multiProductFunctions.length;
        if (numberOfFunctions < 2 )
            this.multiProductFunctions[0] = null;
        this.multiProductFunctions = new Function[numberOfFunctions];
        for ( int i = 0 ; i < numberOfFunctions ; i++ ){
            this.multiProductFunctions[i] = multiProductFunctions[i];
        }
    }
    /**
     * Constructs a MultiProduct object with the specified derivative and functions.
     * @param derivative the derivative function
     * @param derivativedFunctionIndex the index of the derivative function in the original functions array
     * @param functions the original functions array
     */
    public MultiProduct(Function derivative,int derivativedFunctionIndex, Function... functions){
        this.numberOfFunctions = functions.length;
        this.multiProductFunctions = new Function[numberOfFunctions];
        this.multiProductFunctions[0] = derivative;
        int currentIndex = 1 ;
        for ( int i = 0 ; i < numberOfFunctions ; i++ ){
            if ( i != derivativedFunctionIndex ) {
                this.multiProductFunctions[currentIndex] = functions[i];
                currentIndex++;
            }
        }
    }
    /**
     * Returns the product of the functions at a given input.
     * @param x the input value
     * @return the product of the functions
     */
    @Override
    public double valueAt(double x){
        double result = 1;
        for ( int i = 0; i < numberOfFunctions ; i++ ){
            result *= multiProductFunctions[i].valueAt(x);
        }
        return result;
    }
    /**
     * Returns a string representation of the product of the functions.
     * The functions are enclosed in parentheses and separated by multiplication signs.
     * @return a string representation of the product of the functions
     */
    @Override
    public String toString(){
        String result = "(";
        for ( int i = 0; i < numberOfFunctions ; i++ ){
            result += multiProductFunctions[i].toString();
            if ( i != numberOfFunctions - 1 )
                result += " * ";
        }
        result += ")";
        return result;
    }
    /**
     * Returns the derivative of the product of the functions.
     * The derivative is computed by applying the product rule and creating a MultiSum object.
     * @return the derivative of the product of the functions
     */
    @Override
    public Function derivative() {
        MultiProduct[] result = new MultiProduct[numberOfFunctions];
        Function[] derivatives = new Function[numberOfFunctions];
        for ( int i = 0; i < numberOfFunctions; i++ ){
            derivatives[i] = multiProductFunctions[i].derivative();
        }
        for ( int i = 0; i < numberOfFunctions ; i++ ){
            MultiProduct currentDerivative = new MultiProduct(derivatives[i], i, multiProductFunctions);
            result[i] = currentDerivative;
        }
        return new MultiSum(result);
    }
}
