package org.tbogdanov.epamhw.module1.task4;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Timur Bogdanov on 10.02.18.
 */
public class VectorMaxExample {
    public static final int DEF_DIM = 1;

    public static int findMax (int[] vector, boolean verbose) throws Exception {
        int dim = vector.length;

        if (dim <= 0) {
            throw new Exception("Empty vector specified!");
        }

        if (dim % 2 == 1) {
            throw new Exception("N should be divisible by 2");
        }

        int half = dim / 2;
        int max = vector[0] + vector[dim-1];
        int sum = 0;
        int j = 0;
        System.out.printf("a[%d] + a[%d] = %d\n", 1, dim, max);

        for (int i = 1; i < half; i++) {
            j = dim - i - 1;
            sum = vector[i] + vector[j];

            if (verbose) {  // +1 because vector specified in the task starts with 1
                System.out.printf("a[%d] + a[%d] = %d\n", i + 1, j + 1, sum);
            }

            if (sum > max) {
                max = sum;
            }
        }

        if (verbose) {
            System.out.printf("Max value: %d\n", max);
        }

        return max;
    }

    public static void printVector (int[] vector) {
        for (int x : vector) {
            System.out.printf("%d ", x);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int dim = 0;
        int vectorMaxDim = DEF_DIM;
        int[] vector = new int[vectorMaxDim];

        for (String arg: args) {
            try {
                vector[dim] = Integer.parseInt(arg);
            } catch (NumberFormatException ex) {
                System.err.println(ex.getMessage());
            }
            dim++;
            if (dim >= vectorMaxDim) { // Dynamic vector enlarge
                vectorMaxDim *= 2;
                vector = Arrays.copyOf(vector, vectorMaxDim);
            }
        }

        boolean verbose = true;

        vector = Arrays.copyOf(vector, dim);
        printVector(vector);
        try {
            findMax(vector, verbose);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println("Usage: a1 a2 ... aN for specific values");
            System.err.println("N should be divisible by 2");
        }
    }
}
