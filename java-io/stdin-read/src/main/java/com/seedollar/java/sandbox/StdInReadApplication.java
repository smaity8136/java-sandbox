package com.seedollar.java.sandbox;

import java.util.Scanner;

/**
 * Created by seedollar on 11/18/17.
 */
public class StdInReadApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        String name = scanner.next();
        double amount = scanner.nextDouble();

        System.out.println("ID = " + id);
        System.out.println("name = " + name);
        System.out.println("amount = " + amount);
    }
}
