package com.epam.rd.autotasks.sequence;
import java.util.Scanner;

public class FindMaxInSeq {
    public static int max() {

        // Put your code here
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();   // first number
        int max = num;

        while (num != 0) {
            num = sc.nextInt();

            if (num != 0 && num > max) {
                max = num;
            }
        }

        return max;

    }

    public static void main(String[] args) {

        System.out.println("Test your code here!\n");

        // Get a result of your code

        System.out.println(max());
    }
}
