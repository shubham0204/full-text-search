package io.shubham0204.fulltextsearch;

import io.shubham0204.fulltextsearch.readers.DOCXReader;
import io.shubham0204.fulltextsearch.readers.PDFReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FileManager {

    private final PDFReader pdfReader = new PDFReader();
    private final DOCXReader docxReader = new DOCXReader();

    public static boolean isIndexInDir(String dirPath) {
        File indexFile = new File(dirPath, ".FST_INDEX");
        return indexFile.exists();
    }

    public List<String> getFilesFromDir(String dirPath, List<String> allowedExtensions) {
        File file = new File(dirPath);
        File[] readFiles = file.listFiles((dir, name) -> name.contains("."));
        ArrayList<String> docs = new ArrayList<>();
        if (readFiles != null) {
            for (File readFile : readFiles) {
                Optional<String> extension = getFileExtension(readFile.getName());
                if (extension.isPresent() && (allowedExtensions.contains(extension.get()))) {
                    docs.add(readFile(readFile.getAbsolutePath(), extension.get()));
                }
            }
        }
        return docs;
    }

    private String readFile(String filepath, String extension) {
        try {
            if (Objects.equals(extension, "pdf")) {
                return pdfReader.parse(filepath);
            } else if (Objects.equals(extension, "docx")) {
                return docxReader.parse(filepath);
            } else {
                return Files.readString(Paths.get(filepath));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Reference: https://www.baeldung.com/java-file-extension
    private Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

}
