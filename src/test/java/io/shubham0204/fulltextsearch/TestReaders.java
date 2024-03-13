package io.shubham0204.fulltextsearch;

import io.shubham0204.fulltextsearch.readers.DOCXReader;
import io.shubham0204.fulltextsearch.readers.PDFReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestReaders {

    @Test()
    @DisplayName( "test-pdf-reader" )
    void testPDFReader() {
        PDFReader reader = new PDFReader() ;
        String text = reader.parse( "src/test/resources/test-pdf-doc.pdf" ) ;
        assertEquals("Hello World from PDF!" , text ) ;
    }

    @Test()
    @DisplayName( "test-docx-reader" )
    void testDOCXReader() {
        DOCXReader reader = new DOCXReader() ;
        String text = reader.parse( "src/test/resources/test-docx-doc.docx" ) ;
        assertEquals("Hello World from Microsoft DOCX!" , text ) ;
    }

}
