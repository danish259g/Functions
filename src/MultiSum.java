/**
 * The MultiSum class represents the sum of multiple functions.
 * It extends the Function class.
 */
public class MultiSum extends Function {
    Function[] multiSumFunctions;
    int numberOfFunctions;
    /**
     * Constructs a MultiSum object with the specified functions.
     * @param multiSumFunctions the functions to be summed
     */
    public MultiSum(Function... multiSumFunctions){
        this.numberOfFunctions = multiSumFunctions.length;
        if ( numberOfFunctions < 2 )
            this.multiSumFunctions[0] = null; // to create an error
        this.multiSumFunctions = new Function[numberOfFunctions];
        for ( int i = 0 ; i < numberOfFunctions ; i++ ){
            this.multiSumFunctions[i] = multiSumFunctions[i]; // maybe write new Function,
        }
    }
    /**
     * Returns the sum of the functions at a given input.
     * @param x the input value
     * @return the sum of the functions
     */
    @Override
    public double valueAt(double x){
        double result = 0;
        for ( int i = 0; i < numberOfFunctions ; i++ ){
            result += multiSumFunctions[i].valueAt(x);
        }
        return result;
    }
    /**
     * Returns a string representation of the sum of the functions.
     * The functions are enclosed in parentheses and separated by addition signs.
     * @return a string representation of the sum of the functions
     */
    @Override
    public String toString(){
        String result = "(";
        for ( int i = 0; i < numberOfFunctions ; i++ ){
            result += multiSumFunctions[i].toString();
            if ( i != numberOfFunctions - 1 )
                result += " + ";
        }
        result += ")";
        return result;
    }
    /**
     * Returns the derivative of the sum of the functions.
     * The derivative is computed by taking the derivative of each function separately.
     * @return the derivative of the sum of the functions
     */
    @Override
    public Function derivative() {
        Function[] derivatives = new Function[ numberOfFunctions ];
        for ( int i = 0; i < numberOfFunctions ; i++ ){
            derivatives[i] = multiSumFunctions[i].derivative();
        }
        return new MultiSum( derivatives );
    }
}
