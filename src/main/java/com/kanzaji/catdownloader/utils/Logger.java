package com.kanzaji.catdownloader.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;

public class Logger {
    private static Logger instance = null;

    Path logFile = Path.of(".", "Launcher.log");

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void init() {
        try {
            if (Files.deleteIfExists(this.logFile)) {
                Files.createFile(this.logFile);
                this.log("Old Log file found! \"Launcher.log\" file replaced with empty one.");
            } else {
                Files.createFile(this.logFile);
                this.log("\"Launcher.log\" file created.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.log("Logger Initialization completed.");
    }

    public void log(String msg) {
        this.log(msg, 0, null);
    }

    public void log(String msg, int type) {
        this.log(msg, type, null);
    }

    public void log(String msg, Throwable error) {
        this.log(msg, 3, error);
    }

    public void log(String msg, int type, Throwable error) {
        // TODO: Pretty much add type support xD
        try {
            Files.writeString(this.logFile, "[" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Date()) + "] " + msg + "\n", StandardOpenOption.APPEND);
            if (error != null) {
                Files.writeString(this.logFile, "[" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Date()) + "] " + error.getMessage() + "\n", StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
