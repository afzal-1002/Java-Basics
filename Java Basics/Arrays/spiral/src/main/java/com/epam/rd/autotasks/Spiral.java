package com.epam.rd.autotasks;

class Spiral {

    static int[][] spiral(int rows, int columns) {
        int[][] result = new int[rows][columns];

        int number = 1;

        int firstRow = 0;
        int lastRow = rows - 1;
        int firstCol = 0;
        int lastCol = columns - 1;

        while (number <= rows * columns) {

            // Fill first row: left → right
            for (int col = firstCol; col <= lastCol; col++) {
                result[firstRow][col] = number++;
            }
            firstRow++;

            // Fill last column: top → bottom
            for (int row = firstRow; row <= lastRow; row++) {
                result[row][lastCol] = number++;
            }
            lastCol--;

            // Fill last row: right → left
            if (firstRow <= lastRow) {
                for (int col = lastCol; col >= firstCol; col--) {
                    result[lastRow][col] = number++;
                }
                lastRow--;
            }

            // Fill first column: bottom → top
            if (firstCol <= lastCol) {
                for (int row = lastRow; row >= firstRow; row--) {
                    result[row][firstCol] = number++;
                }
                firstCol++;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[][] result = Spiral.spiral(10, 6);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 6; j++) {

                System.out.printf("%d \t", result[i][j]);
            }
            System.out.println();
        }

        // System.out.println(Arrays.toString(Spiral.spiral(5, 4)));
    }
}
