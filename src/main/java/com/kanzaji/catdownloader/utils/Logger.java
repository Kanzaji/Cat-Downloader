package com.kanzaji.catdownloader.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static Logger instance = null;

    File dir = new File(".");
    File logFile = new File(dir, "Launcher.log");

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void init() {
        if (this.logFile.exists()) {
            this.logFile.delete();
            try {
                this.logFile.createNewFile();
                this.log("Old Log file found! \"Launcher.log\" file replaced with empty one.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.logFile.createNewFile();
                this.log("\"Launcher.log\" file created.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.log("Logger Initialization completed.");
    }

    public void log(String msg) {
        this.log(msg, 0, null);
    }

    public void log(String msg, int type) {
        this.log(msg, type, null);
    }

    public void log(String msg, int type, Throwable error) {
        // TODO: Pretty much add type support xD
        try {
            FileWriter logFileWriter = new FileWriter(logFile, true);
            logFileWriter.append("[" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Date()) + "] " + msg + "\n");
            if (error != null) {
                logFileWriter.append("[" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Date()) + "] " + error.getMessage() + "\n");
            }
            logFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
