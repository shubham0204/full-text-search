package io.shubham0204.fulltextsearch.cli.index;

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

    }

}
