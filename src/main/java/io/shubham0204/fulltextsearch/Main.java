package io.shubham0204.fulltextsearch;

import io.shubham0204.fulltextsearch.index.InvertedIndex;
import picocli.CommandLine;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(
                CommandLine.Help.Ansi.AUTO.string(
                        "@|bold,red,underline " + "Someone caught!" + " |@"
                )
        );
        FileManager fileManager = new FileManager();
        List<String> docs = fileManager.getFilesFromDir("C:\\Users\\equip\\Documents\\Test_2023_2024", List.of("docx"));
        InvertedIndex invertedIndex = new InvertedIndex();
        invertedIndex.build(docs.toArray(String[]::new));
    }

}