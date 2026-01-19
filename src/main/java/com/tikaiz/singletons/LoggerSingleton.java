package com.tikaiz.singletons;

import com.hypixel.hytale.logger.HytaleLogger;

public class LoggerSingleton {
    private HytaleLogger hytaleLogger;
    private static LoggerSingleton instance;
    public static LoggerSingleton getInstance() {
        if (instance == null) {
            instance = new LoggerSingleton();
        }
        return instance;
    }

    private LoggerSingleton() {
    }

    public HytaleLogger getHytaleLogger() {
        return hytaleLogger;
    }

    public void setHytaleLogger(HytaleLogger hytaleLogger) {
        this.hytaleLogger = hytaleLogger;
    }
}
