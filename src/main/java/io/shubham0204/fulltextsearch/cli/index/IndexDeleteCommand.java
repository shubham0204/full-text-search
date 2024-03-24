package io.shubham0204.fulltextsearch.cli.index;

import io.shubham0204.fulltextsearch.ConsoleLogger;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@CommandLine.Command(
        name = "rm",
        description = "Deletes the index in the given directory"
)
public class IndexDeleteCommand implements Runnable {

    @CommandLine.Parameters(index = "0")
    private String directoryPath;

    @Override
    public void run() {
        try {
            Files.delete(Paths.get(directoryPath, ".FST_INDEX"));
            ConsoleLogger.success( "Index deleted successfully" );
        }
        catch (IOException e) {
            ConsoleLogger.error(e.getMessage());
        }
    }

}
