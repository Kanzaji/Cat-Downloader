package kanzaji.catdownloader;

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

        public String getFileName() {
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
}
