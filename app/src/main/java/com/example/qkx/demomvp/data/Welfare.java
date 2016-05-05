package com.example.qkx.demomvp.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkx on 16/4/28.
 */
public class Welfare {
    public List<Result> results;

    public Welfare() {
        results = new ArrayList<>();
    }

    public class Result {
        public String url;
        public String who;

        public Result(String url, String who) {
            this.url = url;
            this.who = who;
        }
    }

    public void addResult(String url, String who) {
        results.add(new Result(url, who));
    }

    public List<String> getUrls() {
        List<String> list = new ArrayList<>();
        for (Result result : results) {
            list.add(result.url);
        }
        return list;
    }

}
