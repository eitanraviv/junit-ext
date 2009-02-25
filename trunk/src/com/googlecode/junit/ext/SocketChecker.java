package com.googlecode.junit.ext;

import java.net.Socket;
import java.io.IOException;

public class SocketChecker implements Checker {
    private String host;
    private Integer port;

    public SocketChecker(String[] args) {
        this.host = args[0];
        this.port = Integer.valueOf(args[0]);
    }

    public boolean satisfy() {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
