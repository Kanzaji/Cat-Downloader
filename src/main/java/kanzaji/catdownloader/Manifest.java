package kanzaji.catdownloader;

import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;

public class Manifest {
    public String manifestType;
    public String version;
    public Files[] files;
    public Number manifestVersion;
    public String name;
    public String overrides;
    public String author;
    public minecraft minecraft;

    public class Files {
        public Number projectID;
        public Number fileID;
        public String downloadUrl;
        public Boolean required;

        public String getDownloadUrl() {
            Gson gson = new Gson();
            if (downloadUrl == null) {
                System.out.println("Getting downloadURL for project with ID: " + projectID);
                try {
                    URL url = new URL("https://api.cfwidget.com/" + projectID);
                    InputStreamReader site_data = new InputStreamReader(url.openStream());
                    data json_data = gson.fromJson(site_data, data.class);
                    for (data_files file : json_data.files) {
                        System.out.println(file.name + " / " + file.id  + " / " + fileID);
                        if (file.id == fileID) {
                            String url_2 = "https://edge.forgecdn.net/files/" + String.valueOf(file.id).substring(0, 3) + "/" + String.valueOf(file.id).substring(4) + "/" + file.name;
                            System.out.println(url_2);
                            return url_2;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Failed to get downloadURL for project with ID: " + projectID + ".");
                    e.printStackTrace();
                }
                System.out.println("Failed to get downloadURL for project with ID: " + projectID + " for unknown reason.");
                return "";
            } else {
                return downloadUrl;
            }
        }

        public String getFileName() {
            downloadUrl = getDownloadUrl();
            int cut = downloadUrl.lastIndexOf("/");
            return downloadUrl.substring(cut+1);
        }
    }

    public class minecraft {
        public String version;
        public modLoaders[] modLoaders;
    }

    public class modLoaders {
        public String id;
        public boolean primary;
    }

    private class data {
        private data_files[] files;
    }

    private class data_files {
        private Number id;
        private String name;
    } 
}
