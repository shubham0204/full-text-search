package io.shubham0204.fulltextsearch;

import io.shubham0204.fulltextsearch.index.InvertedIndex;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager() ;
        List<String> docs = fileManager.getFilesFromDir("C:\\Users\\equip\\Documents\\Test_2023_2024", List.of("docx"));
        InvertedIndex invertedIndex = new InvertedIndex() ;
        for (String doc : docs) {
            System.out.println( doc ) ;
        }
        invertedIndex.build( docs.toArray( String[]::new ) );
    }
}