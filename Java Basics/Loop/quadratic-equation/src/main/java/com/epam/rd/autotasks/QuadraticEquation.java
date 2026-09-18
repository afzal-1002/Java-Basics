package com.epam.rd.autotasks;

import java.util.Locale;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
//        ax² + bx + c = 0
        // D = b² - 4ac
        double d = b * b -4 * a * c;

        if (d < 0)
        {
            System.out.println("no roots");
        } else if ( d == 0)
        {
//            x = -b / (2a)
            System.out.println(-b / (2 * a));
        }
        else {
//            x₁ = (-b + √D) / (2a)
//            x₂ = (-b - √D) / (2a)

            double x1 = (-b + Math.sqrt(d)) / (2 * a);
            double x2 = (-b - Math.sqrt(d)) / (2 * a);

            System.out.println(x1 + " " + x2);
        }

    }

}