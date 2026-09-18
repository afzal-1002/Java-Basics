package com.epam.rd.autotasks.arrays;

import java.util.Arrays;

public class LocalMaximaRemove {

    public static void main(String[] args) {
        int[] array = new int[]{18, 1, 3, 6, 7, -5};

        System.out.println(Arrays.toString(removeLocalMaxima(array)));
    }

    public static int[] removeLocalMaxima(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0];
        }

        int sum = 1;

        int[] arr = new int[array.length];

        for (int i = 0; i < array.length - 1; i++) {

            if (i == array.length) {
                break;
            }
            if (array[i] > array[i + 1]) {

            } else {
                sum++;
                arr[i] = array[i];
            }
        }
        arr[arr.length - 1] = array[array.length - 1];

        int[] result = new int[sum];

        int k = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {

            } else {
                result[k++] = arr[i];
            }
        }

        return (result);

    }
}
