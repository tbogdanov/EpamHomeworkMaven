package org.tbogdanov.epamhw.module1.task2;

/**
 * Created by Timur Bogdanov on 10.02.18.
 */
public class SequenceExample {

    public static int findLeastN(double epsilon, boolean verbose) throws Exception {
        if (epsilon <= 0) {
            throw new Exception("epsilon must be greater than 0!");
        }

        double currentA = 0.0;
        int N = 0;
        do {
            N++;
            currentA = 1 / Math.pow(N + 1, 2);
            if (verbose) {
                System.out.printf("a(%d) = %f\n", N, currentA);
            }
        } while (currentA >= epsilon);

        System.out.println(N);
        return N;
    }

    public static void main(String[] args) throws Exception {
        double epsilon = 0.0;
        boolean epsilonSet = false;

        for (String arg: args) {
            try {
                epsilon = Double.parseDouble(arg);
            } catch (NumberFormatException ex) {
                System.err.println(ex.getMessage());
            }
            epsilonSet = true;
        }

        if (!epsilonSet) {
            System.err.println("No epsilon specified!");
            System.exit(1);
        }

        boolean verbose = true;

        try {
            findLeastN(epsilon, verbose);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
