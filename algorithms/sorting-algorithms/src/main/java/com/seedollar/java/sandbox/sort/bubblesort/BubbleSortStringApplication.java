package com.seedollar.java.sandbox.sort.bubblesort;

public class BubbleSortStringApplication {

    public static void main(String[] args) {
        Character[] characters = new Character[] {'f', 'G', 'S', 'B', 'd', 'W', 'a'};

        int size = characters.length;

        for (int x=0; x<size; x++) {
            for (int y=0; y<size-1; y++) {
                int z = y + 1;
                if (characters[y] > characters[z]) {
                    char temp = characters[y];
                    characters[y] = characters[z];
                    characters[z] = temp;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (char ch : characters) {
            builder.append(ch).append(",");
        }

        System.out.println(builder.toString());
    }
}
