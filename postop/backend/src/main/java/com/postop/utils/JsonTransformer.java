package com.postop.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Response;
import spark.ResponseTransformer;

import java.util.HashMap;

/**
 * This class transforms an Object into JSON
 * @author Geet Sawhney, Rohit Aakash
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    /**
     * @param model -object to be transformed
     * @return json as a string
     */
    @Override
    public String render(Object model) {
        if (model instanceof Response) {
            return gson.toJson(new HashMap<>());
        }
        return gson.toJson(model);
    }
}