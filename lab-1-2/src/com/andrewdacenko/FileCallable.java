package com.andrewdacenko;

import java.io.File;
import java.util.concurrent.Callable;

public class FileCallable implements Callable {

    private File file;

    public FileCallable(File runFile) {
        file = runFile;
    }

    @Override
    public Object call() throws Exception {
        return new FileMatcher(file);
    }
}