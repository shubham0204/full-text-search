package io.shubham0204.fulltextsearch;

import io.shubham0204.fulltextsearch.readers.DOCXReader;
import io.shubham0204.fulltextsearch.readers.PDFReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileManager {

    private final PDFReader pdfReader = new PDFReader();
    private final DOCXReader docxReader = new DOCXReader();

    public static boolean isIndexInDir(String dirPath) {
        File indexFile = new File(dirPath, ".FST_INDEX");
        return indexFile.exists();
    }

    public List<String> readFilesFromDir(String dirPath, List<String> ignoredExtensions) {
        File dirFile = new File(dirPath);
        File[] readFiles = dirFile.listFiles((dir, name) -> name.contains("."));
        ArrayList<String> docs = new ArrayList<>();
        if (readFiles != null) {
            Arrays.sort( readFiles );
            for (File file : readFiles) {
                Optional<String> extension = getFileExtension(file.getName());
                if (extension.isPresent() && (!ignoredExtensions.contains(extension.get()))) {
                    Optional<String> readFileResult = readFile(file.getAbsolutePath(), extension.get()) ;
                    readFileResult.ifPresent(docs::add);
                }
            }
        }
        return docs;
    }

    public static List<String> getFileNamesFromDir(String dirPath, List<String> allowedExtensions) {
        File dirFile = new File(dirPath);
        File[] readFiles = dirFile.listFiles((dir, name) -> name.contains("."));
        ArrayList<String> docs = new ArrayList<>();
        if (readFiles != null) {
            Arrays.sort( readFiles );
            for (File file : readFiles) {
                Optional<String> extension = getFileExtension(file.getName());
                if (extension.isPresent() && (allowedExtensions.contains(extension.get()))) {
                    docs.add( file.getName() );
                }
            }
        }
        return docs;
    }

    private Optional<String> readFile(String filepath, String extension) {
        try {
            if (Objects.equals(extension, "pdf")) {
                return Optional.of( pdfReader.parse(filepath) );
            } else if (Objects.equals(extension, "docx")) {
                return Optional.of( docxReader.parse(filepath) );
            } else {
                return Optional.of( Files.readString(Paths.get(filepath)) );
            }
        } catch (IOException e) {
            ConsoleLogger.warn( "Could not read file: " + filepath );
            return Optional.empty() ;
        }
    }

    // Reference: https://www.baeldung.com/java-file-extension
    private static Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

}
