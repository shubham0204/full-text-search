package io.shubham0204.fulltextsearch.cli.index;

import io.shubham0204.fulltextsearch.ConsoleLogger;
import io.shubham0204.fulltextsearch.FileManager;
import io.shubham0204.fulltextsearch.index.InvertedIndex;
import picocli.CommandLine;

@CommandLine.Command(
    name = "info",
    description = "Provides information about the index present in the given directory"
)
public class IndexInfoCommand implements Runnable {

    @CommandLine.Parameters(index = "0")
    private String directoryPath;

    @Override
    public void run() {
        if(FileManager.isIndexInDir(directoryPath)) {
            try {
                InvertedIndex invertedIndex = InvertedIndex.load( directoryPath ) ;
                InvertedIndex.IndexInformation indexInformation = invertedIndex.getInformation() ;
                ConsoleLogger.info( indexInformation.toString() );
            }
            catch (Exception e) {
                ConsoleLogger.error(e.getMessage());
            }
        }
        else {
            ConsoleLogger.error("No index found in the given directory");
        }
    }

}
