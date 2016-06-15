package com.javaclasses.brainfuck;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class GroovyExecutor {

    public static void main(String[] args) {


        System.out.println("Result of executing groovy script: " +
                run("generatedOut/GroovyCode.groovy", "execute"));
    }

    public static String run(String groovyCodePath, String invokeMethodName) {

        final Logger log =
                LoggerFactory.getLogger(GroovyExecutor.class);
        final String LINE_SEPARATOR =
                System.getProperty("line.separator");

        if (log.isInfoEnabled()) {
            log.info("Execute: run()");
        }
        if (log.isInfoEnabled()) {
            log.info("Execute: run() with param: " + LINE_SEPARATOR
                    + "groovyCodePath= " + groovyCodePath + LINE_SEPARATOR
                    + "invokeMethodName= " + groovyCodePath + LINE_SEPARATOR);
        }

        String groovyScriptResult = "";

        ClassLoader parent = GroovyExecutor.class.getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);

        try {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);

            System.setOut(printStream);

            Class groovyClass = loader.parseClass(
                    new File(groovyCodePath));
            GroovyObject groovyObject = (GroovyObject)
                    groovyClass.newInstance();
            groovyObject.invokeMethod(invokeMethodName, null);

            groovyScriptResult = outputStream.toString();

            printStream.flush();

        } catch (InstantiationException e) {

            if (log.isErrorEnabled()) {
                log.error("InstantiationException:" + e.getMessage());
            }

            throw new RuntimeException(
                    new InstantiationException());

        } catch (IllegalAccessException e) {

            if (log.isErrorEnabled()) {
                log.error("IllegalAccessException:" + e.getMessage());
            }

            throw new RuntimeException(
                    new IllegalAccessException());

        } catch (IOException e) {

            if (log.isErrorEnabled()) {
                log.error("IOException:" + e.getMessage());
            }

            throw new RuntimeException(
                    new IOException());
        }
        return groovyScriptResult;
    }
}
