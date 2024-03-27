package io.shubham0204.fulltextsearch.cli;

import io.shubham0204.fulltextsearch.cli.index.IndexCommand;
import picocli.CommandLine;

@CommandLine.Command(
        name = "fulltextsearch",
        description = "Performs a full-text-search on all readable files in the given directory",
        subcommands = {
                QueryCommand.class,
                IndexCommand.class
        }
)
public class FullTextSearchCommand implements Runnable {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new FullTextSearchCommand()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        CommandLine.usage(new FullTextSearchCommand(), System.out);
    }

}