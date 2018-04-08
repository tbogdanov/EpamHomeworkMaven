package org.tbogdanov.epamhw.module1.task5;

/**
 * Created by Timur Bogdanov on 10.02.18.
 */
public class MatrixExample {

    public static void printMatrix (int[][] matrix) {
        for (int[] row : matrix) {
            for (int x : row) {
                System.out.printf("%1d ", x);
            }
            System.out.println();
        }
    }

    public static int[][] makeSquareMatrix (int dim) throws Exception {
        if (dim <= 0) {
            throw new Exception("The dimension must be greater than 0!");
        }

        int[][] result = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                result[i][j] = (j == i || j == dim - i - 1) ? 1 : 0;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("At least 1 argument (n) needed!");
            System.exit(1);
        }

        int dim = 0;
        int[][] resultMatrix;
        try {
            dim = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
        }

        try {
            resultMatrix = makeSquareMatrix(dim);
            printMatrix(resultMatrix);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
