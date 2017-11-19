package com.seedollar.java.sandbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by seedollar on 11/18/17.
 */
public class FilesReadAllBytesApplication {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        br.lines().map(String::toUpperCase).forEach(System.out::println);
//        byte[] bytes = Files.readAllBytes(Paths.get(args[0]));
//        System.out.println("bytes = " + new String(bytes));
    }
}
