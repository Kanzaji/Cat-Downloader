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
                String final_url = "";
                try {
                    URL url = new URL("api.cfwidget.com/" + projectID);
                    InputStreamReader site_data = new InputStreamReader(url.openStream());
                    data json_data = gson.fromJson(site_data, data.class);
                    return json_data.urls[0].project+"/files/"+fileID;
                } catch (Exception e) {
                    System.out.println("Failed to get downloadURL for project with ID: " + projectID + ". Aborting!");
                    e.printStackTrace();
                }

                return final_url;
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
        private urls[] urls;
    }

    private class urls {
        private String project;
    } 
}
