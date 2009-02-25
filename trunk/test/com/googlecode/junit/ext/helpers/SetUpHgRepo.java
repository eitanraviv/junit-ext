package com.googlecode.junit.ext.helpers;

import com.googlecode.junit.ext.Precondition;

import java.util.Map;

public class SetUpHgRepo implements Precondition {
    private Map obj;
    private String url;

    public SetUpHgRepo(Object obj) {
        System.out.println(obj.hashCode());
    }

    public void setup() {
        this.url = "http://localhost:8080/hg/url";
        obj.put("URL", url);
        System.out.println("Set Up Hg Repo at " + url);
    }

    public void teardown() {
        System.out.println("Teardown Hg Repo at " + url);
    }
}
