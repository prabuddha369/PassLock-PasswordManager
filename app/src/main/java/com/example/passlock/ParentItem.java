package com.example.passlock;

public class ParentItem {

    String socialmedia;
    int imageId;

    public ParentItem(String socialmedia, int imageId) {
        this.socialmedia = socialmedia;
        this.imageId = imageId;
    }

    public String getSocialmedia() {
        return socialmedia;
    }

    public int getImageId() {
        return imageId;
    }
}