package io.shubham0204.fulltextsearch.readers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFReader extends Reader {

    @Override
    public String parse(String filepath) {
        File pdfFile = new File(filepath);
        try (PDDocument pdfDoc = PDDocument.load(pdfFile)) {
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            return pdfTextStripper.getText(pdfDoc).strip();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
