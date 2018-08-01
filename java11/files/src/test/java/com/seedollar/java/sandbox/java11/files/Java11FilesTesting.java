package com.seedollar.java.sandbox.java11.files;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Java11FilesTesting {
    String fileString = "This is a java 11 file string example";

    @Test
    public void testWriteString() throws IOException {
        Files.writeString(Path.of("/tmp", "java11writeString.txt"), fileString);
    }

    @Test
    public void testReadString() throws IOException {
        testWriteString();
        String readString = Files.readString(Path.of("/tmp", "java11writeString.txt"));
        Assertions.assertEquals(readString, fileString);
    }

    @Test
    public void testNullReader() throws IOException {
        Reader reader = Reader.nullReader();
        Assertions.assertEquals(-1, reader.read());
    }

    @Test
    public void testNullInputStream() throws IOException {
        InputStream inputStream = InputStream.nullInputStream();
        Assertions.assertEquals(-1, inputStream.read());
    }
}
