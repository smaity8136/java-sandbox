package com.seedollar.java.sandbox.ocr.tess4j.configuration;

import com.google.common.collect.Lists;
import com.seedollar.java.sandbox.ocr.tess4j.scanner.ImageScanner;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class Tess4JConfiguration {

    @Bean
    public ITesseract iTesseract() {
        ITesseract tesseract = new Tesseract();
        Path configPath = Paths.get("src/main/resources/");
        String path = configPath.toUri().getPath();
        tesseract.setConfigs(Lists.newArrayList(path));
        tesseract.setDatapath(path);
        return tesseract;
    }

    @Bean
    public ImageScanner imageScanner() {
        return new ImageScanner(iTesseract());
    }

}
