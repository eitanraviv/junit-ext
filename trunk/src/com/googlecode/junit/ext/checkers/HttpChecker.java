package com.googlecode.junit.ext.checkers;

import java.net.URL;
import java.net.URLConnection;
import static java.lang.Integer.parseInt;


public class HttpChecker implements Checker {
    private String urlString;
    private int milli = 10 * 1000;

    public HttpChecker(String url) {
        this.urlString = url;
    }

    public HttpChecker(String[] args) {
        this.urlString = args[0];
        this.milli = parseInt(args[1]);
    }

    public boolean satisfy() {
        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(milli);
            urlConnection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
