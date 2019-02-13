package com.example.geniusplazatest.model;

public class ModelUser {

    private String  mFirstName;
    private String  mLastName;
    private String  mAvatarUrl;

    public ModelUser(String fName, String lName, String aUrl)
    {
        mFirstName = fName;
        mLastName = lName;
        mAvatarUrl = aUrl;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String mAvatarUrl) {
        this.mAvatarUrl = mAvatarUrl;
    }
}
