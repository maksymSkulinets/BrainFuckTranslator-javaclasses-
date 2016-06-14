package com.javaclasses.brainfuck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileOperationManager {

    final static Logger log = LoggerFactory.getLogger
            (FileOperationManager.class);

    public FileOperationManager() {
    }

    public static void writeToFile(String filePath, String content) {

        if (log.isInfoEnabled()) {
            log.info("Execute: execute((String templatePath, String modified, String modifiedPath))");
        }
        if (log.isDebugEnabled()) {
            log.debug("Enter: execute((String templatePath, String modified, String modifiedPath))");
        }
        FileWriter writer = null;

        try {

            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter buffer = new BufferedWriter(
                    new FileWriter(file.getAbsoluteFile()));
            buffer.write(content);
            buffer.close();

        } catch (IOException e) {

            if (log.isErrorEnabled()) {
                log.error("Throw IllegalStateException." +
                        "Cause: " +
                        "Exception in TemplateModifier logic.");

            }

            throw new RuntimeException(new IOException());
        }

        if (log.isDebugEnabled()) {
            log.debug("Exit execute((String templatePath, String modified, String modifiedPath))");
        }

    }

    public static String readFromFile(String filePath) {

        StringBuffer result = new StringBuffer();

        try (BufferedReader buffer = new BufferedReader(new FileReader(filePath))) {

            String currentLine;
            while ((currentLine = buffer.readLine()) != null) {
                result.append(currentLine);
            }

        } catch (IOException e) {
            throw new RuntimeException();
        }
        return result.toString();
    }
}
