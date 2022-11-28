package kanzaji.catdownloader;

import com.google.gson.Gson;

import vazkii.instancesync.DownloadManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class CatDownloader {

    public static String VERSION = "1.1.1";

    public static void main(String[] args) {
        // "What the hell did I just run" section
        System.out.println("---------------------------------------------------------------------");
        System.out.println("     Cat Downloader " + VERSION);
        System.out.println("     Created by: Kanzaji -> My first project in java \\o/");
        System.out.println("---------------------------------------------------------------------");

        // Setting directory where program was turned on
        File dir = new File(".");
        System.out.println("Running in " + dir.getAbsolutePath());

        // Checking if manifest.json is present
        File manifestFile = new File(dir, "manifest.json");
        if (!manifestFile.exists()) {
            System.out.println("No manifest.json file exists in this directory, aborting!");
            System.exit(1);
        }

        // Getting data from manifest.json
        Gson gson = new Gson();
        try {
            Manifest manifest = gson.fromJson((new FileReader(manifestFile)),Manifest.class);
            // Checking if manifest contains modpack name, it doesn't matter that much, but its better to check :P
            if (manifest.name == null) {
                System.out.println("manifest.json doesn't have modpack name!");
            } else {
                System.out.println("Installing modpack: " + manifest.name + " " + manifest.version);
                System.out.println("That requires ModLoader: " + manifest.minecraft.version + " " + manifest.minecraft.modLoaders[0].id);
            }
            // Checking if manifest has any mods.
            if (manifest.files.length == 0) {
                System.out.println("manifest.json doesn't have any mods in it! So no job for me :D");
                System.exit(0);
            }
            // Some more info about modpack
            System.out.println("Found " + manifest.files.length + " mods!");

            // Checking if /mods directory exists and can be used
            File mods = new File(dir, "mods");
            if(mods.exists() && !mods.isDirectory()) {
                System.out.println("/mods exists but is a file, aborting");
                System.exit(1);
            }
            
            if(!mods.exists()) {
                System.out.println("/mods does not exist, creating");
                mods.mkdir();
            }

            // Using modififed Vazkii DownloadManager to download mods
            DownloadManager manager = new DownloadManager(mods);
            manager.downloadInstance(manifest); 

        } catch (IOException e) {
            System.out.println("[ERROR]: Something bad happened...");
            e.printStackTrace();
        }
    }
}