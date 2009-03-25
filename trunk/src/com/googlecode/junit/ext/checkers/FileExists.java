package com.googlecode.junit.ext.checkers;

import java.io.File;

public class FileExists implements Checker {
    private String filePath;

    public FileExists(String filePath) {
        this.filePath = filePath;
    }

    public boolean satisfy() {
        if (filePath == null || filePath.trim().equals("")) {
            throw new RuntimeException("Please provide the filePath " + filePath);
        }
        return new File(filePath).exists();
    }
}
