package com.epam.rd.autotasks.meetautocode;

import java.util.Scanner;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();

        int hour = (seconds / 3600) % 24;
        int minute = (seconds / 60) % 60;
        int second = seconds % 60;
        System.out.println(hour + ":" + String.format("%02d:%02d", minute, second));

    }
}
