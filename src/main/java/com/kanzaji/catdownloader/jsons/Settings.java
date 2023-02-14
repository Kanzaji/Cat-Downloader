package com.kanzaji.catdownloader.jsons;

public class Settings {
    public Launcher launcher;
    public boolean cached;

    public class Launcher {
        public String path;
        public String type;
    }
}
