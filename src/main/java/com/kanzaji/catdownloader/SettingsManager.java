package com.kanzaji.catdownloader;

import com.google.gson.Gson;
import com.kanzaji.catdownloader.jsons.Settings;
import com.kanzaji.catdownloader.utils.Logger;

import java.io.IOException;
import java.nio.file.*;

public class SettingsManager {
    private static SettingsManager instance = null;
    public boolean isWindows = System.getProperty("os.name").startsWith("Windows");
    public String fsp = System.getProperty("file.separator"); // File Separator
    boolean initSuccesful = false;
    Path LocalDir;
    Path Kanzaji;
    Path CatDownloader;
    Path SettingsFile;

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

        this.LocalDir = Path.of(System.getProperty("user.home") + fsp + "Appdata" + fsp + "Local");
        this.Kanzaji = Path.of(LocalDir.toAbsolutePath().toString(), "Kanzaji");
        this.CatDownloader = Path.of(Kanzaji.toAbsolutePath().toString(), "CatDownloader");

        if (!this.Kanzaji.toFile().exists()) {
            logger.log("Kanzaji folder in " + this.LocalDir.toAbsolutePath() + " not found! Creating...");
            try {
                Files.createDirectory(this.Kanzaji);
            } catch (IOException e) {
                logger.log("Failed to create Kanzaji folder in Appdata!", e);
                return;
            }
            logger.log(this.Kanzaji.toAbsolutePath() + " created.");
        }

        if (!this.CatDownloader.toFile().exists()) {
            logger.log(this.CatDownloader.toAbsolutePath() + " not found! Creating \"CatDownloader\" Folder and template of settings.");
            try {
                Files.createDirectory(this.CatDownloader);
            } catch (IOException e) {
                logger.log("Failed to create CatDownloader folder in Appdata!", e);
                return;
            }
            Settings data = gson.fromJson("{}", Settings.class);
            data.launcher = Path.of(".");
            data.cached = false;
            // TODO: Fix this stupid thing writing class path and not the actual data
            this.SettingsFile = Path.of(this.CatDownloader.toAbsolutePath().toString(), "settings.json");
            try {
                Files.createFile(this.SettingsFile);
            } catch (IOException e) {
                logger.log("Failed to create settings file in Appdata!", e);
                return;
            }
        } else {
            logger.log("CatDownloader data folder found!");
        }
        this.initSuccesful = true;
        logger.log("SettingsManager initialization completed.");
    }
}
