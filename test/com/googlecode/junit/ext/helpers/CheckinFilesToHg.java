package com.googlecode.junit.ext.helpers;

import com.googlecode.junit.ext.Precondition;

import java.util.Map;

public class CheckinFilesToHg implements Precondition {
    private Map obj;
    private String dir;

    public CheckinFilesToHg(Object obj) {
        this.obj = (Map) obj;
    }

    public void setup() {
        dir = "tmp/workingfolder";
        obj.put("WKDIR", dir);
        System.out.println("checkout the from " + obj.get("URL") + " to workingdir" + dir);
    }

    public void teardown() {
        System.out.println("removing the workingcopy" + dir);
    }
}
