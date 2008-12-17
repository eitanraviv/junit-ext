package com.googlecode.junit.ext;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class HTTPChecker implements Checker {
    private String url;

    public HTTPChecker(String url) {
        this.url = url;
    }

    public boolean satisfy() {
        try {
            HttpClient httpClient = new HttpClient();
            GetMethod get = new GetMethod(url);
            httpClient.executeMethod(get);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
