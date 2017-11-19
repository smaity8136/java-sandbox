package com.seedollar.java.sandbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by seedollar on 11/18/17.
 */
public class CSVFileReadApplication {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
//        int counter = 0;
//        String line = null;
//        while ((line = reader.readLine()) != null) {
//            if (counter > 1)
//                System.out.println(line);
//            counter++;
//        }

        Double total = reader.lines().skip(1).map(ln -> Double.valueOf(ln.split(",")[17])).reduce(0d, (a, b) -> a + b);
        System.out.println("total = " + total);
    }
}
