package com.epam.rd.autotasks.matrices;

import java.util.Arrays;

public class TransposeMatrix {

    public static int[][] transpose(int[][] matrix) {

        //Put your code here
        int totalCol = matrix[0].length;
        int totalRow = matrix.length;
        int firstRow = 0;
        int lastRow = totalRow - 1;
        int firstCol = 0;
        int lastCol = totalCol - 1;
        // System.out.println(" totalCol " + totalCol);

        // System.out.println(" totalRow " + matrix.length);
        int[][] result = new int[totalCol][totalRow];

        for (int col = 0; col <= lastCol; col++) {

            for (int row = 0; row <= lastRow; row++) {
                result[col][row] = matrix[row][col];

       
            }
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println("Test your code here!\n");

        // Get a result of your code
        int[][] matrix = {
            {1, 2, 3},
            {7, -13, 5},
            {4, 7, 10}
        };

        int[][] result = transpose(matrix);
        System.out.println(Arrays.deepToString(result).replace("],", "]\n"));
    }

}
