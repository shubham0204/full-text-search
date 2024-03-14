package io.shubham0204.fulltextsearch.index;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Utils {

    public static List<String> getFilesFromDir(String dirpath, List<String> allowedExtensions) {
        File file = new File(dirpath);
        File[] readFiles = file.listFiles((dir, name) -> name.contains("."));
        ArrayList<String> docs = new ArrayList<>();
        for (File readFile : readFiles) {
            Optional<String> extension = getFileExtension(readFile.getName());
            if (extension.isPresent()) {
                if (allowedExtensions.contains(extension.get())) {
                    docs.add(readFileText(readFile.getAbsolutePath()));
                }
            }
        }
        return docs;
    }

    public static String readFileText(String filepath) {
        try {
            return Files.readString(Paths.get(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Reference: https://www.baeldung.com/java-file-extension
    public static Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static List<String> preprocessDocument(String text) {
        String cleanedText = clean(text.toLowerCase());
        return tokenize(cleanedText);
    }

    private static String clean(String text) {
        return text.replaceAll("[^A-Za-z ]+", "").strip();
    }

    private static List<String> tokenize(String text) {
        return Arrays.stream(text.split(" "))
                .map(String::trim)
                .filter(token -> !token.isEmpty())
                .toList();
    }

}
