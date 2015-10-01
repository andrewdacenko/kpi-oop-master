package com.andrewdacenko;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        int amountOfThreads = 10;
        ExecutorService threadPool = Executors.newFixedThreadPool(amountOfThreads);
        ExecutorCompletionService<Long> tasks = new ExecutorCompletionService<Long>(threadPool);

        String path = "./files";
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles)
        {
            if (file.isFile())
            {
                tasks.submit(new Callable() {
                    @Override
                    public Object call() throws Exception {
                        FileMatcher fm = new FileMatcher(file);
                        return fm;
                    }
                });
            }
        }

        FileWriter out = null;

        try {
            out = new FileWriter("success.txt");
            for (File file : listOfFiles) {
                Future<Long> task = tasks.take();

                out.write(task.get() + " matches" + System.getProperty("line.separator"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        threadPool.shutdown();
    }
}
