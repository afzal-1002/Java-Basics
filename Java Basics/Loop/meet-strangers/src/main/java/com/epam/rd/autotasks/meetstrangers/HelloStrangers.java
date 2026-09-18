package com.epam.rd.autotasks.meetstrangers;

import java.io.IOException;
import java.util.Scanner;

public class HelloStrangers {
    public static void main(String[] args) throws IOException {
        //Write a program, asks for a number - amount of strangers to meet.
        //Then reads stranger names line by line and prints line by line "Hello, ...".

            Scanner sc = new Scanner(System.in);

            int count = sc.nextInt();
            sc.nextLine();

            if (count == 0) {
                System.out.println("Oh, it looks like there is no one here");
            } else if (count < 0) {
                System.out.println("Seriously? Why so negative?");
            } else {
                int i = 0;
                while (i < count) {
                    String name = sc.nextLine();
                    System.out.println("Hello, " + name);
                    i++;
                }
            }
    }
}
