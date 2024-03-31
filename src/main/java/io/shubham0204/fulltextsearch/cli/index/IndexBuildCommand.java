package io.shubham0204.fulltextsearch.cli.index;

import io.shubham0204.fulltextsearch.ConsoleLogger;
import io.shubham0204.fulltextsearch.FileManager;
import io.shubham0204.fulltextsearch.index.InvertedIndex;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@CommandLine.Command(
        name = "build",
        description = "Builds an index in the given directory"
)
public class IndexBuildCommand implements Runnable {

    @CommandLine.Parameters(index = "0")
    private String directoryPath;

    @CommandLine.Option(
            names = {"--ignore", "-i"},
            description = "File extensions to be ignored while reading files from the directory" ,
            defaultValue = ""
    )
    private String ignoredExtensionsValue;

    @Override
    public void run() {
        Instant start = Instant.now();
        FileManager fileManager = new FileManager();

        List<String> ignoredExtensions = Arrays
                .stream(ignoredExtensionsValue.split(","))
                .map(extension -> extension.trim().toLowerCase(Locale.getDefault()))
                .toList();

        List<String> docs = fileManager.readFilesFromDir(directoryPath, ignoredExtensions);
        InvertedIndex invertedIndex = new InvertedIndex();
        ConsoleLogger.info("Started building index...");
        invertedIndex.build(docs.toArray(String[]::new));
        ConsoleLogger.info("Index built successfully");
        try {
            invertedIndex.save(Paths.get(directoryPath, ".FST_INDEX").toString());
            ConsoleLogger.success("Index saved successfully");
            Instant end = Instant.now();
            ConsoleLogger.info("Index built in " + Duration.between(start, end).toMillis() + " ms");
        } catch (IOException e) {
            ConsoleLogger.error(e.getMessage());
        }
    }

}
