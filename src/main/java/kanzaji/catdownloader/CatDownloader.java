package kanzaji.catdownloader;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

// import kanzaji.catdownloader.Manifest;

public final class CatDownloader {
    public static void main(String[] args) {
        // "What the hell did I just run" section
        System.out.println("Manifest to MinecraftInstance Converter v1.0");
        System.out.println("Created by: Kanzaji -> My first project in java \\o/");

        // Setting directory where program was turned on
        File dir = new File(".");
        System.out.println("Running in " + dir.getAbsolutePath());

        // Checking if manifest.json is present
        File manifestFile = new File(dir, "manifest.json");
        if (!manifestFile.exists()) {
            System.out.println("No manifest.json file exists in this directory, aborting!");
            System.exit(1);
        }

        // Checking if minecraftinstance.json isn't already created.
        File instanceFileCheck = new File(dir, "minecraftinstance.json");
        if (instanceFileCheck.exists()) {
            System.out.println("minecraftinstance.json already exists, aborting!");
            System.exit(1);
        }

        // Getting data from manifest.json
        Gson gson = new Gson();
        try {
            System.out.println(gson.fromJson((new FileReader(manifestFile)),Manifest[].class));
        } catch (IOException e) {
            System.out.println("ERROR: Something bad happened!");
            e.printStackTrace();
        }

        // Creating new minecraftinstance.json with data from manifest.json
        System.out.println("Creating minecraftinstance.json...");
        try {
            FileWriter instanceFile = new FileWriter(".\\minecraftinstance.json");
            instanceFile.write("{\"test\"}");
            instanceFile.close();
            if (!instanceFileCheck.exists()) {
                System.out.println("Something horrible happenend...");
                System.exit(1);
            }
            System.out.println("minecraftinstance.json created!");
         } catch (IOException err) {
            err.printStackTrace();
         }
    }
}