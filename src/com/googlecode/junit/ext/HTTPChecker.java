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
            int i = httpClient.executeMethod(get);
            System.out.println("return true" + get.getResponseBodyAsString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("return false");
            return false;
        }
    }
}
