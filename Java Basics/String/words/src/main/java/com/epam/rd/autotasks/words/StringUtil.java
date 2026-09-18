package com.epam.rd.autotasks.words;

import java.util.Arrays;
import java.util.stream.Stream;

public class StringUtil {

    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {

        if (words == null || sample == null) { return 0; }

        String normalizedSample = sample.trim();
        
        if (normalizedSample.isEmpty()) { return 0;}

        int count = 0;
        for (String word : words) {
            if (word != null && word.trim().equalsIgnoreCase(normalizedSample)) {
                count++;
            }
        }
        return count;
    }

    public static String[] splitWords(String text) {

        if (text == null || text.isEmpty()) { return null;}

        String trimmed = text.trim();
        if (trimmed.isEmpty() || trimmed.matches("[\\s,.:;!?]+")) {
            return null;
        }

        String[] parts = trimmed.split("[\\s,.:;!?]+");

        int count = 0;

        for (String word : parts) {
            if (!word.isEmpty()) {
                count++;
            }
        }

        String[] words = new String[count];

        int index = 0;

        for (String word : parts) {
            if (!word.isEmpty()) {
                words[index] = word;
                index++;
            }
        }
        return words.length == 0 ? null : words;
    }

    public static String convertPath(String path, boolean toWin) {

        if (path == null || path.isEmpty() || isIllegalPath(path)) {
            return null;
        }

        if (toWin) {
            return path.contains("\\") ? path : unixToWindows(path);
        }

        return path.contains("/") || !path.contains("\\") ? path : windowsToUnix(path);
    }

    public static String joinWords(String[] words) {

        if (words == null || words.length == 0) {
            return null;
        }

        StringBuilder joined = new StringBuilder();
        for (String word : words) {
            if (word != null && !word.isEmpty()) {
                if (joined.length() > 0) {
                    joined.append(", ");
                }
                joined.append(word);
            }
        }

        if (joined.length() == 0) {
            return null;
        }

        return '[' + joined.toString() + ']';
    }

    private static boolean isIllegalPath(String path) {
        if (path.contains("/") && path.contains("\\")) {
            return true;
        }

        if (path.indexOf('~') >= 0 && !(path.equals("~") || path.startsWith("~/"))) {
            return true;
        }

        if (path.startsWith("~/") && path.substring(2).contains("~")) {
            return true;
        }

        int driveIndex = path.indexOf("C:");
        if (driveIndex > 0 || path.indexOf("C:", 2) >= 0) {
            return true;
        }

        if (path.contains("///") || path.contains("\\\\\\")) {
            return true;
        }

        return false;
    }

    private static String unixToWindows(String path) {
        if (path.equals("~")) {
            return "C:\\User";
        }
        if (path.startsWith("~/")) {
            return "C:\\User\\" + path.substring(2).replace("/", "\\");
        }
        if (path.equals("/")) {
            return "C:\\";
        }
        if (path.startsWith("/")) {
            return "C:\\" + path.substring(1).replace("/", "\\");
        }
        return path.replace("/", "\\");
    }

    private static String windowsToUnix(String path) {
        if (path.equals("C:\\User")) {
            return "~";
        }
        if (path.startsWith("C:\\User\\")) {
            return "~/" + path.substring("C:\\User\\".length()).replace("\\", "/");
        }
        if (path.equals("C:\\")) {
            return "/";
        }
        if (path.startsWith("C:\\")) {
            return "/" + path.substring(3).replace("\\", "/");
        }
        return path.replace("\\", "/");
    }




    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS", };
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}