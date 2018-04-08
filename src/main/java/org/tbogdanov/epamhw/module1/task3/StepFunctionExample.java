package org.tbogdanov.epamhw.module1.task3;

/**
 * Created by Timur Bogdanov on 10.02.18.
 */
public class StepFunctionExample {

    public static double func(double x) {
        return Math.tan(2 * x) - 3;
    }

    public static void printFunctionTable(double a, double b, double h) throws Exception {

        // At least we can print F(a)
        System.out.printf("%12s %12s\n","x", "Value");
        System.out.printf("%12f %12f\n", a, func(a));

        if (a == b) {
            return;
        }

        /*  We have 2 causes when a, b, h specified could cause an infinite loop
            1) h == 0
            2) a > b and h > 0; or a < b and h < 0
         */
        if ((h == 0) || ((a < b) != (h > 0))) {
            throw new Exception("The step specified causes an infinite loop");
        }

        // Print the rest (if needed)
        for (double x = a + h; x <= b; x += h) {
            System.out.printf("%12f %12f\n", x, func(x));
        }
    }

    public static void main(String[] args) {

        if (args.length < 3) {
            System.err.println("At least 3 arguments (a, b, h) needed!");
            System.exit(1);
        }

        double a = 0;
        double b = 0;
        double h = 0;
        try {
            a = Double.parseDouble(args[0]);
            b = Double.parseDouble(args[1]);
            h = Double.parseDouble(args[2]);
        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
        }

        try {
            printFunctionTable(a, b, h);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }


}
