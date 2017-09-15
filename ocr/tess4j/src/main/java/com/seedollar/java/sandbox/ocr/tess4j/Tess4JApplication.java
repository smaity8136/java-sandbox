package com.seedollar.java.sandbox.ocr.tess4j;

import com.seedollar.java.sandbox.ocr.tess4j.configuration.Tess4JConfiguration;
import com.seedollar.java.sandbox.ocr.tess4j.scanner.ImageScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Paths;

@SpringBootApplication
public class Tess4JApplication implements ApplicationRunner {

    @Autowired
    private ImageScanner scanner;

    public static void main(String[] args) {
        SpringApplication.run(new Object[] {Tess4JConfiguration.class, Tess4JApplication.class}, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String scanned = scanner.scan(Paths.get(args.getSourceArgs()[0]).toFile());
        System.out.println("scanned = " + scanned);
    }
}
