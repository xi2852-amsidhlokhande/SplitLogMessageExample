package com.amsidh.mvc.splitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MarkerIgnoringBase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.function.Consumer;

public class MessageSplittingLogger extends MarkerIgnoringBase {
    //Target size is 64k for split.  UTF-8 nominally has 1 byte characters, but some characters will use > 1 byte so leave some wiggle room
    //Also leave room for additional messages
    private static final int MAX_CHARS_BEFORE_SPLIT = 1024;
    private static final String ENCODING = "UTF-8";
    private Logger LOGGER;

    public MessageSplittingLogger(Class<?> clazz) {
        this.LOGGER = LoggerFactory.getLogger(clazz);
    }

    public Logger getLogger(Class<?> clazz){
        this.LOGGER = LoggerFactory.getLogger(clazz);
        return this.LOGGER;
    }


    private void splitMessageAndLog(String msg, Throwable t, Consumer<String> logLambda) {
        String combinedMsg = msg + (t != null ? "\nStack Trace:\n" + printStackTraceToString(t) : "");

        int totalMessages = combinedMsg.length() / MAX_CHARS_BEFORE_SPLIT;
        if (combinedMsg.length() % MAX_CHARS_BEFORE_SPLIT > 0) {
            totalMessages++;
        }

        int index = 0;
        int msgNumber = 1;
        while (index < combinedMsg.length()) {
            String messageNumber = totalMessages > 1 ? "(" + msgNumber++ + " of " + totalMessages + ")\n" : "";
            logLambda.accept(messageNumber + combinedMsg.substring(index, Math.min(index + MAX_CHARS_BEFORE_SPLIT, combinedMsg.length())));
            index += MAX_CHARS_BEFORE_SPLIT;
        }
    }

    /**
     * Get the stack trace as a String
     */
    private String printStackTraceToString(Throwable t) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos, true, ENCODING);
            t.printStackTrace(ps);

            return baos.toString(ENCODING);
        } catch (UnsupportedEncodingException e) {
            return "Exception printing stack trace: " + e.getMessage();
        }
    }

    @Override
    public String getName() {
        return LOGGER.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return LOGGER.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        LOGGER.trace(msg);
    }

    @Override
    public void trace(String format, Object arg) {
        LOGGER.trace(format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        LOGGER.trace(format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        LOGGER.trace(format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t) {
        splitMessageAndLog(msg, t, LOGGER::trace);
    }

    @Override
    public boolean isDebugEnabled() {
        return LOGGER.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        LOGGER.debug(msg);
    }

    @Override
    public void debug(String format, Object arg) {
        LOGGER.debug(format, arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        LOGGER.debug(format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        LOGGER.debug(format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t) {
        LOGGER.debug(msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return LOGGER.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        if (msg.length() < 1024) {
            LOGGER.info(msg);
        } else {
            splitMessageAndLog(msg, null, LOGGER::info);
        }
    }

    @Override
    public void info(String format, Object arg) {
        LOGGER.info(format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        LOGGER.info(format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
        LOGGER.info(format, arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
        splitMessageAndLog(msg, t, LOGGER::info);
    }

    @Override
    public boolean isWarnEnabled() {
        return LOGGER.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        LOGGER.warn(msg);
    }

    @Override
    public void warn(String format, Object arg) {
        LOGGER.warn(format, arg);
    }

    @Override
    public void warn(String format, Object... arguments) {
        LOGGER.warn(format, arguments);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        LOGGER.warn(format, arg1, arg2);
    }

    @Override
    public void warn(String msg, Throwable t) {
        LOGGER.warn(msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return LOGGER.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        LOGGER.error(msg);
    }

    @Override
    public void error(String format, Object arg) {
        LOGGER.error(format, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        LOGGER.error(format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        LOGGER.error(format, arguments);
    }

    @Override
    public void error(String msg, Throwable t) {
        LOGGER.error(msg, t);
    }


}
