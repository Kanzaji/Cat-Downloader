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
        public Number ProjectID;
        public Number FileID;
        public String downloadURL;
        public Boolean required;
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
