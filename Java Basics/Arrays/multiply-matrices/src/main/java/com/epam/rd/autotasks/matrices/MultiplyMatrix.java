package com.epam.rd.autotasks.matrices;

import java.util.Arrays;

public class MultiplyMatrix {

    public static int[][] multiply(int[][] matrix1, int[][] matrix2) {

        // Put your code here
        int matrix1Rows = matrix1.length;
        int matrix2Columns = matrix2[0].length;

        int[][] result = new int[matrix1Rows][matrix2Columns];

        for (int row = 0; row < matrix1.length; row++) {

            // Keep one Matrix 1 row selected
            for (int col = 0; col < matrix2[0].length; col++) {

                // Select one Matrix 2 column
                int sum = 0;    

                for (int k = 0; k < matrix1[0].length; k++) {

                    // Move across the Matrix 1 row
                    // and down the Matrix 2 column
                    sum += matrix1[row][k] * matrix2[k][col];
                }

                result[row][col] = sum;
            }
        }

        // sum += firstNum * secondNum;
        return result;
    }

    public static void main(String[] args) {

        System.out.println("Test your code here!\n");

        // Get a result of your code
        int[][] a = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] b = {
            {7, 8},
            {9, 10},
            {11, 12}
        };

        // 7 + 18 + 33 =  58
        int[][] result = multiply(a, b);
        System.out.println(Arrays.deepToString(result).replace("],", "]\n"));
    }
}
