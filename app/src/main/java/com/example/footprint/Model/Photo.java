package com.example.footprint.Model;

public class Photo {
    private String id;
    private Urls urls;

    public String getId() {
        return id;
    }

    public String getUrls() {
        return urls.getSmall();
    }

    private class Urls{
        private String small;
        private String regular;

        public String getSmall() {
            return small;
        }

        public String getRegular() {
            return regular;
        }
    }


}
