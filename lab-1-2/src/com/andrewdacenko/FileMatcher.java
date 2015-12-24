package com.andrewdacenko;

import java.io.*;

public class FileMatcher {

    public static final Character LETTER = 'a';
    private final File file;
    public final String content;
    private final int matches;

    public FileMatcher(File file) {
        this.file = file;
        content = readFile();
        matches = calculateMatches(content);
    }

    @Override
    public String toString() {
        return file.getName() + ": " + matches;
    }

    private int calculateMatches(String content) {
        int matches = 0;
        String[] words = content.split(" ");
        for (String word : words) {
            if (word.length() > 0) {
                if (word.charAt(0) == LETTER) {
                    matches++;
                }
            }
        }

        return matches;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}

