package io.shubham0204.fulltextsearch.cli;

import io.shubham0204.fulltextsearch.ConsoleLogger;
import io.shubham0204.fulltextsearch.FileManager;
import io.shubham0204.fulltextsearch.index.InvertedIndex;
import picocli.CommandLine;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@CommandLine.Command(
        name = "query",
        description = "Performs full-text-search for a given query",
        helpCommand = true
)
public class QueryCommand implements Runnable {

    @CommandLine.Parameters(index = "0")
    private String directoryPath;

    @Override
    public void run() {
        if (FileManager.isIndexInDir(directoryPath)) {
            try {
                InvertedIndex invertedIndex = InvertedIndex.load(directoryPath);
                List<String> filenames = FileManager.getFileNamesFromDir(directoryPath, List.of("docx")) ;
                if( filenames.size() != invertedIndex.getInformation().numDocuments() ) {
                    ConsoleLogger.warn( "The index is outdated. " +
                            "The given directory has been modified after the index was built. " +
                            "Consider rebuilding the index.");
                }
                Scanner scanner = new Scanner( System.in ) ;
                while (true) {
                    ConsoleLogger.info( "Enter query: " );
                    String line = scanner.nextLine() ;
                    if (line.trim().isEmpty()) {
                        break;
                    }
                    Integer[] indices = invertedIndex.query( line ) ;
                    ConsoleLogger.info( "Found in documents: " );
                    Arrays.stream(indices).forEach(index -> ConsoleLogger.info( filenames.get(index) ) ) ;
                }
            }
            catch (IOException | ClassNotFoundException e) {
                ConsoleLogger.error(e.getMessage());
            }
        }
        else {
            ConsoleLogger.error("No index found in the given directory");
        }

    }

}
