package com.epam.rd.autotasks;

import java.util.Arrays;

class CycleSwap {

    public static void main(String[] args) {
        int[] first = {1, 3, 2, 7, 4};
        cycleSwap(first);
        System.out.println(Arrays.toString(first));

        int[] second = {1, 3, 2, 7, 4};
        cycleSwap(second, 2);
        System.out.println(Arrays.toString(second));

        int[] third = {1, 3, 2, 7, 4};
        cycleSwap(third, 5);
        System.out.println(Arrays.toString(third));
    }

    public static void cycleSwap(int[] array) {
        cycleSwap(array, 1);
    }

    public static void cycleSwap(int[] array, int shift) {
        if (array.length == 0) {
            return;
        }

        int[] result = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            int newIndex = (i + shift) % array.length;
            result[newIndex] = array[i];
        }

        System.arraycopy(result, 0, array, 0, array.length);
    }
}
