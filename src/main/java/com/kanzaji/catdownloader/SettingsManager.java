package com.kanzaji.catdownloader;

import com.google.gson.Gson;
import com.kanzaji.catdownloader.utils.Logger;

import java.io.File;
import java.io.FileReader;

public class SettingsManager {
    private static SettingsManager instance = null;
    public boolean isWindows = System.getProperty("os.name").startsWith("Windows");
    public String fsp = System.getProperty("file.separator"); // File Separator
    boolean initSuccesful = false;
    File LocalDir;
    File Kanzaji;
    File CatDownloader;

    Gson gson = new Gson();
    Logger logger = Logger.getInstance();

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public void init() {
        if (!isWindows) {
            logger.log("Settings for your operating system (" + System.getProperty("os.name") + ") are not available yet! Falling back to \"mod download only\" mode.");
            return;
        }
        this.LocalDir = new File(System.getProperty("user.home") + fsp + "Appdata" + fsp + "Local");
        this.Kanzaji = new File(LocalDir, "Kanzaji");
        this.CatDownloader = new File(Kanzaji, "CatDownloader");
        if (!this.Kanzaji.exists()) {
            logger.log("Kanzaji folder in " + LocalDir.getAbsolutePath() + " not found! Creating...");
            Kanzaji.mkdir();
            logger.log(Kanzaji.getAbsolutePath() + " created.");
        }
        if (!this.CatDownloader.exists()) {
            logger.log(CatDownloader.getAbsolutePath() + " not found! Creating \"CatDownloader\" Folder and template of settings.");
            this.CatDownloader.mkdir();
            
        }
    }
}
