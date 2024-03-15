package io.shubham0204.fulltextsearch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFileManager {

    @Test()
    @DisplayName( "test-get-files-from-dir" )
    void testGetFilesFromDir() {
        FileManager fileManager = new FileManager() ;
        List<String> docs = fileManager.getFilesFromDir("E:\\IntellijIdeaProjects\\full-text-search\\src\\test\\resources", List.of("docx", "pdf", "java"));
        for (String doc : docs) {
            System.out.println( doc ) ;
        }
        assertEquals( 2 , docs.size() ) ;
        assertEquals( "Hello World from Microsoft DOCX!" , docs.get(0) ) ;
        assertEquals( "Hello World from PDF!" , docs.get(1) ) ;
    }

}
