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

        assert listOfFiles != null;
        for (File file : listOfFiles)
        {
            if (file.isFile())
            {
                tasks.submit(new FileCallable(file));
            }
        }

        FileWriter out = null;

        try {
            out = new FileWriter("success.txt");
            for (File file : listOfFiles) {
                Future<Long> task = tasks.take();

                String result = task.get() + " matches";
                out.write(result + System.getProperty("line.separator"));
                System.out.println(result);
            }
        } catch (InterruptedException | ExecutionException | IOException e) {
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
