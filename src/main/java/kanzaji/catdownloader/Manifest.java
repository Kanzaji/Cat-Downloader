package kanzaji.catdownloader;

public class Manifest {
    public String manifestType;
    public String version;
    public Files[] files;
    public Number manifestVersion;
    public String name;
    public String overrides;
    public String author;
    public minecraft[] minecraft;

    public static class Files {
        public Number ProjectID;
        public Number FileID;
        public String downloadURL;
        public Boolean required;
    }

    public static class minecraft {
        public String version;
        public modLoaders[] modLoaders;
    }

    public static class modLoaders {
        public String id;
        public boolean primary;
    }
}
