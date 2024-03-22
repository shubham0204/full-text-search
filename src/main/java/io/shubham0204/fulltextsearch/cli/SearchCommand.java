package io.shubham0204.fulltextsearch.cli;

import io.shubham0204.fulltextsearch.FileManager;
import io.shubham0204.fulltextsearch.index.InvertedIndex;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(
        name = "search",
        description = "Performs full-text-search for a given query"
)
public class SearchCommand implements Runnable {

    @CommandLine.Parameters(index = "0")
    private String directoryPath;


    @Override
    public void run() {
        if (!FileManager.isIndexInDir(directoryPath)) {
            InvertedIndex invertedIndex = null;
            try {
                invertedIndex = InvertedIndex.load(directoryPath);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("");
        }

    }

}
