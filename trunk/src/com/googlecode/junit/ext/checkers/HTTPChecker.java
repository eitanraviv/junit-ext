package com.googlecode.junit.ext.checkers;

import com.googlecode.junit.ext.checkers.Checker;
import com.googlecode.junit.ext.checkers.SocketChecker;

public class HTTPChecker implements Checker {
    private SocketChecker socketChecker;


    public HTTPChecker(String url) {
        String host = url;
        String port = "80";
        socketChecker = new SocketChecker(new String[]{host, port});
    }

    public HTTPChecker(String[] args) {
        String host = args[0];
        String port = null;
        if (args.length == 1) {
            port = "80";
        } else {
            port = args[1];
        }
        socketChecker = new SocketChecker(new String[]{host, port});
    }

    public boolean satisfy() {
        return socketChecker.satisfy();
    }
}
