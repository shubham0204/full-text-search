package io.shubham0204.fulltextsearch;

import io.shubham0204.fulltextsearch.index.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> docs = Utils.getFilesFromDir(".", List.of("docx", "pdf", ".java"));
        for (String doc : docs) {
            System.out.println(doc);
        }
    }
}