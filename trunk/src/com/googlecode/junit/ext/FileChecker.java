package com.googlecode.junit.ext;

import java.io.File;

public class FileChecker implements Checker {
    private String filePath;

    public FileChecker(String filePath) {
        this.filePath = filePath;
    }

    public boolean satisfy() {
        if (filePath == null || filePath.trim().equals("")) {
            throw new RuntimeException("Please provide the filePath " + filePath);
        }
        return new File(filePath).exists();
    }
}
