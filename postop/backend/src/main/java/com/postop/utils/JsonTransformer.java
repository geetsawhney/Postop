package com.postop.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Response;
import spark.ResponseTransformer;

import java.util.HashMap;

public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    @Override
    public String render(Object model) {
        if (model instanceof Response) {
            return gson.toJson(new HashMap<>());
        }
        return gson.toJson(model);
    }
}