package com.aaexample.restaurant;

public class ListItem {
    private String mLabel;
    private float details;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    public ListItem(String Label, int mImageResourceId) {
        this.mLabel = Label;
        this.mImageResourceId = mImageResourceId;
    }

    public ListItem(String Label, int mImageResourceId,float price) {
        this.mLabel = Label;
        this.mImageResourceId = mImageResourceId;
        this.details = price;
    }

    public String getmTableNumber() {
        return mLabel;
    }

    public float getDetails() { return details; }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
