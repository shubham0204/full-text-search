package io.shubham0204.fulltextsearch;

import io.shubham0204.fulltextsearch.index.InvertedIndex;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager() ;
        List<String> docs = fileManager.getFilesFromDir("E:\\IntellijIdeaProjects\\full-text-search\\src\\test\\resources", List.of("docx", "pdf", "java"));
        InvertedIndex invertedIndex = new InvertedIndex() ;
        for (String doc : docs) {
            System.out.println( doc ) ;
        }
        invertedIndex.build( docs.toArray( String[]::new ) );
        System.out.println(Arrays.toString( invertedIndex.query( "pdf" ) ) ) ;
    }
}