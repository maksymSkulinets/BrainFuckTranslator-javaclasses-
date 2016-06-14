package com.javaclasses.brainfuck;

import java.util.HashMap;
import java.util.Map;

public class TemplatePathHolder {

    /* TODO change class design */

    final Map<String, String> templateLocations
            = new HashMap<>();

    public TemplatePathHolder() {
        templateLocations.put("java","resources/javaTemplate.txt");
        templateLocations.put("javascript","resources/javascriptTemplate.txt");
    }

    public String getPath(String key) {
       return templateLocations.get(key);
    }

    public void addPath(String key, String path) {
         templateLocations.put(key,path);
    }

}
