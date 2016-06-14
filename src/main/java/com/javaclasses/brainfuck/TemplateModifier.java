package com.javaclasses.brainfuck;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TemplateModifier {

    final static Logger log = LoggerFactory.getLogger((TemplateModifier.class));

    private Configuration configuration;
    private Template template;

    public TemplateModifier() {
        configuration = new Configuration();

        if (log.isDebugEnabled()) {
            log.debug("Initialize.");
        }
    }

    public void execute(String templatePath, String modified, String modifiedPath) {

        if (log.isInfoEnabled()) {
            log.info("Execute: execute((String templatePath, String modified, String modifiedPath))");
        }
        if (log.isDebugEnabled()) {
            log.debug("Enter: execute((String templatePath, String modified, String modifiedPath))");
        }

        try {

            template = configuration.getTemplate(templatePath);
            Map<String, String> data = new HashMap<>();
            data.put("model", modified);
            Writer fileWriter = new FileWriter(new File(modifiedPath));
            template.process(data, fileWriter);

        } catch (Exception e) {

            if (log.isErrorEnabled()) {
                log.error("Throw IllegalStateException." +
                        "Cause: " +
                        "Exception in TemplateModifier logic.");

            }

            throw new RuntimeException(new Exception());
        }
        if (log.isDebugEnabled()) {
            log.debug("Exit: execute((String templatePath, String modified, String modifiedPath))");
        }

    }

}
