package io.shubham0204.fulltextsearch.cli.index;

import picocli.CommandLine;

@CommandLine.Command(
        name = "index",
        description = "Performs index operations",
        helpCommand = true,
        subcommands = {
                IndexBuildCommand.class,
                IndexDeleteCommand.class,
                IndexInfoCommand.class
        }
)
public class IndexCommand implements Runnable {

    @Override
    public void run() {
        CommandLine.usage(new IndexCommand(), System.out);
    }

}