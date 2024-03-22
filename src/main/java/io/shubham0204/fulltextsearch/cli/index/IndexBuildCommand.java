package io.shubham0204.fulltextsearch.cli.index;

import io.shubham0204.fulltextsearch.ConsoleLogger;
import io.shubham0204.fulltextsearch.FileManager;
import io.shubham0204.fulltextsearch.index.InvertedIndex;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@CommandLine.Command(
        name = "build",
        description = "Builds an index in the given directory"
)
public class IndexBuildCommand implements Runnable {

    @CommandLine.Parameters(index = "0")
    private String directoryPath;

    @Override
    public void run() {
        FileManager fileManager = new FileManager();
        List<String> docs = fileManager.getFilesFromDir(directoryPath, List.of("docx"));
        InvertedIndex invertedIndex = new InvertedIndex();
        ConsoleLogger.info("Started building index...");
        invertedIndex.build(docs.toArray(String[]::new));
        ConsoleLogger.info("Index built successfully");
        try {
            invertedIndex.save(Paths.get(directoryPath, ".FST_INDEX").toString());
            ConsoleLogger.info("Index saved successfully");
        } catch (IOException e) {
            ConsoleLogger.error(e.getMessage());
        }
    }

}
