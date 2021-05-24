package com.aaexample.restaurant;

public class ListItem {
    private String mLabel;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    public ListItem(String Label, int mImageResourceId) {
        this.mLabel = Label;
        this.mImageResourceId = mImageResourceId;
    }

    public String getmTableNumber() {
        return mLabel;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
