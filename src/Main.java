import java.util.Random;

public class Main {
    private static Random rnd;

    public static void main(String[] args) {
        test();
    }

    /**
     * Runs tests on various mathematical functions and operations,
     * including constants, sums, products, differences,
     * quotients, and polynomials. It also tests root-finding methods.
     */
    private static void test() {
        rnd = new Random(42);

        Function const1 = new Constant(12345);
        testFunction(const1, "const1", 10);

        Function const2 = new Constant(12.391709);
        testFunction(const2, "const2", 10);

        Function const3 = new Constant(-1709);

        Function prod1 = new Product(
                                     new Sum(
                                             const1,
                                             const2),
                                     const3);
        testFunction(prod1, "prod1", 10);

        Function prod2 = new Product(
                                    new Sum(
                                            const1,
                                            const2),
                                    new Negation(
                                            new Negation(
                                                         const3)));
        testFunction(prod2, "prod2", 10);

        Function diff1 = new Difference(const1, const3);
        testFunction(diff1, "diff1", 10);

        Function quotient1 = new Quotient(
                                          new Sum(
                                                  new Difference(
                                                          new Product(
                                                                  const1,
                                                                  const3),
                                                          const2),
                                                  new Polynomial(1, 0, 1)),
                                          prod1);
        testFunction(quotient1, "quotient1", 4);

        Function multiSum1 = new MultiSum(
                                    new Constant(21.03),
                                    new Constant(3));
        testFunction(multiSum1, "multiSum1", 10);

        Function poly1 = new Polynomial(0, 0, 1, 1.5, 2, 3.1415);
        testFunction(poly1, "poly1", 10);

        Function poly2 = new Polynomial(1, 0, 2, 1.5, 5, 2.17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3);
        testFunction(poly2, "poly2", 20);

        Function multiSum2 = new MultiSum(
                                    new Constant(21.03),
                                    poly1,
                                    new Constant(5),
                                    new Polynomial(3.1415));
        testFunction(multiSum2, "multiSum2", 10);

        Function multiProd1 = new MultiProduct(new Constant(10), new Polynomial(7, 3.5, 2, 1));
        testFunction(multiProd1, "multiProd1", 10);

        Function multiProd2 = new MultiProduct(
                                            new Constant(3),
                                            poly2,
                                            new Negation(
                                                    new Sum(
                                                            new Constant(21),
                                                            poly2)));
        testFunction(multiProd2, "multiProd2", 10);

        Function pow1 = new Power(poly1, 3);
        testFunction(pow1, "pow1", 5);

        Function pow2 = new Power(pow1, 3);
        testFunction(pow2, "pow2", 4);

        Function pow3 = new Power(new Negation(poly1), 2);
        testFunction(pow3, "pow3", 4);

        Function quotient2 = new Quotient(new Polynomial(1, 0, 2.5, 3), new Polynomial(1, 2, 3, 4));
        testFunction(quotient2, "quotient2", 8);

        Function massiveFunc = new Sum(
                                new MultiSum(quotient1, poly1, poly2),
                                new Negation(
                                        new Sum(
                                                quotient1,
                                                new Quotient(
                                                        new Sum(
                                                                new Difference(
                                                                        pow1,
                                                                        const3),
                                                                const2),
                                                        new Negation(
                                                                new Quotient(
                                                                        new Constant(2.17),
                                                                        new Difference(quotient1, new Negation(poly2))))))));
        testFunction(massiveFunc, "massiveFunc", 5);

        testRootFinding();
    }

    /**
     * Tests the methods of a given function, printing its values,
     * derivative, and Taylor polynomial up to a specified order.
     *
     * @param function The function to test
     * @param name The name of the function (for output purposes)
     * @param taylorMaxOrder The maximum order for Taylor polynomial calculation
     */
    private static void testFunction(Function function, String name, int taylorMaxOrder) {
        System.out.println(name + ": " + function);
        System.out.println(name + " with toString: " + function.toString());
        printFunctionValues(function, name, 10);
        System.out.println(name + " derivative: " + function.derivative());
        printTaylorPolynomial(function, name, taylorMaxOrder);
        System.out.println();
    }

    /**
     * Prints the values of the function at randomly generated x values.
     *
     * @param function The function to evaluate
     * @param name The name of the function (for output purposes)
     * @param numberOfValues The number of random values to generate and test
     */
    private static void printFunctionValues(Function function, String name, int numberOfValues) {
        double[] xValues = new double[numberOfValues];
        xValues[0] = 0;
        xValues[1] = 1;
        xValues[2] = -1;

        for (int i = 3; i < numberOfValues; i++) {
            double x = rnd.nextDouble() * 6000 - 3000;  // Random number in range [-3000, 3000]
            x = roundThreePlaces(x);
            xValues[i] = x;
        }


        for (double x : xValues) {
            System.out.println(name + " value at " + x + ": " + function.valueAt(x));
        }
    }

    /**
     * Rounds a given number to three decimal places.
     *
     * @param num The number to round
     * @return The rounded number
     */
    private static double roundThreePlaces(double num) {
        return Double.parseDouble(String.format("%.3f", num));
    }

    /**
     * Prints the Taylor polynomial of the function up to a specified maximum order.
     *
     * @param function The function for which to calculate the Taylor polynomial
     * @param name The name of the function (for output purposes)
     * @param maxOrder The maximum order for Taylor polynomial calculation
     */
    private static void printTaylorPolynomial(Function function, String name, int maxOrder) {
        for (int n = 0; n <= maxOrder; n++) {
            System.out.println(name + " Taylor polynomial of order " + n + ": " + function.taylorPolynomial(n));
        }
    }

    /**
     * Tests root-finding methods on predefined polynomial functions.
     */
    private static void testRootFinding() {
        Function poly3 = new Polynomial(-4, 0, 1);
        System.out.println("poly3: " + poly3);
        printRoot(poly3, "poly3", 0, 5, 1e-5);
        printRoot(poly3, "poly3", 0, 5, -1);
        printRoot(poly3, "poly3", 1, 9.5, 1e-10);
        printRoot(poly3, "poly3", 1.0, 900000.235, 1e-10);
        printRoot(poly3, "poly3", -10, 0, 1e-6);
        System.out.println();

        Function poly4 = new Polynomial(4, 0, -1);
        System.out.println("poly4: " + poly4);
        printRoot(poly4, "poly4", 0, 5, 1e-5);
        printRoot(poly4, "poly4", 0, 5, -1);
        printRoot(poly4, "poly4", 1, 9, 1e-10);
        printRoot(poly4, "poly4", -10, 0, 1e-6);
        System.out.println();

        Function quotient3 = new Quotient(poly3, new Polynomial(0, 0, 1, 0, 1));
        System.out.println("quotient3: " + quotient3);
        printRoot(quotient3, "quotient3", 1, 4, 1e-5);
        printRoot(quotient3, "quotient3", 1, 4, -1);
        printRoot(quotient3, "quotient3", 1, 3, 1e-10);
        printRoot(quotient3, "quotient3", -3, -1, 1e-10);
        printRoot(quotient3, "quotient3", -4, -1, 1e-6);
    }

    /**
     * Prints the root of a function within a specified interval [a, b]
     * using the bisection method and Newton-Raphson method.
     *
     * @param function The function for which to find the root
     * @param name The name of the function (for output purposes)
     * @param a The lower bound of the interval
     * @param b The upper bound of the interval
     * @param epsilon The precision for the root-finding methods
     */
    private static void printRoot(Function function, String name, double a, double b, double epsilon) {
        if (epsilon > 0) {
            System.out.println(name + " root in [" + a + ", " + b + "] and epsilon=" + epsilon + ": " + function.bisectionMethod(a, b, epsilon));
        } else {
            System.out.println(name + " root in [" + a + ", " + b + "] and default epsilon: " + function.bisectionMethod(a, b));
        }
        double mid = (a + b) / 2;
        if (epsilon > 0) {
            System.out.println(name + " root near " + mid + " and epsilon=" + epsilon + ": " + function.newtonRaphsonMethod(mid, epsilon));
        } else {
            System.out.println(name + " root near " + mid + " and default epsilon: " + function.newtonRaphsonMethod(mid));
        }
    }
}