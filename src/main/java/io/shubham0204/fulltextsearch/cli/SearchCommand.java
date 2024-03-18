package io.shubham0204.fulltextsearch.cli;

import io.shubham0204.fulltextsearch.FileManager;
import io.shubham0204.fulltextsearch.index.InvertedIndex;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
    name = "search",
    description = "Performs full-text-search for a given query"
)
public class SearchCommand implements Runnable {

    @CommandLine.Parameters( index = "0" )
    private String directoryPath ;

    @Override
    public void run() {
        FileManager fileManager = new FileManager() ;
        List<String> docs = fileManager.getFilesFromDir("C:\\Users\\equip\\Documents\\Test_2023_2024", List.of("docx"));
        InvertedIndex invertedIndex = new InvertedIndex() ;
        for (String doc : docs) {
            System.out.println( doc ) ;
        }
        invertedIndex.build( docs.toArray( String[]::new ) );
    }

}
