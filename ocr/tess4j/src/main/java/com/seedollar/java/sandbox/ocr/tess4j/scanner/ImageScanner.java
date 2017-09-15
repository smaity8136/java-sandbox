package com.seedollar.java.sandbox.ocr.tess4j.scanner;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class ImageScanner {

    private ITesseract iTesseract;

    public ImageScanner(ITesseract iTesseract) {
        this.iTesseract = iTesseract;
    }

    public String scan(File targetFile) throws TesseractException {
        return iTesseract.doOCR(targetFile);
    }
}
