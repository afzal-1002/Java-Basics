package com.epam.rd.autotasks;

public class Average {

    public static void main(String[] args) {

        // Scanner scanner = new Scanner(System.in);
        // // Use Scanner methods to read input
        // int sum = 0;
        // int count = 0;
        // int number;
        // number = scanner.nextInt();
        // while (number != 0)
        // {
        //     sum += number;
        //     count++;
        //     number = scanner.nextInt();
        // }
        // System.out.println(sum/count);
        // System.out.println(compressString("aabba"), 3);
        System.out.println(compressString("aabba", 3));

        System.out.println(compressString("aabbbbaaa", 3));

    }

    public static String compressString(String str, int size) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int strLen = str.length();

        for (int i = 0; i < strLen; i++) {
            char currChar = str.charAt(i);
            int matchIndex = i;

            // Count consecutive characters
            while (matchIndex < strLen && str.charAt(matchIndex) == currChar) {
                matchIndex++;
            }

            int count = matchIndex - i;

            // Apply compression if count >= 3
            if (count >= size) {
                result.append("#").append(count).append(currChar);
            } else {
                for (int j = 0; j < count; j++) {
                    result.append(currChar);
                }
            }

            // Set i to the end of the current group.
            // The loop's i++ will then advance i to the next new character.
            i = matchIndex - 1;
        }

        return result.toString();
    }

}
