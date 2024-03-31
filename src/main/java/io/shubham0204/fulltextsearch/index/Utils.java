package io.shubham0204.fulltextsearch.index;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public static List<String> preprocessDocument(String text) {
        String cleanedText = clean(text.toLowerCase());
        return StopWords.removeStopWords( tokenize(cleanedText) );
    }

    private static String clean(String text) {
        return text.replaceAll("[^A-Za-z ]+", "").strip();
    }

    public static List<String> tokenize(String text) {
        return Arrays.stream(text.split(" "))
                .map(String::trim)
                .toList();
    }

}
