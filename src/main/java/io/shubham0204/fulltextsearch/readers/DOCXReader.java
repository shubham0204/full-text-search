package io.shubham0204.fulltextsearch.readers;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class DOCXReader extends Reader {

    @Override
    public String parse(String filepath) {
        File docxFile = new File( filepath ) ;
        try {
            FileInputStream fileInputStream = new FileInputStream( docxFile ) ;
            XWPFDocument document = new XWPFDocument( fileInputStream ) ;
            List<XWPFParagraph> paragraphs = document.getParagraphs() ;
            StringBuilder text = new StringBuilder();
            for ( XWPFParagraph paragraph : paragraphs ) {
                text.append(" ").append(paragraph.getText());
            }
            return text.toString().strip();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
