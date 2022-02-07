package com.amsidh.mvc.chunk;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggerContextVO;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.spi.AppenderAttachable;
import ch.qos.logback.core.spi.AppenderAttachableImpl;
import ch.qos.logback.core.spi.LifeCycle;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.slf4j.Marker;
import org.slf4j.helpers.MessageFormatter;

import java.util.Iterator;
import java.util.Map;

public class LogChunkAppender extends UnsynchronizedAppenderBase<LoggingEvent> implements AppenderAttachable<LoggingEvent> {

    private static final int DEFAULT_CHUNK_LENGTH = 1024;
    private static final String DEFAULT_CHUNK_SEPARATOR = "\0";
    private static final boolean DEFAULT_CHUNK_LONG_WORDS = true;
    private static final String DEFAULT_CHUNK_ON = "\\n";
    private static final String DEFAULT_CHUNK_FORMAT = "[LogChunkEvent {} {}/{}] {}";

    @Getter
    @Setter
    private int chunkLength = DEFAULT_CHUNK_LENGTH;
    @Getter
    @Setter
    private String chunkSeparator = DEFAULT_CHUNK_SEPARATOR;
    @Getter
    @Setter
    private boolean chunkLongWords = DEFAULT_CHUNK_LONG_WORDS;
    @Getter
    @Setter
    private String chunkOn = DEFAULT_CHUNK_ON;
    @Getter
    @Setter
    private String chunkFormat = DEFAULT_CHUNK_FORMAT;

    private final AppenderAttachableImpl<LoggingEvent> appenders = new AppenderAttachableImpl<>();

    @Override
    public void start() {
        appenders.iteratorForAppenders().forEachRemaining(LifeCycle::start);
        super.start();
    }

    @Override
    public void stop() {
        appenders.iteratorForAppenders().forEachRemaining(LifeCycle::stop);
        super.stop();
    }

    @Override
    public boolean isStarted() {
        return super.isStarted();
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        String formattedMessage = loggingEvent.getFormattedMessage();

        if (StringUtils.length(formattedMessage) > chunkLength) {
            String chunkId = Integer.toHexString(loggingEvent.hashCode());
            String chunkedMessage = WordUtils.wrap(formattedMessage, chunkLength, chunkSeparator, chunkLongWords, chunkOn);
            String[] chunkMessages = StringUtils.split(chunkedMessage, chunkSeparator);
            int totalChunks = chunkMessages.length;

            for (int chunkIdx = 0; chunkIdx < totalChunks; chunkIdx++) {
                String chunkMessage = chunkMessages[chunkIdx];
                int currentChunk = chunkIdx + 1;

                appenders.appendLoopOnAppenders(new LogChunkEvent(loggingEvent, MessageFormatter.arrayFormat(chunkFormat,
                        ArrayUtils.toArray(chunkId, currentChunk, totalChunks, chunkMessage))
                        .getMessage()));
            }
        } else {
            appenders.appendLoopOnAppenders(loggingEvent);
        }
    }

    @Override
    public void addAppender(Appender<LoggingEvent> newAppender) {
        addInfo("Attaching appender named [" + newAppender.getName() + "] to LogChunkAppender.");
        appenders.addAppender(newAppender);
    }

    @Override
    public Iterator<Appender<LoggingEvent>> iteratorForAppenders() {
        return appenders.iteratorForAppenders();
    }

    @Override
    public Appender<LoggingEvent> getAppender(String name) {
        return appenders.getAppender(name);
    }

    @Override
    public boolean isAttached(Appender<LoggingEvent> appender) {
        return appenders.isAttached(appender);
    }

    @Override
    public void detachAndStopAllAppenders() {
        appenders.detachAndStopAllAppenders();
    }

    @Override
    public boolean detachAppender(Appender<LoggingEvent> appender) {
        return appenders.detachAppender(appender);
    }

    @Override
    public boolean detachAppender(String name) {
        return appenders.detachAppender(name);
    }

    private static class LogChunkEvent extends LoggingEvent {
        private final LoggingEvent loggingEvent;
        private final String chunkMessage;

        private LogChunkEvent(LoggingEvent loggingEvent, String chunkMessage) {
            this.loggingEvent = loggingEvent;
            this.chunkMessage = chunkMessage;
        }

        @Override
        public void setArgumentArray(Object[] argArray) {
            loggingEvent.setArgumentArray(argArray);
        }

        @Override
        public Object[] getArgumentArray() {
            return loggingEvent.getArgumentArray();
        }

        @Override
        public Level getLevel() {
            return loggingEvent.getLevel();
        }

        @Override
        public String getLoggerName() {
            return loggingEvent.getLoggerName();
        }

        @Override
        public void setLoggerName(String loggerName) {
            loggingEvent.setLoggerName(loggerName);
        }

        @Override
        public String getThreadName() {
            return loggingEvent.getThreadName();
        }

        @Override
        public void setThreadName(String threadName) throws IllegalStateException {
            loggingEvent.setThreadName(threadName);
        }

        @Override
        public IThrowableProxy getThrowableProxy() {
            return loggingEvent.getThrowableProxy();
        }

        @Override
        public void setThrowableProxy(ThrowableProxy tp) {
            loggingEvent.setThrowableProxy(tp);
        }

        @Override
        public void prepareForDeferredProcessing() {
            loggingEvent.prepareForDeferredProcessing();
        }

        @Override
        public LoggerContextVO getLoggerContextVO() {
            return loggingEvent.getLoggerContextVO();
        }

        @Override
        public void setLoggerContextRemoteView(LoggerContextVO loggerContextVO) {
            loggingEvent.setLoggerContextRemoteView(loggerContextVO);
        }

        @Override
        public String getMessage() {
            return loggingEvent.getMessage();
        }

        @Override
        public void setMessage(String message) {
            loggingEvent.setMessage(message);
        }

        @Override
        public long getTimeStamp() {
            return loggingEvent.getTimeStamp();
        }

        @Override
        public void setTimeStamp(long timeStamp) {
            loggingEvent.setTimeStamp(timeStamp);
        }

        @Override
        public void setLevel(Level level) {
            loggingEvent.setLevel(level);
        }

        @Override
        public StackTraceElement[] getCallerData() {
            return loggingEvent.getCallerData();
        }

        @Override
        public boolean hasCallerData() {
            return loggingEvent.hasCallerData();
        }

        @Override
        public void setCallerData(StackTraceElement[] callerDataArray) {
            loggingEvent.setCallerData(callerDataArray);
        }

        @Override
        public Marker getMarker() {
            return loggingEvent.getMarker();
        }

        @Override
        public void setMarker(Marker marker) {
            loggingEvent.setMarker(marker);
        }

        @Override
        public long getContextBirthTime() {
            return loggingEvent.getContextBirthTime();
        }

        @Override
        public String getFormattedMessage() {
            return chunkMessage;
        }

        @Override
        public Map<String, String> getMDCPropertyMap() {
            return loggingEvent.getMDCPropertyMap();
        }

        @Override
        public void setMDCPropertyMap(Map<String, String> map) {
            loggingEvent.setMDCPropertyMap(map);
        }

        @Override
        @SuppressWarnings("deprecation")
        public Map<String, String> getMdc() {
            return loggingEvent.getMdc();
        }

        @Override
        public String toString() {
            return loggingEvent.toString();
        }
    }
}
