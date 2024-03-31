package io.shubham0204.fulltextsearch;

import io.shubham0204.fulltextsearch.index.InvertedIndex;
import picocli.CommandLine;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        List<String> docs = fileManager.readFilesFromDir("C:\\Users\\equip\\Documents\\Test_2023_2024", List.of("docx"));
        InvertedIndex invertedIndex = new InvertedIndex();
        invertedIndex.build(docs.toArray(String[]::new));
        while( true ) {
        }
    }

}