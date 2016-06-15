package com.javaclasses.brainfuck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TemplatePathHolder {

    final static Logger log =
            LoggerFactory.getLogger(TemplatePathHolder.class);

    final Map<String, String> templateLocations
            = new HashMap<>();

    public TemplatePathHolder() {

        if (log.isDebugEnabled()) {
            log.debug("Initialize.");
        }
        if (log.isInfoEnabled()) {
            log.info("Initialize.");
        }

        templateLocations.put("java","resources/javaTemplate.txt");
        templateLocations.put("javascript","resources/javascriptTemplate.txt");
        templateLocations.put("groovy","resources/groovyTemplate.txt");

    }

    public String getPath(String key) {
        if (log.isDebugEnabled()) {
            log.debug("execute: getPath(): return : " +
                    "" + templateLocations.get(key) + "/");
        }
       return templateLocations.get(key);
    }

    public void addPath(String key, String path) {

        templateLocations.put(key,path);

        if (log.isDebugEnabled()) {
            log.debug("execute: addPath():" +
                    " add:" +
                    " key = \"" + key + "\" path = "  + path);
        }
    }

}
