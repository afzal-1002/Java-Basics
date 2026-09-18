package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Words {

    // Matches runs of letters (any language) AND runs of digits (e.g. years like "1812")
    private static final Pattern WORD_PATTERN = Pattern.compile("[\\p{L}\\p{N}]+");

    public String countWords(List<String> lines) {

        Map<String, Integer> wordCounts = new HashMap<String, Integer>();

        for (String line : lines) {
            Matcher matcher = WORD_PATTERN.matcher(line);
            while (matcher.find()) {
                String word = matcher.group().toLowerCase();
                Integer currentCount = wordCounts.get(word);
                if (currentCount == null) {
                    wordCounts.put(word, 1);
                } else {
                    wordCounts.put(word, currentCount + 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> filteredEntries = new ArrayList<Map.Entry<String, Integer>>();
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            if (word.length() >= 4 && count >= 10) {
                filteredEntries.add(entry);
            }
        }

        Collections.sort(filteredEntries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> first, Map.Entry<String, Integer> second) {
                int countComparison = second.getValue().compareTo(first.getValue());
                if (countComparison != 0) {
                    return countComparison;
                }
                return first.getKey().compareTo(second.getKey());
            }
        });

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < filteredEntries.size(); i++) {
            Map.Entry<String, Integer> entry = filteredEntries.get(i);
            if (i > 0) {
                result.append("\n");
            }
            result.append(entry.getKey()).append(" - ").append(entry.getValue());
        }

        return result.toString();
    }
}