package com.example.passlock;

public class ChildItem {

    String userid,password;
    int imageID,imageID2,imageID3,imageID4;

    public ChildItem(String userid, String password,int imageID2,int imageID3,int imageID4,int imageID) {
        this.userid=userid;
        this.password=password;
        this.imageID=imageID;
        this.imageID2=imageID2;
        this.imageID3=imageID3;
        this.imageID4=imageID4;
    }

    public String getPassword() {
        return password;
    }

    public String getUserid() {
        return userid;
    }

    public int getImageID() {
        return imageID;
    }

    public int getImageID2() {
        return imageID2;
    }

    public int getImageID3() {
        return imageID3;
    }

    public int getImageID4() {
        return imageID4;
    }
}