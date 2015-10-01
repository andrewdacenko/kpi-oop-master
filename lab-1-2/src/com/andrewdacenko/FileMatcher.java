package com.andrewdacenko;

import java.io.*;

public class FileMatcher {

    private final File file;

    String content;

    public FileMatcher(File file) {
        this.file = file;
        content = readFile();
    }

    private String readFile() {
        try {

            final BufferedReader reader = new BufferedReader(new FileReader(file));
            final StringBuilder contents = new StringBuilder();
            while (reader.ready()) {
                contents.append(reader.readLine());
            }
            reader.close();
            return contents.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}

