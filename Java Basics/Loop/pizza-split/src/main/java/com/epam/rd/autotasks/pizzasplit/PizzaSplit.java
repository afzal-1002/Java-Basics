package com.epam.rd.autotasks.pizzasplit;

import java.util.Scanner;

public class PizzaSplit {
    public static void main(String[] args) {
        //Write a program, reading number of people and number of pieces per pizza and then
        //printing minimum number of pizzas to order to split all the pizzas equally and with no remainder

        Scanner scanner = new Scanner(System.in);

        int totalPeople = scanner.nextInt();
        int piecesPerPizza = scanner.nextInt();
        boolean found = false;
        int pizzas  = 1;
        int slices = 0;

        while (!found)
        {
            slices = pizzas * piecesPerPizza;
            if (slices % totalPeople == 0)
                found = true;
            else
                pizzas++;
        }
        System.out.println(pizzas);
    }
}
